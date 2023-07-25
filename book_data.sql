drop database if exists booktracker;

create database booktracker;
use booktracker;

##################
#   USERS  #
##################

create table users (
	user_id int auto_increment,
    username varchar(30) unique,
    password varchar(15),
    primary key (user_id)
);

alter table users auto_increment = 1000; 
INSERT INTO users values ();


####################
#   BOOKS   #
####################
