CREATE TABLE IF NOT EXISTS students
(   id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    registedon BIGINT NOT NULL,
    status INT NOT NULL
);

CREATE TABLE IF NOT EXISTS courses
(   id BIGINT PRIMARY KEY,
    coursename VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS coursework
(   id BIGINT PRIMARY KEY,
    student_id BIGINT NOT NULL REFERENCES students(id),
    course_id BIGINT NOT NULL REFERENCES courses(id),
    mark INT NOT NULL
);


