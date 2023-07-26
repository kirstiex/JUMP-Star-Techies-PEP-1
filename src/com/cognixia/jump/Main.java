package com.cognixia.jump;

import java.sql.SQLException;
import com.cognixia.jump.dao.BooksDAO;
import com.cognixia.jump.dao.BooksDAOImpl;
import com.cognixia.jump.dao.Tracker;
import com.cognixia.jump.dao.UserDAO;
import com.cognixia.jump.dao.UserDAOImpl;

import java.util.Optional;
import java.util.Scanner; 

/*
	Use this class as a way to test out your DAO and make sure it is working properly. There is no need 
	to write a menu or create an actual working program. Just use this as a way to see if methods work as
	they should.  
	
	You are free to ignore this file or make another class to test everything out.
	
	Do note that the connection is opened and closed for you in this file. Just make sure you test your methods
	in the space between opening and closing the connection.
 */

public class Main {
	
	
	public static void main(String[] args) {
		
		//New objects 
		BooksDAO booksDao = new BooksDAOImpl();
		
		// Call establishConnection() first to make sure you are connected to the chef database before
		// you call any other methods
		try {
			booksDao.establishConnection();	
			
		} catch (ClassNotFoundException | SQLException e1) {
			
			System.out.println("\nCould not connect to the Books Database, application cannot run at this time.");
		}
	}
		// TEST THE REST OF YOUR METHODS IN THE DAO FROM THIS LINE FORWARD
			
			//print the menu to the command line. 
	public static void displayMenu() {
		// login -- show all books --- add book to tracker ---- update the tracker --- delete book in tracker --- view all books in the tracker --- get an item 
		// quit 				
		System.out.println("MENU: ");
		System.out.println("------------------------------");
		System.out.println("1 - View All Books");
		System.out.println("2 - Login");
		System.out.println("3 - Quit");
				    
	}
	
	public static void loginMenu() {
		System.out.println("1 - Update a tracker");
		System.out.println("2 - delete a book in the tracker");
		System.out.println("3 - view all books in the tracker");
		System.out.println("4 - add a book to tracker");
		System.out.println("5 - find a book in the tracker");
		System.out.println("6 - Quit"); 
		
	}
			
	public static int getInitialInput(Scanner input) {
		System.out.print("Enter a number 1 - 3: ");
		int userChoice = input.nextInt();
		return userChoice;
	}
	
	public static int getUserInput(Scanner input) {
		System.out.print("Enter a number 1 - 4 : ");
		int userChoice = input.nextInt();
		return userChoice;
	}
	
	public void getInitialChoice(Scanner input) {
		int choice;
		do {
			choice = getUserInput(input);
					
		switch (choice) {
		case 1:
			BookTrackerDAO.getall();
		case 2:
			//login
			String name;
			String password;
			
			BooksTrackerDAO.userLogIn(name, password);
			
		case 3:
			//quit
			System.out.println("Thank you. Have a great day!");
			break;
		default:
			System.out.println("Not a valid input. Try again");
			
			} 
		} while(choice != 6);
	}
			
	public void getUserChoice(Scanner input) {
		int choice;
		do {
			choice = getUserInput(input);
					
		switch (choice) {
		case 1:
			//update tracker
			BooksTrackerDAO.findById(tracker_id);
		case 2:
			//delete a book in tracker
			BooksTrackerDAO.delete();
		case 3:
			//view all books in tracker
			
		case 4:
			//add a book to the tracker
			
		case 5:
			//quit
			System.out.println("Thank you. Have a great day!");
			break;
		default:
			System.out.println("Not a valid input. Try again");
			
			} 
		} while(choice != 6);
	}
}


	

			

			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// once done, always close your connection
				try {
					booksDao.closeConnection();
				} catch (SQLException e) {
					System.out.println("Could not close connection properly");
				}
			}
		
	}
