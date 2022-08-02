INSERT INTO ROLES VALUES ('ADMIN'), ('FREELANCER'), ('RECRUITER');

INSERT INTO RECRUITER (id, business_name)
VALUES (1, 'buss1');
INSERT INTO RECRUITER (id, business_name)
VALUES (2, 'buss2');
INSERT INTO RECRUITER (id, business_name)
VALUES (3, 'buss3');

INSERT INTO FREELANCER (id, jobs_finished, rating, total_income)
VALUES (1, 30, 4.9, 4250.00);
INSERT INTO FREELANCER (id, jobs_finished, rating, total_income)
VALUES (2, 60, 8.9, 12995.00);
INSERT INTO FREELANCER (id, jobs_finished, rating, total_income)
VALUES (3, 15, 6.5, 3590.00);

INSERT INTO USERS (username, password, name, surname, email_address, phone_number, freelancer_id, recruiter_id, role_name)
VALUES ('user', '{bcrypt}$2a$12$hTVnqHiwPKLEkjnfKVcV/etmu9hUSo3YSIaESEgDlWC980an9emGO', 'Darius', 'Go', 'dariusg@mail.com', '+370 333 35855', null, null, 'ADMIN');
INSERT INTO USERS (username, password, name, surname, email_address, phone_number, freelancer_id, recruiter_id, role_name)
VALUES ('business1', '{bcrypt}$2a$12$7mNhA5LRDpfoVEbZNNSKTORHXqv7nkCfL8VSap6VkVQ9wwXU.ZiF6', 'Darius', 'G', 'dariusg@mail.com', '+370 333 35855', 1, null, 'FREELANCER');
INSERT INTO USERS (username, password, name, surname, email_address, phone_number, freelancer_id, recruiter_id, role_name)
VALUES ('business2', '{bcrypt}$2a$12$kTijxFevBWE6XUmCiqRIfezImr2LeUA0jQTUirp4FSVzo3dsfdTtC', 'Saulius', 'S', 'sausasu@mail.com', '+370 112 12345', 2, null, 'FREELANCER');
INSERT INTO USERS (username, password, name, surname, email_address, phone_number, freelancer_id, recruiter_id, role_name)
VALUES ('business3', '{bcrypt}$2a$12$RvhEcm6xXoc0lTfB65httOVFYf1XFXAu8M1zh04Qm44Hkq1OTFjf6', 'Joana', 'J', 'janbej@mail.com', '+370 568 22895', null, 1, 'RECRUITER');
INSERT INTO USERS (username, password, name, surname, email_address, phone_number, freelancer_id, recruiter_id, role_name)
VALUES ('business4', '{bcrypt}$2a$12$j53hVuqgPxEEGHyIwUvlIuP7OS28xsi9.bx8eToCLyEB2NncFmiPa', 'Gitana', 'Z', 'zerog@gmail.com', '+370 552 05874', 3, null, 'FREELANCER');
INSERT INTO USERS (username, password, name, surname, email_address, phone_number, freelancer_id, recruiter_id, role_name)
VALUES ('business5', '{bcrypt}$2a$12$zvRYIg6I7nyxVWKT05vfm.43WhQT9IVD3RiXhuHEaeqMih7f6BtDe', 'Kristina', 'K', 'kritti@mail.com', '+370 152 00256', null, 2, 'RECRUITER');
INSERT INTO USERS (username, password, name, surname, email_address, phone_number, freelancer_id, recruiter_id, role_name)
VALUES ('business6', '{bcrypt}$2a$12$4yC.0Uix4AsG8W.5fHfTyuf1u8Pu6IrVrNuZNOS7.wOa74.QlByUq', 'Svetlana', 'K', 'svet@gmail.com', '+370 655 00112', null, 3, 'RECRUITER');

