DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM devices;
DELETE FROM channels;
DELETE FROM messages;
DELETE FROM channel_device;

ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE message_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO devices (id, user_id, serial) VALUES
  (100002, 100000, 1),
  (100003, 100000, 2),
  (100004, 100001, 3);

INSERT INTO channels (id, user_id, current_message) VALUES
  (100005, 100000, 1),
  (100006, 100000, 2),
  (100007, 100001, 3);

INSERT INTO messages (id, message, channel_id, user_id) VALUES
  (100000, '{"key":"empty1"}', 100005, 100000),
  (100001, '{"key":"empty2"}', 100006, 100001),
  (100002, '{"key":"empty3"}', 100007, 100001),
  (100003, '{"key":"empty4"}', 100005, 100000),
  (100004, '{"key":"empty5"}', 100005, 100000);

INSERT INTO channel_device (channel_id, device_id) VALUES
  (100005, 100002),
  (100005, 100003),
  (100006, 100004);
