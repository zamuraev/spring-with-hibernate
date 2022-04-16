package com.zamuraev;

import com.zamuraev.entity.Course;
import com.zamuraev.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@SpringBootTest
@Slf4j
public class JPQLTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void jpql_courses_without_students(){
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where c.students is empty", Course.class);
        List<Course> resultList = query.getResultList();
        log.info("Results -> {}", resultList);
    }

    @Test
    public void jpql_courses_with_atleast_2_students(){
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where size(c.students)>=2", Course.class);
        List<Course> resultList = query.getResultList();
        log.info("Results -> {}", resultList);
    }

    @Test
    public void jpql_courses_ordered_by_students(){
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c order by size(c.students) desc", Course.class);
        List<Course> resultList = query.getResultList();
        log.info("Results -> {}", resultList);
    }

    @Test
    public void jpql_students_with_passports_in_a_certain_pattern(){
        TypedQuery<Student> query = entityManager.createQuery("Select s from Student s where s.passport.number like '%123%'", Student.class);
        List<Student> resultList = query.getResultList();
        log.info("Results -> {}", resultList);
    }

    //like
    //BETWEEN 100 and 1000
    //IS NULL
    //upper, lower, trim, length

    //JOIN => Select c,s from Course c JOIN c.students s
    //LEFT JOIN => Select c,s from Course c LEFT JOIN c.students s
    //CROSS JOIN => Select c,s from Course c, Student s
    @Test
    public void join() {
       Query query = entityManager.createQuery("Select c,s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        log.info("Results -> {}", resultList.size());
        for(Object[] result: resultList) {
            log.info("Course{} Student{}", result[0], result[1]);
        }
    }

    @Test
    public void left_join() {
        Query query = entityManager.createQuery("Select c,s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        log.info("Results -> {}", resultList.size());
        for(Object[] result: resultList) {
            log.info("Course{} Student{}", result[0], result[1]);
        }
    }

    @Test
    public void cross_join() {
        Query query = entityManager.createQuery("Select c,s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        log.info("Results -> {}", resultList.size());
        for(Object[] result: resultList) {
            log.info("Course{} Student{}", result[0], result[1]);
        }
    }

   //Criteria Builder

    @Test
    public void criteria_builder_basic(){
        //SELECT c FROM Course c
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);

        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        log.info("Typed Query -> {}", resultList);
    }

    @Test
    public void all_courses_having_100Steps(){
        //SELECT c FROM Course c where name like '%100 Steps'
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        Predicate like = cb.like(courseRoot.get("name"), "100 Steps");
        cq.where(like);
        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        log.info("Typed Query -> {}", resultList);
    }

    @Test
    public void all_courses_without_students() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
        cq.where(studentsIsEmpty);
        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        log.info("Typed Query -> {}", resultList);
    }

    @Test
    public void join_with_criteria_builder(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        Join<Object, Object> join = courseRoot.join("students");
        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        log.info("Typed Query -> {}", resultList);
    }

    @Test
    public void left_join_with_criteria_builder(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        Join<Object, Object> join = courseRoot.join("students",JoinType.LEFT);
        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        log.info("Typed Query -> {}", resultList);
    }

}
