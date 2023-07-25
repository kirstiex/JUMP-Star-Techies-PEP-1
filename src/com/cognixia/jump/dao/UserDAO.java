package com.cognixia.jump.dao;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
	
	// needed for later so we make sure that the connection manager gets called
	public void establishConnection() throws ClassNotFoundException, SQLException;
	
	// as well, this method will help with closing the connection
	public void closeConnection() throws SQLException ;
		
	// Find user by username
	public List<User> findByUsername(String username);
	
	// Sign up new user
	public User signUp(User user);
	
}
