drop database tema;

create database if not exists tema;

use tema;

create table student(
student_id int PRIMARY KEY,
name varchar(40),
birth_date date,
address varchar(40));

create table course(
course_id int PRIMARY KEY,
name varchar(40),
teacher varchar(40),
study_year int);

create table enrollment(
student_id int,
course_id int,
constraint fk_student_enrollment_student_id FOREIGN KEY (student_id) references student(student_id) on delete restrict on update cascade,
constraint fk_course_enrollment_course_id FOREIGN KEY (course_id) references course(course_id) on delete restrict on update cascade);

insert into student values
(1,"Narita Catalin-Ioan",'1995-07-10',"Bucium_Sat 165"),
(2,"Milas Bogdan Adrian",'1995-09-28',"ZALAU"),
(3,"Coca Sergiu",'1995-12-27',"ZALAU"),
(4,"Cosma Dragos",'1995-10-10',"ZALAU");

insert into course values
(1,"Software Design","Mihaela Dinsoreanu",3),
(2,"Analiza Matematica","Mircea Ivan",1),
(3,"Circuite Analogice si Numerice","Adrian Peculea",2),
(4,"Arhitectura Calculatoarelor","Florin Oniga",2);

insert into enrollment values
(1,1),
(1,4),
(2,3),
(2,2),
(4,2);
