
CREATE TABLE public.users (
	id serial NOT NULL,
	name varchar(100) NOT NULL unique,
	mail varchar(100) NULL,
	password varchar(100) NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE public.keyWords (
	id serial NOT NULL,
	word varchar(100) NOT NULL UNIQUE,
	CONSTRAINT keyWords_pk PRIMARY KEY (id)
);

CREATE TABLE public.users_keyWords (
	id serial NOT NULL,
	"user" bigint NOT NULL,
	word bigint NOT NULL,
	CONSTRAINT users_keyWord_pk PRIMARY KEY (id),
	CONSTRAINT users_keyWords_fk0 FOREIGN KEY ("user") REFERENCES users(id) on update cascade,
	CONSTRAINT users_keyWords_fk1 FOREIGN KEY (word) REFERENCES keyWords(id) on update cascade
);

CREATE SEQUENCE users_seq START 1 OWNED BY users.id;
CREATE SEQUENCE keyWords_seq START 1 OWNED BY keyWords.id;
CREATE SEQUENCE users_keyWords_seq START 1 OWNED BY users_keyWords.id;


