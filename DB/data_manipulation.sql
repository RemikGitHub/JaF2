SELECT * FROM users;
SELECT * FROM user_role;
SELECT * FROM tokens;
SELECT * FROM posts;
SELECT * FROM comments;

DELETE FROM users;
DELETE FROM user_role;
DELETE FROM tokens;
DELETE FROM posts;
DELETE FROM comments;

SET SQL_SAFE_UPDATES = 0;
SET SQL_SAFE_UPDATES = 1;

INSERT INTO posts (title, content, post_category, published_date_time, user_username) VALUES('Tytul','Pellentesque tempus aliquam nibh, ultricies iaculis ligula volutpat id. Donec turpis nulla, porttitor ut turpis non, cursus mollis ligula. Nulla quis accumsan dolor. Praesent fringilla faucibus orci ut aliquam. Phasellus consequat ipsum eu elit elementum tempus. In sodales auctor eros, ac sagittis metus semper vitae. Donec finibus eleifend risus, eu aliquet diam dapibus nec. Maecenas iaculis congue ligula, vel tempor mi sagittis ac. Fusce rhoncus fringilla ligula, ac consectetur orci. Donec eget posuere orci, in consectetur massa. Nulla eu rutrum diam. Donec elit ipsum, pharetra eget tellus ut, dapibus vehicula lectus. Maecenas odio augue, vehicula eu accumsan quis, semper at ex. Quisque consequat non erat eget varius. Morbi hendrerit dolor arcu.','FRONTEND',now(),'marik1234');
SELECT * FROM posts;

UPDATE users
SET email = 'mailmail@mail.com'
WHERE username = 'user11';