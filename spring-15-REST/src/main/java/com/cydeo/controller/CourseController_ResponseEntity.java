package com.cydeo.controller;

import com.cydeo.dto.CourseDTO;
import com.cydeo.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/api/v2")
public class CourseController_ResponseEntity {
    //We create this Controller because we want to manipulate some codes.
    //we want to add some status code, header , etc.


    private final CourseService courseService;

    public CourseController_ResponseEntity(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){   //with this ResponseEntity we can manipulate status code,header etc. this came with Spring boot.

        return ResponseEntity
                .status(HttpStatus.ACCEPTED) // httpStatus.accepted=202
                .header("Version","Cydeo.V2")
                .header("Operation","Get List")
                .body(courseService.getCourses()); //body = what data do you want to show in API
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") long courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId)); //ok=200
    }

    @GetMapping("category/{name}")
    public ResponseEntity<List<CourseDTO>> getCourseByCategory(@PathVariable("name") String category){
        return ResponseEntity.ok(courseService.getCoursesByCategory(category)); //ok=200,give name of category and return all the courses.
    }

    @PostMapping()
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO course){
        return ResponseEntity
                .status(HttpStatus.CREATED)  //status = 201 Created
                .header("Operation","Create")
                .body(courseService.createCourse(course));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable("id") Long courseId){
        courseService.deleteCourseById(courseId);
        return ResponseEntity.noContent().build();
        // when we delete something, we doesnt return anything. because of this we use ResponseEntity<Void>.  ResponseEntity.noContent() is give us 204 status code. when return void, use noContent().
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable("id") Long courseId,@RequestBody CourseDTO course){
        courseService.updateCourse(courseId,course);
        return ResponseEntity.noContent().build();
    }




}
