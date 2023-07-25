package com.cognixia.jump.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/*
	DAO INTERFACE
	--------------------
*/

public interface BooksDAO {

	// needed for later so we make sure that the connection manager gets called
	public void establishConnection() throws ClassNotFoundException, SQLException;

	// as well, this method will help with closing the connection
	public void closeConnection() throws SQLException;

	// Return all books in the table
	public List<Book> getAll();

	// Find a book by its id and returns an Optional of book
	public Optional<Book> findById(int id);

	// Updates values for a given book, assume that the book object contains the id
	// for the book to change.
	// Any values that aren't the id could have been changed, so update them all. As
	// well, as long as the book
	// exists (id is found in the table), you should be returning a true
	public boolean update(Book book);

	// Removes a book by its id. As long as the id given is in the table, return
	// true that you removed
	// that book
	public boolean delete(int id);

	// Create a new book in the table. Make sure to set the id of the book after it
	// is added to the database.
	public Book add(Book book);
}
