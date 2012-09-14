# Create schema

# --- !Ups
CREATE TABLE "INSTAGRAM_USER"
(
  fullname character varying(100) NOT NULL,
  username character varying(20) NOT NULL,
  id numeric(15,0) NOT NULL,
  picture character varying(255) NOT NULL,
  CONSTRAINT user_pkey PRIMARY KEY (id),
  CONSTRAINT unq_username UNIQUE (username)
);

# --- !Downs
DROP TABLE "INSTAGRAM_USER";