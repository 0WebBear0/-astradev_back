
CREATE TABLE public.users (
	id serial NOT NULL,
	name varchar(100) NOT NULL unique,
	mail varchar(100) NULL,
	password varchar(100) NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE public.users_keyWords (
	id serial NOT NULL,
	"user" integer NOT NULL,
	word integer NOT NULL,
	CONSTRAINT users_keyWord_pk PRIMARY KEY (id)
);

CREATE TABLE public.keyWords (
	id serial NOT NULL,
	word varchar(100) NOT NULL UNIQUE,
	CONSTRAINT keyWords_pk PRIMARY KEY (id)
);

CREATE SEQUENCE users_seq START 1 OWNED BY users.id;
CREATE SEQUENCE keyWords_seq START 1 OWNED BY keyWords.id;
CREATE SEQUENCE users_keyWords_seq START 1 OWNED BY users_keyWords.id;
