package com.zamuraev.repository;

import com.zamuraev.entity.Course;
import com.zamuraev.entity.Review;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CourseRepositoryTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    public void findById_basic() {
        Course course = courseRepository.findByID(1002L);
        assertEquals("Spring", course.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        courseRepository.deleteById(1003L);
        assertNull(courseRepository.findByID(1003L));
    }

    @Test
    @DirtiesContext
    public void save_basic() {

        Course course = courseRepository.findByID(1001L);
        assertEquals("JPA",course.getName());
        course.setName("updated Jpa");
        courseRepository.save(course);

        Course course1 = courseRepository.findByID(1001L);
        assertEquals("updated Jpa",course.getName());
    }

    @Test
    @DirtiesContext
    public void playWithEntityManager() {
        courseRepository.playWithEntityManager();
    }

    @Test
    public void jpql_typed() {
        TypedQuery<Course> query = entityManager.createQuery("Select c From Course c", Course.class);
        List resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> query = entityManager.createQuery("Select c From Course c where c.name like '%Hib%'", Course.class);
        List resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void jpql_named_query() {
        Query query = entityManager.createNamedQuery("query_get_all_courses");
        List resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void jpql_named_hibernate_query() {
        Query query = entityManager.createNamedQuery("query_get_all_hibernate_courses");
        List resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void native_queries_basic() {
        Query query = entityManager.createNativeQuery("SELECT * FROM course", Course.class);
        List resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void native_queries_with_parameter() {
        Query query = entityManager.createNativeQuery("SELECT * FROM course where id =?", Course.class);
        query.setParameter(1, 1001L);
        List resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void native_queries_with_named_parameter() {
        Query query = entityManager.createNativeQuery("SELECT * FROM course where id =:id", Course.class);
        query.setParameter(1, 1001L);
        List resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    @Transactional
    public void native_queries_to_update() {
        Query query = entityManager.createNativeQuery("UPDATE course SET last_updated_date=localtimestamp", Course.class);
        int noOfRowsUpdated = query.executeUpdate();
        logger.info("noOfRowsUpdated -> {}", noOfRowsUpdated);
    }


    @Test
    @Transactional
    public void retrieveReviewsForCourse(){
        Course course = courseRepository.findByID(1001L);
        logger.info("{}", course.getReviews());
    }

    @Test
    @Transactional(isolation = Isolation.READ_COMMITTED )
    public void retrieveCourseForReview(){
        Review review = entityManager.find(Review.class, 5001L);
        logger.info("{}",review.getCourse());
    }

    @Test
    @Transactional
    public void findById_firstLevelCacheDemo(){
        Course course = courseRepository.findByID(1001L);
        log.info("First Course Retrieved {}", course);
        Course course1 = courseRepository.findByID(1001L);
        log.info("First Course Retrieved again{}", course1);
        assertEquals("JPA in 50 Steps", course.getName());
        assertEquals("JPA in 50 Steps", course1.getName());
    }






}
