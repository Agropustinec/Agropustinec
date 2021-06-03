CREATE database IF NOT EXISTS chat;
USE chat;


CREATE table IF NOT EXISTS roles
(
    id          INT          NOT NULL primary key auto_increment,
    role_name   VARCHAR(30)  NOT NULL UNIQUE,
    description VARCHAR(100) NOT NULL
);

CREATE table IF NOT EXISTS users
(
    username VARCHAR(30) NOT NULL primary key,
    role_id  INT         NOT NULL,
    constraint `fk_users_to_roles` foreign key (role_id)
        references roles (id)
        ON delete NO ACTION
        ON update NO ACTION
);

CREATE table IF NOT EXISTS message_statuses
(
    id          INT          NOT NULL primary key auto_increment,
    status_name VARCHAR(15)  NOT NULL UNIQUE,
    description VARCHAR(100) NOT NULL
);

CREATE table IF NOT EXISTS messages
(
    id                INT           NOT NULL primary key auto_increment,
    username          VARCHAR(30)   NOT NULL,
    body              TEXT,
    created_at        TIMESTAMP     NOT NULL default now(),
    message_status_id INT           NOT NULL,
    constraint `fk_messages_to_users` foreign key (username)
        references users (username)
        ON delete NO ACTION
        ON update NO ACTION,
    constraint `fk_messages_to_message_statuses` foreign key (message_status_id)
        references message_statuses (id)
        ON delete NO ACTION
        ON update NO ACTION
);

alter table messages add index fk_messages_to_users (username);
alter table messages add index fk_messages_to_statuses (message_status_id);
alter table users add index fk_users_to_roles (role_id);
