package com.cognixia.jump.dao;

public class BooksTracker {
		private int book_id = -1;
		private int tracker_id = -1;
		private Enum completion;
		
		
		public BooksTracker(int book_id, int tracker_id, Enum completion) {
			super();
			this.book_id = book_id;
			this.tracker_id = tracker_id;
			this.completion = completion;
		}
		public int getBook_id() {
			return book_id;
		}
		public void setBook_id(int book_id) {
			this.book_id = book_id;
		}
		public int getTracker_id() {
			return tracker_id;
		}
		public void setTracker_id(int tracker_id) {
			this.tracker_id = tracker_id;
		}
		public Enum getCompletion() {
			return completion;
		}
		public void setCompletion(Enum completion) {
			this.completion = completion;
		}
		
		@Override
		public String toString() {
			return "BooksTracker [book_id=" + book_id + ", tracker_id=" + tracker_id + ", completion=" + completion
					+ "]";
		}
		
		

	
}
