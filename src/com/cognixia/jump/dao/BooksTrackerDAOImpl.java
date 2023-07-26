package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.cognixia.jump.connection.ConnectionManager;

/*
DAO CLASS
-------------------
- Class already implements the DAO interface

- Finish implementing all the methods inherited from the DAO Interface

- Do not make changes to establishConnection() or closeConnection(), those methods have already
  been completed for you

- Hint: Take a look at the DAO interface file for extra details on how to complete methods
*/

public class BooksTrackerDAOImpl implements BooksTrackerDAO{
		
		private Connection connection = null;

		@Override
		public void establishConnection() throws ClassNotFoundException, SQLException {
			
			if(connection == null) {
				connection = ConnectionManager.getConnection();
			}
		}
		
		@Override
		public void closeConnection() throws SQLException {
			connection.close();
		}
		
		
		
		// -----------------------------------------------
		// PROVIDE IMPLEMENTATIONS FOR ALL METHODS BELOW
		// -----------------------------------------------
	
		@Override
		public List<Book> getAll(){
			// TODO Auto-generated method stub
			List<Book> books = new ArrayList<>();
			
			Connection conn = null;
			try {
				conn = ConnectionManager.getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery("SELECT * FROM books");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				while(rs.next()) {
					try {
						books.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3)));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return books;
		}

		@Override
		public Optional<Book> findById(int id) throws ClassNotFoundException, SQLException {
			// TODO Auto-generated method stub
			Connection conn = ConnectionManager.getConnection();
			   try {
			        Statement stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery("SELECT * FROM book WHERE id = " + id);

			        if (rs.next()) {
			            return Optional.of(new Book(rs.getInt(1), rs.getString(2), rs.getString(3)));
			        } else {
			            return Optional.empty();
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return Optional.empty();
			    }
		}

//		@Override
//		public boolean update(Book book) throws ClassNotFoundException, SQLException {
//			Connection conn = ConnectionManager.getConnection();
//			try {
//				String query = "UPDATE book SET book_name = ?, book_Author = ?, WHERE id = ?";
//		        PreparedStatement pstmt = conn.prepareStatement(query);
//		        
//		        //Set the values
//		        pstmt.setString(1, book.getBook_name());
//		        pstmt.setString(2, book.getAuthor());
//		        pstmt.setInt(3, book.getId());
//		        
//		        int rowsUpdated = pstmt.executeUpdate();
//		        
//		        return rowsUpdated > 0;
//			} catch (SQLException e) {
//				e.printStackTrace();
//				return false;    
//			}
//		}
//
//		@Override
//		public boolean delete(int id) {
//			Connection conn = null;
//		    PreparedStatement pstmt = null;
//		    
//		    try {
//		        conn = ConnectionManager.getConnection();
//		        String query = "DELETE FROM book WHERE id = ?";
//		        pstmt = conn.prepareStatement(query);
//
//		        // Set the value for the placeholder in the prepared statement
//		        pstmt.setInt(1, id);
//
//		        int rowsDeleted = pstmt.executeUpdate();
//
//		        // Check if any rows were deleted and return true if at least one row was deleted
//		        return rowsDeleted > 0;
//		    } catch (SQLException | ClassNotFoundException e) {
//		        e.printStackTrace();
//		        return false;
//		        
//		    }
//		}
//			
//
//		@Override
//		public Book add(Book book) throws ClassNotFoundException, SQLException {
//		
//			Connection conn = ConnectionManager.getConnection();
//			try {
//				String query = "INSERT INTO chef(book_name, author, book_id) VALUES (?,?,?)";
//		        PreparedStatement pstmt = conn.prepareStatement(query);
//		        
//		        //Set the values
//		        pstmt.setString(1, book.getBook_name());
//		        pstmt.setString(2, book.getAuthor());
//		        pstmt.setInt(3, book.getId());
//		        
//		        int rowsAdded = pstmt.executeUpdate();
//		        
//		        if(rowsAdded > 0) {
//		        	return book;
//		        }
//		        
//			} catch (SQLException e) {
//				e.printStackTrace();
//				  
//			} return null;  
//		}

		@Override
		public boolean userLogIn(String username, String password) {
			boolean credential_check = false;
			
			try {
				Connection conn = ConnectionManager.getConnection();
				Statement stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username=" + username);
				
				if(rs.next()) {
					if (rs.getString(2) == password) {
						credential_check = true;
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return credential_check;
		}
		
		@Override
		public int getUserTrackerId(User user) {
			int user_tracker_id = -1;
			
			try {
				Connection conn = ConnectionManager.getConnection();
				Statement stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery("select trackers.tracker_id from users"
												+ "join trackers on trackers.user_id = users.user_id\r\n"
												+ "where users.user_id = " + user.getId());
				
				if(rs.next()) {
					user_tracker_id = rs.getInt(1);
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			return user_tracker_id;
		}

		@Override
		public List<Book> getAllByUserId(User user) {
			List<Book> tracker = new ArrayList<>();
			
			try {
				Connection conn = ConnectionManager.getConnection();
				Statement stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery("select *"
												+ "from books" 
												+ "join books_trackers on books.book_id = books_trackers.book_id"
												+ "join trackers on trackers.tracker_id = books_trackers.tracker_id"
												+ "join users on users.user_id = trackers.user_id"
												+ "where users.user_id =" + user.getId() +";");
				
				while(rs.next()) {
					tracker.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3)));
				}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return tracker;
		}

		@Override
		public boolean addBookToTracker(Book book, int tracker_id, String completion) {
			boolean result = false;
			
			Connection conn = null;
			try {
				conn = ConnectionManager.getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sql = "insert into books_trackers(book_id, tracker_id, completion)"
						+ "values (?, ?, ?)";
			
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, book.getId());
				pstmt.setInt(2, tracker_id);
				pstmt.setString(3, completion);
				
				pstmt.executeUpdate();
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}

		@Override
		public boolean updateBookStatus(String completion, int tracker_id, Book book) {
			
			boolean result = false;
			
			Connection conn = null;
			try {
				conn = ConnectionManager.getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sql = "update books_trackers"
						+ "set completion = ?"
						+ "where book_id = ? and tracker_id = ?;";
			
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, completion);
				pstmt.setInt(2, book.getId());
				pstmt.setInt(3, tracker_id);
				
				
				pstmt.executeUpdate();
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}

}

