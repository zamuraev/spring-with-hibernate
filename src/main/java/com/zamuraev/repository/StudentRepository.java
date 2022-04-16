package com.zamuraev.repository;

import com.zamuraev.entity.Course;
import com.zamuraev.entity.Passport;
import com.zamuraev.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StudentRepository {

    private final EntityManager entityManager;

    public Student findByID(Long id) {
        return entityManager.find(Student.class, id);
    }

    public void deleteById(Long id) {
        Student student = findByID(id);
        entityManager.remove(student);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }
        return student;
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("S123456");
        entityManager.persist(passport);
        Student student = new Student("Sergey");
        student.setPassport(passport);
        entityManager.persist(student);
    }

    public void insertStudentAndCourse(){
        Student student = new Student("Jack");
        Course course = new Course("Microservices in 100 Steps");
        entityManager.persist(student);
        entityManager.persist(course);
        student.getCourses().add(course);
        course.getStudents().add(student);
        entityManager.persist(student);
    }

}
