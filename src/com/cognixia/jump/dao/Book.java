package com.cognixia.jump.dao;
/*
	DAO MODEL CLASS
	---------------------------
	1.) Make sure the following data is being stored:
		- id
		- book_name
		- author
		
	2.) Add a constructor that accepts all the attributes as arguments
	
	3.) Have getters/setters for each attribute
	
	4.) toString() method to help with testing
*/


public class Book {
	
	private int id = -1;
	private String book_name;
	private String author;
	
	public Book(int id, String book_name, String author) {
		super();
		this.setId(id);
		this.setBook_name(book_name);
		this.setAuthor(author);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", book name=" + book_name + ", author=" + author + "]";
	}

}
