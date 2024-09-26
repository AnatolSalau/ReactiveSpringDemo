

INSERT INTO public.students (id, name, registedon, status) VALUES
                                                               (1, 'Ivan', 1, 1),
                                                               (2, 'Petr', 2, 1);

INSERT INTO public.courses (id, coursename) VALUES
                                                (1, 'Math'),
                                                (2, 'English'),
                                                (3, 'Physics'),
                                                (4, 'Chemistry'),
                                                (5, 'Biology');

INSERT INTO public.coursework (id, student_id, course_id, mark) VALUES
                                                                    (1, 1, 1, 5),
                                                                    (2, 1, 2, 4),
                                                                    (3, 1, 3, 3),
                                                                    (4, 1, 4, 2),
                                                                    (5, 1, 5, 1),
                                                                    (6, 2, 1, 5),
                                                                    (7, 2, 2, 4),
                                                                    (8, 2, 3, 3),
                                                                    (9, 2, 4, 2);