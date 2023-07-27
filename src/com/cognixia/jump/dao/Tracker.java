package com.cognixia.jump.dao;

/*
	DAO MODEL CLASS
	---------------------------
	1.) Make sure the following data is being stored:
		- tracker_id
		- user_id

	2.) Add a constructor that accepts all the attributes as arguments

	3.) Have getters/setters for each attribute

	4.) toString() method to help with testing
*/

public class Tracker {

	private int tracker_id = -1;
	private int user_id;

	public Tracker(int tracker_id, int user_id) {
		super();
		this.setTracker_id(tracker_id);
		this.setUser_id(user_id);
	}

	public int getTracker_id() {
		return tracker_id;
	}

	public void setTracker_id(int tracker_id) {
		this.tracker_id = tracker_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Tracker [tracker_id=" + tracker_id + ", user_id=" + user_id + "]";
	}
}


