--category
INSERT INTO dbsticast.category (id, name) VALUES (1, 'sport');
INSERT INTO dbsticast.category (id, name) VALUES (2, 'politics');
INSERT INTO dbsticast.category (id, name) VALUES (3, 'economics');
INSERT INTO dbsticast.category (id, name) VALUES (4, 'crypto');

--user
INSERT INTO dbsticast.user (id, budget, email, password, username, status) VALUES (1, 0, 'test@test.it', '$2a$10$vAEMmhDJPdYGeSyrWkt7ZONe5KCwyPeubFGYHqbiGveeKZ6loxa4u', 'pippo', 'active');
INSERT INTO dbsticast.user (id, budget, email, password, username, status) VALUES (2, 10, 'pippo@pippo.it', '$2a$10$vAEMmhDJPdYGeSyrWkt7ZONe5KCwyPeubFGYHqbiGveeKZ6loxa4u', 'pippod', 'active');

--question
INSERT INTO dbsticast.question (id, followers, forecasts, no_share, text, yes_share, status, creation_date, expiration_date) VALUES (1, 22, 243, 100, 'Will bitcoin succeed in exceeding the 10k$ threshold by 2018?', 143, 'open', '2021-07-01', '2021-07-22');
INSERT INTO dbsticast.question (id, followers, forecasts, no_share, text, yes_share, status, creation_date, expiration_date) VALUES (2, 4, 43, 3, 'Will SSC Napoli win Serie A 2018/19?', 40, 'open', '2021-07-02', '2021-07-23');
INSERT INTO dbsticast.question (id, followers, forecasts, no_share, text, yes_share, status, creation_date, expiration_date) VALUES (3, 12, 89, 50, 'Will Vettel win the F1 World Championship 2018?', 49, 'closed', '2021-07-03', '2021-07-24');
INSERT INTO dbsticast.question (id, followers, forecasts, no_share, text, yes_share, status, creation_date, expiration_date) VALUES (4, 5, 56, 20, 'Will Ethereum be able to return to $1000 by December?', 36, 'open', '2021-07-04', '2021-07-25');
INSERT INTO dbsticast.question (id, followers, forecasts, no_share, text, yes_share, status, creation_date, expiration_date) VALUES (5, 6, 66, 33, 'Will Italy GDP grow in the third quarter?', 33, 'open', '2021-07-05', '2021-07-26');

--questions_categories
INSERT INTO dbsticast.questions_categories (question_id, category_id) VALUES (1, 4);
INSERT INTO dbsticast.questions_categories (question_id, category_id) VALUES (2, 1);
INSERT INTO dbsticast.questions_categories (question_id, category_id) VALUES (3, 1);
INSERT INTO dbsticast.questions_categories (question_id, category_id) VALUES (4, 4);
INSERT INTO dbsticast.questions_categories (question_id, category_id) VALUES (5, 2);

--role
INSERT INTO dbsticast.role (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO dbsticast.role (id, name) VALUES (2, 'ROLE_ADMIN');

--users_roles
INSERT INTO dbsticast.users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO dbsticast.users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO dbsticast.users_roles (user_id, role_id) VALUES (2, 1);


--user_question_details
INSERT INTO dbsticast.user_question_details (question_id, user_id, is_followed, no_share_quantity, yes_share_quantity) VALUES (1, 1, true, 10, 10);
INSERT INTO dbsticast.user_question_details (question_id, user_id, is_followed, no_share_quantity, yes_share_quantity) VALUES (1, 2, false, 10, 10);

--users_details
INSERT INTO dbsticast.user_details (user_id, closed_question_notification, comment_notification, created_at, is_2fa_active, last_ip, last_login) VALUES (2, true, true, '2021-07-21 22:17:30', true, '127.0.0.0', '2021-07-21 22:17:47');