INSERT INTO JOB_DETAILS (description, salary, id,)
VALUES ('Very good job', 2500.00, 1);
INSERT INTO JOB (deadline, details_id, recruiter_id, freelancer_id, job_title, job_type, jobid)
VALUES ('2022-05-01', 1, 1, 1, 'FasJob', 'Java', '00000000-0000-0000-0000-000000000001');
INSERT INTO JOB_DETAILS (description, salary, id)
VALUES ('Very good job', 1500.00, 2);
INSERT INTO JOB (deadline, details_id, recruiter_id, freelancer_id, job_title, job_type, jobid)
VALUES ('2022-08-01', 2, 2, null, 'FasterJob', 'Kotlin', '00000000-0000-0000-0000-000000000002');
INSERT INTO JOB_DETAILS (description, salary, id)
VALUES ('Very good job', 2200.00, 3);
INSERT INTO JOB (deadline, details_id, recruiter_id, freelancer_id, job_title, job_type, jobid)
VALUES ('2022-07-01', 3, 1, null, 'FastestJob', 'Maven', '00000000-0000-0000-0000-000000000003');
INSERT INTO JOB_DETAILS (description, salary, id)
VALUES ('Very good job', 4200.00, 4);
INSERT INTO JOB (deadline, details_id, recruiter_id, freelancer_id, job_title, job_type, jobid)
VALUES ('2022-09-01', 4, 3, 1, 'FSlowJob', 'C++', '00000000-0000-0000-0000-000000000004');
INSERT INTO JOB_DETAILS (description, salary, id)
VALUES ('Very good job', 900.00, 5);
INSERT INTO JOB (deadline, details_id, recruiter_id, freelancer_id, job_title, job_type, jobid)
VALUES ('2022-11-01', 5, 2, 3, 'VerySlowJob', 'C#', '00000000-0000-0000-0000-000000000005');
INSERT INTO JOB_DETAILS (description, salary, id)
VALUES ('Very good job', 1200.00, 6);
INSERT INTO JOB (deadline, details_id, recruiter_id, freelancer_id, job_title, job_type, jobid)
VALUES ('2022-10-01', 6, 2, null, 'FasJob', 'Java', '00000000-0000-0000-0000-000000000006');
INSERT INTO JOB_DETAILS (description, salary, id)
VALUES ('Very good job', 3350.00, 7);
INSERT INTO JOB (deadline, details_id, recruiter_id, freelancer_id, job_title, job_type, jobid)
VALUES ('2022-05-21', 7, 3, 2, 'FasJob', 'Python', '00000000-0000-0000-0000-000000000007');
INSERT INTO JOB_DETAILS (description, salary, id)
VALUES ('Very good job', 25100.00, 8);
INSERT INTO JOB (deadline, details_id, recruiter_id, freelancer_id, job_title, job_type, jobid)
VALUES ('2022-05-11', 8, 1, null, 'FasJob', 'JUnit', '00000000-0000-0000-0000-000000000008');
INSERT INTO JOB_DETAILS (description, salary, id)
VALUES ('Very good job', 200.00, 9);
INSERT INTO JOB (deadline, details_id, recruiter_id, freelancer_id, job_title, job_type, jobid)
VALUES ('2022-06-11', 9, 2, null, 'FasJob', 'Git', '00000000-0000-0000-0000-000000000009');
INSERT INTO JOB_DETAILS (description, salary, id)
VALUES ('Very good job', 2800.00, 10);
INSERT INTO JOB (deadline, details_id, recruiter_id, freelancer_id, job_title, job_type, jobid)
VALUES ('2022-02-11', 10, 1, null, 'FasJob', 'Kotlin', '00000000-0000-0000-0000-000000000010');
INSERT INTO JOB_DETAILS (description, salary, id)
VALUES ('Very good job', 3500.00, 11);
INSERT INTO JOB (deadline, details_id, recruiter_id, freelancer_id, job_title, job_type, jobid)
VALUES ('2023-04-01', 11, 2, 3, 'FasJob', 'Android', '00000000-0000-0000-0000-000000000011');