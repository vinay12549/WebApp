

CREATE TABLE users (
    mbid VARCHAR(36),
    username VARCHAR(256) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    first_name VARCHAR(256) NOT NULL,
    last_name VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL,
    mobile VARCHAR(256) NOT NULL,
    isactive BOOLEAN NOT NULL DEFAULT TRUE,
    roles VARCHAR(256) NOT NULL,
    metadata VARCHAR(1024) NULL,
    PRIMARY KEY (mbid)
);


INSERT INTO users (mbid, username, password, first_name, last_name, email, mobile, isactive, roles, metadata)
VALUES ('1003d263-9386-4c1c-9ccd-1b8acf0866b0', 'admin', '$2a$10$GsQsDUUvdEGOp1.NQFf5.euQ8lZvBzdkpm4Cq9y7QxnGtaMlh7LQu', 'admin', 'admin', 'admin@medblaze.com', '9999999999', true, 'ROLE_ADMIN', null);
