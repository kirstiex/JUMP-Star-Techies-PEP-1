package com.cognixia.jump;

import java.sql.SQLException;
//import java.util.Optional;
import java.util.Scanner;

import com.cognixia.jump.dao.Book;
import com.cognixia.jump.dao.BooksTrackerDAO;
import com.cognixia.jump.dao.BooksTrackerDAOImpl;
import com.cognixia.jump.dao.Tracker;
import com.cognixia.jump.dao.User;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

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

		BooksTrackerDAO booksTrackerDao = new BooksTrackerDAOImpl();

		// Call establishConnection() first to make sure you are connected to the chef
		// database before
		// you call any other methods
		try {
			booksTrackerDao.establishConnection();

		} catch (ClassNotFoundException | SQLException e1) {

			System.out.println("\nCould not connect to the Books Database, application cannot run at this time.");
		}

		// TEST THE REST OF YOUR METHODS IN THE DAO FROM THIS LINE FORWARD
		Scanner input = new Scanner(System.in);
		displayMenu();
		User LoggedInUser = getInitialChoice(input);
		
		//try {
			if (LoggedInUser != null) {
				getUserChoice(LoggedInUser, null, 0, null, input);
			} 
		//} catch (SQLException e) {
	//		System.out.println("Could not close connection properly");
	//	}

		input.close();
		// once done, always close your connection
		try {
			booksTrackerDao.closeConnection();
		} catch (SQLException e) {
			System.out.println("Could not close connection properly");
		}
	}

	// print the menu to the command line.
	public static void displayMenu() {
		// login -- show all books --- add book to tracker ---- update the tracker ---
		// delete book in tracker --- view all books in the tracker --- get an item
		// quit
		System.out.println("MENU: ");
		System.out.println("------------------------------");
		System.out.println("1 - View All Books");
		System.out.println("2 - Login");
		System.out.println("3 - Quit");

	}

	public static void loginMenu() {
		System.out.println("1 - View all books in the tracker");
		System.out.println("2 - Add a book to tracker");
		System.out.println("3 - Update a book in tracker");
		System.out.println("4 - Delete a book in the tracker");
		System.out.println("5 - Quit");

	}

	public static int getInitialInput(Scanner input) {
		//includes mismatch exception
		int userChoice = 0;
		boolean validChoice = false;
		do{
			try {
		
			System.out.print("Enter a number 1 - 3: ");
			userChoice = input.nextInt();
			validChoice = userChoice >=1 && userChoice <= 3;
			return userChoice;
			}
		catch(InputMismatchException e) {
			input.nextLine(); 
			System.out.println("Invalid input. Please enter a valid number (1 - 3).");
        }
	} while(!validChoice);
	return userChoice;
	
	}

	public static int getUserInput(Scanner input) {
		//includes mismatch exception
		int userChoice = 0;
		boolean validChoice = false;
		do{
			try {		
		System.out.print("Enter a number 1 - 5 : ");
		userChoice = input.nextInt();
		validChoice = userChoice >= 1 && userChoice <= 5;
		return userChoice;
			}
			catch(InputMismatchException e) {
				input.nextLine(); 
				System.out.println("Invalid input. Please enter a valid number (1 - 3).");
	        }
		} while(!validChoice);
		return userChoice;
		
	}

	static String[] getCredential() {
		Scanner input = new Scanner(System.in);
		String[] user = new String[2];
		for (int i = 0; i < user.length; i++) {
			if (i == 0) {
				System.out.println("Please enter username: ");
				user[0] = input.nextLine().trim();
			} else {
				System.out.println("Please enter password: ");
				user[1] = input.nextLine().trim();
			}
		}
		
		return user;
		
	}

	public static User getInitialChoice(Scanner input) {
		BooksTrackerDAO booksTrackerDao = new BooksTrackerDAOImpl();
		int choice;
		
		do {
			choice = getInitialInput(input);
			switch (choice) {
			case 1:
				List<Book> books = new ArrayList<Book>();
				books = booksTrackerDao.getAll();

				for (int i = 0; i < books.size(); i++) {
					System.out.println(books.get(i));
				}
				break;
			case 2:
				// login
				String[] user = getCredential();

				boolean check = booksTrackerDao.userLogIn(user[0], user[1]);

				if (check) {
					System.out.println("Log in successfully!");
					//need to change this to return the logged in user. 
					User LoggedInUser = new User(2, user[0], user[1]);
					loginMenu();
					return LoggedInUser;
				} else {
					System.out.println("Incorrect credentials");
					displayMenu();
				}
				break;
			case 3:
				// quit
				System.out.println("Thank you. Have a great day!");
				break;
			default:
				System.out.println("Not a valid input. Try again");
			}
		}
		while (choice != 3);
		return null;
		
	}

	public static void getUserChoice(User user, Book book, int tracker_id, String completion, Scanner input) {
		BooksTrackerDAO booksTrackerDao = new BooksTrackerDAOImpl();
		int choice;
		do {
			choice = getUserInput(input);

			switch (choice) {
				case 1:
					// view all books in tracker
					List<Book> booksinTracker = new ArrayList<Book>();
					List<Tracker> trackers = new ArrayList<Tracker>();
					booksinTracker = booksTrackerDao.getAllByUserId(user);
					for (int i = 0; i < trackers.size(); i++) {
						System.out.println(trackers.get(i));
					}
					break;
				case 2:
					// add a book to the tracker
					booksTrackerDao.addBookToTracker(book, tracker_id, completion);
					break;
				case 3:
					// Update book status in user's tracker
					booksTrackerDao.updateBookStatus(completion, tracker_id, book);
					break;
				case 4:
					// delete a book in tracker
					booksTrackerDao.removeBookFromTracker(tracker_id, book);
					break;
				case 5:
					// quit
					System.out.println("Thank you. Have a great day!");
					break;
				default:
					System.out.println("Not a valid input. Try again");

			}
		} while (choice != 5);
	}

}
