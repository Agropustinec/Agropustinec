-- addNewMessage=
INSERT INTO messages (username, `body`, created_at, message_status_id)
VALUES (?, ?, NOW(), ?);

-- getLastMessages=
SELECT m.id, m.username, m.created_at, ms.status_name
FROM messages AS m
         JOIN message_statuses AS ms ON ms.id = m.message_status_id
ORDER BY id DESC
limit ?;

-- getUserByUsername=
SELECT u.username, r.role_name
FROM users AS u
         JOIN roles as r ON r.id = u.role_id
WHERE u.username = ?;

-- getStatusNameById=
SELECT ms.status_name
FROM message_statuses AS ms
WHERE ms.id = ?;

-- isLogged=
SELECT exists(
               SELECT t.message_status_id
               FROM (
                        SELECT m.message_status_id
                        FROM messages AS m
                        WHERE m.username = ?
                        ORDER BY created_at DESC, id DESC
                        LIMIT 1
                    ) AS t
               WHERE t.message_status_id != 4
           ) AS isLogged;


-- unKick=
DELETE
FROM messages
WHERE body = ?
  AND message_status_id = 3;

-- getRole=
SELECT r.role_name
FROM users AS u
         LEFT JOIN roles AS r ON u.role_id = r.id
WHERE u.username = ?;

-- isKicked=
SELECT message_status_id = 3 AS isKicked
FROM messages AS m
WHERE m.body = ?
ORDER BY created_at DESC,
         id DESC
LIMIT 1;

-- createUser=
INSERT INTO users (username, role_id)
VALUES (?, ?);

-- getAllLogged=
SELECT t1.username, r.role_name
FROM messages t1
         LEFT JOIN messages t2
                   on
                       (t1.username = t2.username
                           AND t1.created_at < t2.created_at
                           AND t2.message_status_id = 4)
         LEFT JOIN users AS u ON u.username = t1.username
         LEFT JOIN roles AS r ON r.id = u.role_id
WHERE t1.message_status_id = 1
  AND t2.created_at is null
ORDER BY t1.username;

-- getAllKicked=
SELECT u.username, r.role_name
FROM users AS u
         INNER JOIN messages AS m
         LEFT JOIN roles AS r ON u.role_id = r.id
WHERE m.message_status_id = 3
  AND m.body = u.username
ORDER BY m.username;
