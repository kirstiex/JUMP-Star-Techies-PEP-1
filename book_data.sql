USE booktracker;
-- disable FK constraints 
SET FOREIGN_KEY_CHECKS = 0;
--------------------------------------------------------
--  BOOKTRACKER
--------------------------------------------------------
-- insert users
Insert into booktracker.users (user_id, username, password, status) values (1,'Kirstie', 'password', 'user');
Insert into booktracker.users (user_id, username, password, status) values (2,'Ned', 'pass', 'user');
Insert into booktracker.users (user_id, username, password, status) values (3,'David', 'password1', 'admin');

-- insert trackers
Insert into booktracker.trackers (tracker_id, user_id) values (1,2);
Insert into booktracker.trackers (tracker_id, user_id) values (2,1);
Insert into booktracker.trackers (tracker_id, user_id) values (3,3);

-- insert books
Insert into booktracker.books (book_id, book_name, author) values (1, 'Enders Game', 'Scott Card');
Insert into booktracker.books (book_id, book_name, author) values (2,'Harry Potter', 'J.K. Rowling');
Insert into booktracker.books (book_id, book_name, author) values (3, 'The Great Gatsby', 'F. Scott Fitzgerald');
Insert into booktracker.books (book_id, book_name, author) values (4,'Things Fall Apart', 'Chinua Achebe');
Insert into booktracker.books (book_id, book_name, author) values (5, '1984', 'George Orwell');
Insert into booktracker.books (book_id, book_name, author) values (6,'Beloved', 'Toni Morrison');
Insert into booktracker.books (book_id, book_name, author) values (7, "The Handmaid's Tail", 'Margaret ATwood');
Insert into booktracker.books (book_id, book_name, author) values (8,'Wuthering Heights', 'Emily Bronte');
Insert into booktracker.books (book_id, book_name, author) values (9, 'The Catcher in the Rye', 'J.D. Salinger');
Insert into booktracker.books (book_id, book_name, author) values (10,'Great Expectations', 'Charles Dickens');

-- insert books_trackers
Insert into booktracker.books_trackers (book_id, tracker_id, completion) values (1, 1, 'added to list');
Insert into booktracker.books_trackers (book_id, tracker_id, completion) values (2, 2, 'started');
Insert into booktracker.books_trackers (book_id, tracker_id, completion) values (3, 3, 'completed');

