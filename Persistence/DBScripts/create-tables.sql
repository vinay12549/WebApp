CREATE TABLE tenants (
    mbid VARCHAR(36),
    name VARCHAR(256) NOT NULL UNIQUE,
    description VARCHAR(256) NOT NULL,
    tenant_type VARCHAR(256) NOT NULL,
    owner_name VARCHAR(256) NOT NULL,
    owner_email VARCHAR(256) NOT NULL,
    isActive BOOLEAN NOT NULL DEFAULT FALSE,
    init BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (mbid)
);

CREATE TABLE metadata (
    mbid VARCHAR(36),
    host VARCHAR(256) NOT NULL DEFAULT 'localhost',
    port INTEGER NOT NULL DEFAULT 5432,
    username VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    tenant_id VARCHAR(36) NOT NULL UNIQUE REFERENCES tenants(mbid),
    PRIMARY KEY (mbid,tenant_id)
);

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

CREATE TABLE clients (
	mbid VARCHAR(36),
  	client_id VARCHAR(256) NOT NULL UNIQUE,
  	resource_ids VARCHAR(256) ,
  	client_secret VARCHAR(256) NOT NULL,
  	scopes VARCHAR(256) NOT NULL,
  	grant_types VARCHAR(256) NOT NULL,
  	redirect_uri VARCHAR(256),
  	authorities VARCHAR(256),
  	access_token_validity INTEGER NOT NULL DEFAULT 86400,
  	refresh_token_validity INTEGER,
  	additional_information VARCHAR(4096),
  	autoapprove VARCHAR(256) DEFAULT TRUE,
  	PRIMARY KEY (mbid)
);

INSERT INTO users (mbid, username, password, first_name, last_name, email, mobile, isactive, roles, metadata)
VALUES ('1003d263-9386-4c1c-9ccd-1b8acf086610', 'superadmin', '$2a$10$GsQsDUUvdEGOp1.NQFf5.euQ8lZvBzdkpm4Cq9y7QxnGtaMlh7LQu', 'super admin', 'super admin', 'superadmin@medblaze.com', '9999999999', true, 'ROLE_SUPER_ADMIN', null);