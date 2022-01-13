package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionManager {
	
	private static final String URL = "jdbc:mysql://localhost:3306/testdb";
	//private static final String UNIXONLY = "?serverTimezone=EST5EDT";
	private static final String USERNAME = "root";
	
	//String pass = getPassword();
	
	public static String getPassword() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter your MySQL password :");

		String pass = sc.nextLine();
		
		return pass;
		
	}

	
	//private static final String PASSWORD = "Mur@li18root";
	
	private static final String PASSWORD = getPassword();
	
	
	
	private static Connection connection = null;
	
	private static void makeConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Could not make connection.");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		
		if (connection == null) {
			makeConnection();
		}
		
		return connection;
	}


}
