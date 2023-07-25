//package com.cognixia.jump.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//import com.cognixia.jump.connection.ConnectionManager;
//
//public class UserDAOImpl implements UserDAO {
//	
//	private Connection connection = null;
//
//	@Override
//	public void establishConnection() throws ClassNotFoundException, SQLException {
//		if(connection == null) {
//			connection = ConnectionManager.getConnection();
//		}
//	}
//
//	@Override
//	public void closeConnection() throws SQLException {
//		connection.close();
//	}
//
//	@Override
//	public boolean userLogIn(String username, String password) {
//		
//		boolean credential_check = false;
//		
//		try {
//			Connection conn = ConnectionManager.getConnection();
//			Statement stmt = conn.createStatement();
//			
//			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username=" + username);
//			
//			if(rs.next()) {
//				if (rs.getString(2) == password) {
//					credential_check = true;
//				}
//			}
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return credential_check;
//	}
//
//	@Override
//	public User signUp(User user) {
////		Connection conn = null;
////		
////		try {
////			conn = ConnectionManager.getConnection();
////		} catch (ClassNotFoundException e) {
////			e.printStackTrace();
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////		
////		String sql = "INSERT INTO user " +
////					"VALUES(null, ?, ?, ?);";
////		
////		try {
////			PreparedStatement pstmt = conn.prepareStatement(sql);
////			pstmt.setString(1, user.getUsername());
////			pstmt.setString(2, user.getPassword());
////			pstmt.setString(3, user.getStatus());
////			
////			pstmt.executeUpdate();
////			
////			User user = new User(-1, username, password, "user");
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
//		return user;
//	}
//
//}
