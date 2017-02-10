package com.epam.catalog.dao.connectionpool;


import com.epam.catalog.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance = null;
    private BlockingQueue<Connection> freeConn;
    private BlockingQueue<Object> busyConn;

    private String url;
    private String user;
    private String pass;

    private int maxConn;

    private ConnectionPool(int max, String url, String user, String pass, String driver) {
        try{
            Class.forName(driver);
        }catch(Exception e){
            System.err.println("No such driver.");
            e.printStackTrace();
            // System.exit(1);
        }

        maxConn= max > 0 ? max: 1;
        this.url = url;
        this.user = user;
        this.pass = pass;

        freeConn = new ArrayBlockingQueue<Connection>(maxConn);
        busyConn = new ArrayBlockingQueue<Object>(maxConn);
    }



    public static synchronized ConnectionPool getInstance(int max ,String url, String user, String pass,String driver) {

        if (instance == null) {
            instance = new ConnectionPool(max, url, user, pass, driver);
        }

        return instance;
    }

    public synchronized Connection getConnection() throws DaoException {
        if(!freeConn.isEmpty()){
            Object conn = null;
            try {
                conn = freeConn.take();
            } catch (InterruptedException e) {
                throw new DaoException(e);
            }
            freeConn.remove(freeConn.size()-1);
            busyConn.add(conn);
            return (Connection)conn;
        }
        if(busyConn.size() < maxConn){
            try{
                Connection temp = DriverManager.getConnection(url, user, pass);
                busyConn.add(temp);
                return temp;
            }catch(SQLException e){
                System.out.println("SQLException in ConnectionPool.getConnection();"+e);

            }
        }

        try{
            wait();
        }catch(InterruptedException ie){}

        return getConnection();
    }

    public void freeConnection(Connection conn){
        busyConn.remove(conn);
        freeConn.add(conn);
        //  notifyAll();
    }
}
