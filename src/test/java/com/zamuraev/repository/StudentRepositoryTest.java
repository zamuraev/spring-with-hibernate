package com.zamuraev.repository;

import com.zamuraev.entity.Address;
import com.zamuraev.entity.Passport;
import com.zamuraev.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void retrieveStudentAndPassportDetails(){
        studentRepository.saveStudentWithPassport();
    }

    @Test
    @Transactional
    public void someOperationUnderPersistenceContext() {
        Student student = entityManager.find(Student.class, 2L);
        Passport passport = student.getPassport();
        passport.setNumber("R123456");
        student.setName("Sergey");
        entityManager.merge(passport);
        entityManager.merge(student);
        log.info("passport -> {}",passport );
        log.info("student -> {}",student );
        entityManager.flush();
    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudent(){
        Passport passport = entityManager.find(Passport.class, 4001L);
        log.info("passport -> {}", passport);
        log.info("student -> {}", passport.getStudent());
    }

    @Test
    @Transactional
    public void retrieveStudentAndCourses(){
        Student student = entityManager.find(Student.class, 2001L);
        log.info("student -> {}", student);
        log.info("courses -> {}", student.getCourses());
    }


    @Test
    @Transactional
    public void setAddressDetails()
    {
        Student student = entityManager.find(Student.class, 2001L);
        student.setAddress(new Address("No 101", "Some Street", "Hyderabad"));
        entityManager.flush();
        log.info("student -> {}", student);
        log.info("passport -> {}", student.getPassport());
    }

}
