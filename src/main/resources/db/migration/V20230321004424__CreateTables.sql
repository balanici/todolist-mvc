CREATE EXTENSION IF NOT EXISTS "pgcrypto"; -- creates extension
--CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; -- don't need this extension

--DROP TABLE lists;

CREATE TABLE lists (
	uuid uuid NOT NULL,
	description varchar(255) NULL,
	"name" varchar(255) NULL,
	CONSTRAINT lists_pkey PRIMARY KEY (uuid)
);


--DROP TABLE items;

CREATE TABLE items (
	uuid uuid NOT NULL,
	description varchar(255) NULL,
	"name" varchar(255) NULL,
	list_entity_uuid uuid NULL,
	CONSTRAINT items_pkey PRIMARY KEY (uuid)
);


-- items foreign keys

ALTER TABLE items ADD CONSTRAINT fk_items__lists_uuid FOREIGN KEY (list_entity_uuid) REFERENCES lists(uuid);

