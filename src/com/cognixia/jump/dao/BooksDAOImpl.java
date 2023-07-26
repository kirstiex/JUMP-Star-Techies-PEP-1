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

public class BooksDAOImpl implements BooksDAO {

    private Connection connection = null;

    @Override
    public void establishConnection() throws ClassNotFoundException, SQLException {

        if (connection == null) {
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
    public List<Book> getAll() {
        List<Book> book = new ArrayList<>();

        Statement stmt;
        try {
            stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM books");

            while (rs.next()) {

                book.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Optional<Book> findById(int id) {

        Statement stmt;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM book WHERE id = " + id);

            if (rs.next()) {
                return Optional.of(new Book(rs.getInt(1), rs.getString(2), rs.getString(3)));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Book book) {

        String query = "UPDATE book SET book_name = ?, book_Author = ?, WHERE id = ?";
        PreparedStatement pstmt;
        boolean checkUpdate = false;
        try {
            pstmt = connection.prepareStatement(query);

            // Set the values
            pstmt.setString(1, book.getBook_name());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getId());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                checkUpdate = true;
            } else {
                checkUpdate = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkUpdate;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM book WHERE id = ?";
        PreparedStatement pstmt;
        boolean checkDeleted = false;
        try {
            pstmt = connection.prepareStatement(query);
            // Set the value for id
            pstmt.setInt(1, id);

            // Check if any rows were deleted and return true if at least one row was
            // deleted
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                checkDeleted = true;
            } else {
                checkDeleted = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkDeleted;
    }

    @Override
    public Book add(Book book) {

        String query = "INSERT INTO book(book_name, author, book_id) VALUES (?,?,?)";
        // Set the values
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, book.getBook_name());

            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return book;
    }
}
