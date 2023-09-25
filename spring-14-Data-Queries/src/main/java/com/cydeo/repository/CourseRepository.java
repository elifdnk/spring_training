package com.cydeo.repository;

import com.cydeo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

public interface CourseRepository extends JpaRepository<Course,Long> {

    //Find all courses by category select*from where category ....
    List<Course> findByCategory(String category);

    //Find all courses by category and order the entities by name
    List<Course> findByCategoryOrderByName(String category);

    //Checks if a course with the supplied name exist. Return true if exists, false otherwise;
    boolean existsByName(String name);

    //Returns the count of courses for the given category
    int countByCategory(String category);

    //Finds all the courses that start with the given course name string
    List<Course> findAllByNameStartingWith(String name);

    //Find all courses by category returns a stream
    Stream<Course> streamAllByCategory(String category);





}