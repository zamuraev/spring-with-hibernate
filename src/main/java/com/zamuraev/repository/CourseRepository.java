package com.zamuraev.repository;

import com.zamuraev.entity.Course;
import com.zamuraev.entity.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CourseRepository {

    private final EntityManager entityManager;

    public Course findByID(Long id) {
        Course course = entityManager.find(Course.class, id);
        log.info("Course -> {}", course);
        return course;
    }

    public void deleteById(Long id) {
        Course course = findByID(id);
        entityManager.remove(course);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }
        return course;
    }

    public void playWithEntityManager() {
        Course course1 = new Course("Web Services-1");
        entityManager.persist(course1);
        course1.setName("Updated Web Services");
        entityManager.merge(course1);

        Course course2 = new Course("Web Services-2");
        entityManager.persist(course2);
        entityManager.flush();
        entityManager.detach(course2);
        course2.setName("Web Services-2 Updated");
        entityManager.flush();
        entityManager.clear(); //detach everything
        entityManager.refresh(course1); //reject all set changes
    }

    public void addReviewsForCourse(){
        Course course = findByID(1003L);
        log.info("course.getReviews() -> {}",course.getReviews());
        Review review1 = new Review("5", "Great Hands-on Stuff.");
        Review review2 = new Review("5", "Hatsoff.");
        course.getReviews().add(review1);
        course.getReviews().add(review2);
        review1.setCourse(course);
        review2.setCourse(course);
        entityManager.persist(review1);
        entityManager.persist(review2);
    }







}
