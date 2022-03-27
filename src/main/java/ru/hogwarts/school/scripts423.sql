-- Составить первый JOIN-запрос, чтобы получить информацию обо всех студентах (имя и возраст) с названиями факультетов.
SELECT student.name, student.age, faculty.name
FROM student
INNER JOIN faculty ON student.faculty_id = faculty.id;

-- Составить второй JOIN-запрос, чтобы получить только тех студентов, у которых есть аватарки.
SELECT student.name, student.age, avatar.data
FROM avatar
INNER JOIN student ON student.id = avatar.student_id;
