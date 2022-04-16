package com.zamuraev.repository;

import com.zamuraev.entity.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CourseSpringDataRepositoryTest {

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void findById_CoursePresent(){
        Optional<Course> courseOptional = repository.findById(1000L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent(){
        Optional<Course> courseOptional = repository.findById(2000L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    public void playingAroundWithSpringDataRepository(){
        Course course = new Course("Microservices in 100 Steps");
        repository.save(course);
        course.setName("Microservices in 100 Steps - Updated");
        repository.save(course);

        log.info("Courses -> {}",repository.findAll());
        log.info("Count -> {}",repository.count());
    }

    @Test
    public void sort(){
        log.info("Sort Courses -> {}",repository.findAll(Sort.by(Sort.Direction.DESC, "name")));
        log.info("Count -> {}",repository.count());
    }

    @Test
    public void pagination(){
        PageRequest pageRequest = PageRequest.of(0,3);
        Page<Course> firstPage = repository.findAll(pageRequest);
        log.info("First Page -> {}",firstPage.getContent());
        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        log.info("Second Page -> {}",secondPage.getContent());
    }






}
