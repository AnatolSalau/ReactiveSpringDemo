CREATE TABLE IF NOT EXISTS students
(   id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(255) NOT NULL,
    registered_on BIGINT NOT NULL,
    status INT NOT NULL,
    version BIGINT
);

CREATE TABLE IF NOT EXISTS courses
(   id BIGSERIAL PRIMARY KEY,
    course_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS coursework
(   id BIGSERIAL PRIMARY KEY,
    student_id BIGINT NOT NULL REFERENCES students(id),
    course_id BIGINT NOT NULL REFERENCES courses(id),
    mark INT NOT NULL
);


