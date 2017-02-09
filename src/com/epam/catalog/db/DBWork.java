package com.epam.catalog.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWork {
	 
	    private String user = "root";
	    private String password = "1234";
	    private String url = "jdbc:mysql://localhost:3306/catalog";
	    private String driver = "com.mysql.jdbc.Driver";

	    private Connection cn;
	    private Statement st;
	    private ResultSet rs;

	    public Connection getCn() {
	        return cn;
	    }

	    public DBWork() {
	        try {
	            Class.forName(driver);
	            try {
	                cn = (Connection) DriverManager.getConnection(url, user,password);
	                st = (Statement) cn.createStatement();

	            } catch (SQLException e) {
	                System.out.println("error in create connection " + e);
	            }
	        } catch (ClassNotFoundException e) {
	            System.out.println("error in download driver " + e);
	        }

	    }

	    public void update(String sql) {
	        try {
	            st.executeUpdate(sql);
	        } catch (SQLException e) {
	            System.out.println("error in update " + e);
	        }
	    }

	    public ResultSet query(String sql) {
	        try {
	            rs = st.executeQuery(sql);
	        } catch (SQLException e) {
	            System.out.println("error in query " + e);
	        }
	        return rs;
	    }
	    public void showDatabaseMetaData() {
	        try {
	            
	            DatabaseMetaData dbmd = cn.getMetaData();
	          
	            System.out.println(dbmd.getDatabaseProductName());
	            System.out.println(dbmd.getDatabaseProductVersion());
	            System.out.println(dbmd.getDriverName());
	            System.out.println(dbmd.getDriverVersion());
	        } catch (SQLException ex) {
	            System.out.println("Error in showDatabaseMetaData " + ex);
	        }
	    }
	    public void showResultSet(ResultSet rs) {
	        try {System.out.println();
	            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
	            for (int i = 1; i < rsmd.getColumnCount()+1; i++) {
	                System.out.print(rsmd.getColumnName(i) + "\t");
	            }
	            while (rs.next()) {
	                System.out.println();
	                for (int i = 1; i < rsmd.getColumnCount()+1; i++) {
	                    System.out.print(rs.getString(i) + "\t\t");
	                }
	            }
	        } catch (SQLException e) {

	            e.printStackTrace();
	        }
	    }

	    public void close() {
	        try {
	            st.close();
	            cn.close();
	        } catch (SQLException e) {
	            System.out.println("error in close " + e);
	        }
	    }
}
