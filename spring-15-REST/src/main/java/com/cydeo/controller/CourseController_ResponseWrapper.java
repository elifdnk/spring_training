package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/api/v3")
public class CourseController_ResponseWrapper {
    private final CourseService courseService;

    public CourseController_ResponseWrapper(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllCourses(){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("Version","Cydeı.V3")
                .body(new ResponseWrapper("courses successfully retrieved",courseService.getCourses()));//Whatever write inside body, we see in the API
    }

    @GetMapping("{id}")
    public  ResponseEntity<ResponseWrapper> getCourseById(@PathVariable("id") long courseId){
        return ResponseEntity.ok(new ResponseWrapper("course: " + courseId+ " retrieved",courseService.getCourseById(courseId)));
    }

}
