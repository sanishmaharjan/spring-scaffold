CREATE TABLE roles
(
  id           SERIAL                    PRIMARY KEY,
  role         VARCHAR(50)               UNIQUE NOT NULL,
  created_at    TIMESTAMP                 NOT NULL,
  updated_at    TIMESTAMP                 NULL
);

INSERT INTO roles(role, created_at) VALUES ('user', CURRENT_TIMESTAMP), ('admin', CURRENT_TIMESTAMP);