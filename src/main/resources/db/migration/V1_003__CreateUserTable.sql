CREATE TABLE users (
  id           SERIAL                    PRIMARY KEY,
  person_id    INT                       NOT NULL,
  role_id      INT                       NOT NULL,
  user_name    VARCHAR(50)               NOT NULL,
  password     VARCHAR(255)              NOT NULL,
  salt         TEXT                      NOT NULL,
  is_active    BOOLEAN DEFAULT TRUE      NOT NULL,
  is_deleted    BOOLEAN DEFAULT FALSE    NOT NULL,
  delete_reason VARCHAR(255)             NULL,
  deleted_at    TIMESTAMP                NULL,
  created_at    TIMESTAMP                NOT NULL,
  updated_at    TIMESTAMP                NULL
);

ALTER TABLE users
ADD CONSTRAINT users_persons_fk
FOREIGN KEY (person_id) REFERENCES persons (id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE users
ADD CONSTRAINT roles_persons_fk
FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE;