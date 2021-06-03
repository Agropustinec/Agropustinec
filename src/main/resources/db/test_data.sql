use chat;

INSERT INTO roles(role_name, description)
VALUES
('admin', 'Admin role'),
('user', 'User role');

INSERT INTO users(username, role_id)
VALUES
('Denis', 1),
('Egor', 2),
('Vlad', 2),
('Dima', 2);

INSERT INTO message_statuses(status_name, description)
VALUES
('LOGIN', 'User logged into chat'),
('MESSAGE', 'User left a message'),
('KICK', 'The user threw another user out of the chat'),
('LOGOUT', 'The user has left the chat');

INSERT INTO messages(username, body, message_status_id)
VALUES ('Denis', '', 1),
       ('Egor', '', 1),
       ('Vlad', '', 1),
       ('Dima', '', 1),
       ('Denis', 'Hello, world)', 2),
       ('Egor', 'Hi', 2),
       ('Vlad', 'I am hear', 2),
       ('Dima', 'Hi, everyone', 2),
       ('Dima', 'Bye)', 2),
       ('Egor', 'Dima', 3),
       ('Dima', '', 4);
UPDATE messages SET created_at = DATE_ADD(created_at, INTERVAL id MINUTE);
