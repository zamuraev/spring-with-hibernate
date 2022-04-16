package com.zamuraev.repository;

import com.zamuraev.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseSpringDataRepository extends JpaRepository<Course,Long> {

    List<Course> findByNameAndId(String name, Long id);
    List<Course> findByName(String name);
    List<Course> countByName(String name);
    List<Course> findByNameOrderByIdDesc(String name);
    List<Course> deleteByName(String name);


    @Query("SELECT c FROM Course c WHERE name LIKE '%100 Steps'")
    List<Course> courseWith100StepsInName();

    @Query(value = "Select * FROM course WHERE name LIKE '%100 Steps'", nativeQuery = true)
    List<Course> courseWith100StepsInNameUsingNativeQuery();


}
