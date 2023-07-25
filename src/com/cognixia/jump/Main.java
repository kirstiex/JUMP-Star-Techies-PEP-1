package com.cognixia.jump;

import java.sql.SQLException;
import com.cognixia.jump.dao.BooksDAO;
import com.cognixia.jump.dao.BooksDAOImpl;

/*
	Use this class as a way to test out your DAO and make sure it is working properly. There is no need 
	to write a menu or create an actual working program. Just use this as a way to see if methods work as
	they should. 
	
	You WILL NOT BE GRADED ON THIS CLASS, you will be graded based on your DAO methods and
	the implementations, as well as the setup for your Chef class. 
	
	You are free to ignore this file or make another class to test everything out.
	
	Do note that the connection is opened and closed for you in this file. Just make sure you test your methods
	in the space between opening and closing the connection.
 */

public class Main {
	
	
	public static void main(String[] args) {
		
		BooksDAO booksDao = new BooksDAOImpl();
		
		// Call establishConnection() first to make sure you are connected to the chef database before
		// you call any other methods
		try {
			booksDao.establishConnection();
			
			
		} catch (ClassNotFoundException | SQLException e1) {
			
			System.out.println("\nCould not connect to the Chef Database, application cannot run at this time.");
		}
		
		// TEST THE REST OF YOUR METHODS IN THE DAO FROM THIS LINE FORWARD
		
		// once done, always close your connection
				try {
					booksDao.closeConnection();
				} catch (SQLException e) {
					System.out.println("Could not close connection properly");
				}
			}
		
	}