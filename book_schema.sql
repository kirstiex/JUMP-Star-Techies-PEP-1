drop database if exists booktracker;

create database booktracker;
use booktracker;

##################
#   USERS  #
##################

create table users (
	user_id int NOT NULL auto_increment,
    username varchar(30) unique NOT NULL,
    password varchar(15) NOT NULL,
    primary key (user_id),
    status enum('user', 'admin') NOT NULL
);

##################
#   TRACKERS  #
##################

CREATE TABLE trackers (
    tracker_id INT NOT NULL auto_increment,
    user_id INT,
    CONSTRAINT fk_trackers
        FOREIGN KEY(user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE,
    CONSTRAINT pk_trackers
        PRIMARY KEY(tracker_id)
);

##################
#   BOOKS  #
##################

CREATE TABLE books (
    book_id INT NOT NULL auto_increment,
    book_name VARCHAR(30) NOT NULL,
    author VARCHAR(30) NOT NULL,
    CONSTRAINT pk_books
        PRIMARY KEY(book_id)
);

##################
#   BOOKS_TRACKERS  #
##################

CREATE TABLE books_trackers (
    book_id INT NOT NULL,
    tracker_id INT NOT NULL,
    completion enum('not completed', 'in-progress', 'completed') NOT NULL,
    CONSTRAINT pk_books_trackers
        PRIMARY KEY(book_id, tracker_id),
    CONSTRAINT fk_books_trackers
        FOREIGN KEY(book_id)
        REFERENCES books(book_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_trackers_books
        FOREIGN KEY(tracker_id)
        REFERENCES trackers(tracker_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

alter table users auto_increment = 1000;
INSERT INTO users values ();
