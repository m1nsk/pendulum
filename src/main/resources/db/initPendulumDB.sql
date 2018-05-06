DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS channel_device;
DROP TABLE IF EXISTS channels;
DROP TABLE IF EXISTS devices;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;
DROP SEQUENCE IF EXISTS message_seq;

CREATE SEQUENCE global_seq START 100000;
CREATE SEQUENCE message_seq START 100000;


CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE devices
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR(64),
  user_id     INTEGER   NOT NULL,
  serial      INTEGER NOT NULL ,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE channels
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR(64),
  user_id     INTEGER   NOT NULL,
  current_message INTEGER,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE messages
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('message_seq'),
  message     VARCHAR(1000)   NOT NULL,
  channel_id  INTEGER NOT NULL,
  user_id     INTEGER NOT NULL,
  date        TIMESTAMP DEFAULT NOW(),
  FOREIGN KEY (channel_id) REFERENCES channels (id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE channel_device (
  channel_id   INTEGER NOT NULL,
  device_id    INTEGER NOT NULL,
  PRIMARY KEY (channel_id, device_id),
  FOREIGN KEY (channel_id) REFERENCES channels (id) ON DELETE CASCADE,
  FOREIGN KEY (device_id) REFERENCES devices (id) ON DELETE CASCADE
);