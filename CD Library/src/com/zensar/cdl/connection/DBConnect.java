package com.zensar.cdl.connection;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnect {
	private static Connection conn=null;
	public static Connection getConnection() {
		FileReader file;
		try {
			file = new FileReader("connection.properties");
			Properties prop=new Properties();
			prop.load(file);
			Class.forName(prop.getProperty("driver"));
			conn=DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}
}
