CREATE TABLE persons
(
  id           SERIAL                    PRIMARY KEY,
  first_name   VARCHAR(50)               NOT NULL,
  middle_name  VARCHAR(50)               NULL,
  last_name    VARCHAR(50)               NOT NULL,
  address      VARCHAR(255)              NULL,
  email        VARCHAR(255)              NULL,
  gender       VARCHAR(6) DEFAULT 'Male' NOT NULL,
  is_deleted    BOOLEAN DEFAULT FALSE     NOT NULL,
  delete_reason VARCHAR(255)              NULL,
  deleted_at    TIMESTAMP                 NULL,
  created_at    TIMESTAMP                 NOT NULL,
  updated_at    TIMESTAMP                 NULL
);