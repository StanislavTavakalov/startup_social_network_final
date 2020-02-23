CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE roles(
  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
  role_name VARCHAR(50) NOT NULL
);

CREATE TABLE business_roles(
  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
  role_name VARCHAR(30) NOT NULL
);

CREATE TABLE skills(
  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
  skill_name VARCHAR(20) NOT NULL
);

CREATE TABLE users(
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	login VARCHAR(30) NOT NULL,
	hashed_password VARCHAR(255) NOT NULL,
	salt VARCHAR(255) NOT NULL,
	id_role uuid NOT NULL,
	email VARCHAR(255) NOT NULL,
	CONSTRAINT FK_ROLE FOREIGN KEY (id_role) REFERENCES roles(id)
);

CREATE TABLE accounts(
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	birthday DATE NOT NULL,
	id_user uuid NOT NULL,
	CONSTRAINT FK_USER FOREIGN KEY (id_user) REFERENCES users(id)
);

CREATE TABLE contacts(
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	id_your_account uuid NOT NULL,
	id_contact_account uuid NOT NULL,
	CONSTRAINT FK_ID_YOUR_ACCOUNT FOREIGN KEY (id_your_account) REFERENCES accounts(id),
	CONSTRAINT FK_ID_CONTACT_ACCOUNT FOREIGN KEY (id_contact_account) REFERENCES accounts(id)
);

CREATE TABLE work_experience(
  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
  workplace VARCHAR(255) NOT NULL,
  start DATE NOT NULL,
  finish DATE,
  position VARCHAR(30),
  id_account uuid NOT NULL,
  CONSTRAINT FK_ID_ACCOUNT FOREIGN KEY (id_account) REFERENCES accounts(id)
);

CREATE TABLE resumes(
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    info TEXT NOT NULL,
    id_business_role uuid NOT NULL,
    id_account uuid NOT NULL,
    CONSTRAINT FK_ID_BUSINESS_ROLE FOREIGN KEY (id_business_role) REFERENCES business_roles(id),
    CONSTRAINT FK_ID_ACCOUNT FOREIGN KEY (id_account) REFERENCES accounts(id)
);

CREATE TABLE resumes_skills(
  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
  id_resume uuid NOT NULL,
  id_skill uuid NOT NULL,
  CONSTRAINT FK_ID_RESUME FOREIGN KEY (id_resume) REFERENCES resumes(id),
  CONSTRAINT FK_ID_SKILL FOREIGN KEY (id_skill) REFERENCES skills(id)
);

CREATE TABLE educations(
  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
  institution VARCHAR(255) NOT NULL,
  completion_year INTEGER,
  id_account uuid NOT NULL,
  CONSTRAINT FK_ID_ACCOUNT FOREIGN KEY (id_account) REFERENCES accounts(id)
);

CREATE TABLE startups(
  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
  id_creator uuid NOT NULL,
  idea TEXT NOT NULL,
  sum_of_investment INTEGER NOT NULL,
  about_project TEXT NOT NULL,
  business_plan TEXT NOT NULL,
  startup_name VARCHAR(255) NOT NULL,
  CONSTRAINT FK_ID_CREATOR FOREIGN KEY (id_creator) REFERENCES accounts(id)
);

CREATE TABLE startups_resumes(
  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
  id_resume uuid NOT NULL,
  id_startup uuid NOT NULL,
  status VARCHAR(255) NOT NULL,
  CONSTRAINT FK_ID_RESUME FOREIGN KEY (id_resume) REFERENCES resumes(id),
  CONSTRAINT FK_ID_STARTUP FOREIGN KEY (id_startup) REFERENCES startups(id)
);

CREATE TABLE startups_roles(
  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
  role_enum VARCHAR(255) NOT NULL,
  id_startup uuid NOT NULL,
  id_account uuid NOT NULL,
  CONSTRAINT FK_ID_STARTUP FOREIGN KEY (id_startup) REFERENCES startups(id),
  CONSTRAINT FK_ID_ACCOUNT FOREIGN KEY (id_account) REFERENCES accounts(id)
);

CREATE TABLE favorites(
  id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
  favorite_types VARCHAR(255) NOT NULL,
  id_account uuid NOT NULL,
  id_object uuid NOT NULL,
  CONSTRAINT FK_ID_ACCOUNT FOREIGN KEY (id_account) REFERENCES accounts(id)
);