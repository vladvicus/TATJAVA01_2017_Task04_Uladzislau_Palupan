package com.epam.catalog.dao.connectionpool;


import com.epam.catalog.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final String USER = "root";
    private static ConnectionPool instance;
    private static String PASS = "1234";
    private static String URL = "jdbc:mysql://localhost:3306/catalog";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private final String MESSSAGE_CP = "SQLException in ConnectionPool.getConnection() ";
    private BlockingQueue<Connection> freeConn;
    private BlockingQueue<Connection> busyConn;
    private int maxConn;

    private ConnectionPool(int max) {

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("No JDBC driver found " + e);
            System.exit(1);
        }

        maxConn = max > 0 ? max : 1;
        freeConn = new ArrayBlockingQueue<Connection>(maxConn);
        busyConn = new ArrayBlockingQueue<Connection>(maxConn);
    }

    public static synchronized ConnectionPool getInstance(int max) {

        if (instance == null) {
            instance = new ConnectionPool(max);
        }
        return instance;
    }

    public synchronized Connection getConnection() throws DaoException {
        if (!freeConn.isEmpty()) {
            Connection conn = null;
            try {
                conn = freeConn.take();
            } catch (InterruptedException e) {
                throw new DaoException(e);
            }
            freeConn.remove(freeConn.size() - 1);
            busyConn.add(conn);
            return conn;
        }
        if (busyConn.size() < maxConn) {
            try {
                Connection temp = DriverManager.getConnection(URL, USER, PASS);
                busyConn.add(temp);
                return temp;
            } catch (SQLException e) {
                throw new DaoException(MESSSAGE_CP + e);

            }
        }
        return getConnection();
    }

    public void freeConnection(Connection conn) {
        busyConn.remove(conn);
        freeConn.add(conn);
    }
}
