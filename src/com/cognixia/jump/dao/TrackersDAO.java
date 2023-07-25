package com.cognixia.jump.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/*
	DAO INTERFACE
	--------------------
*/

public interface TrackersDAO {

    // needed for later so we make sure that the connection manager gets called
    public void establishConnection() throws ClassNotFoundException, SQLException;

    // as well, this method will help with closing the connection
    public void closeConnection() throws SQLException;

    // Return all trackers in the table
    public List<Tracker> getAll();

    // Find a tracker by its id and returns an Optional of tracker
    public Optional<Tracker> findById(int tracker_id);

    // Updates values for a given tracker, assume that the tracker object contains
    // the id
    // for the tracker to change.
    // Any values that aren't the id could have been changed, so update them all. As
    // well, as long as the tracker
    // exists (id is found in the table), you should be returning a true
    public boolean update(Tracker tracker);

    // Removes a tracker by its id. As long as the id given is in the table, return
    // true that you removed
    // that tracker
    public boolean delete(int tracker_id);

    // Create a new tracker in the table. Make sure to set the id of the tracker
    // after it
    // is added to the database.
    public Tracker add(Tracker tracker);
}
