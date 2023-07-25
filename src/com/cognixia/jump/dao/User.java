package com.cognixia.jump.dao;

public class User {
	
	private int id = -1;
	private String username;
	private String password;
	private Enum status;
	
	public User(int id, String book_name, String author) {
		super();
		this.setId(id);
		this.setUsername(username);
		this.setPassword(password);
		this.setStatus(status);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Enum getStatus() {
		return status;
	}

	public void setStatus(Enum status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", status=" + status;
	}

}
