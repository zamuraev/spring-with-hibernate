INSERT INTO course(id, name, created_date, last_updated_date) VALUES (1001, 'JPA', localtimestamp,localtimestamp);
INSERT INTO course(id, name, created_date, last_updated_date) VALUES (1002, 'Spring', localtimestamp,localtimestamp);
INSERT INTO course(id, name, created_date, last_updated_date) VALUES (1003, 'Hibernate', localtimestamp,localtimestamp);

INSERT INTO student (id, name) VALUES (2001, 'Ranga', '');
INSERT INTO student (id, name) VALUES (2002, 'Adam', '');
INSERT INTO student (id, name) VALUES (2003, 'Sergey', '');

INSERT INTO passport(id, number) VALUES (4001, 'E12456');
INSERT INTO passport(id, number) VALUES (4002, 'N12456');
INSERT INTO passport(id, number) VALUES (4003, 'L12456');

INSERT INTO review(id, rating, description, course_id) VALUES (5001, 'FIVE','Great Course', 1001);
INSERT INTO review(id, rating, description, course_id) VALUES (5002, 'FOUR','Wonderful Course', 1002);
INSERT INTO review(id, rating, description, course_id) VALUES (5003, 'THREE','Awesome Course', 1002);

INSERT INTO student_course(student_id,course_id) VALUES (2001,1001);
INSERT INTO student_course(student_id,course_id) VALUES (2002,1001);
INSERT INTO student_course(student_id,course_id) VALUES (2003,1001);
INSERT INTO student_course(student_id,course_id) VALUES (2001,1003);
