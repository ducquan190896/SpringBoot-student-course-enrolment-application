package com.quan.gradepractice.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quan.gradepractice.Entity.Course;
import com.quan.gradepractice.Service.CourseService;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    
    @Autowired
    CourseService courseService;

    @GetMapping("/")
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity(courseService.getCourses(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return new ResponseEntity(courseService.getCourse(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody Course course) {
        

        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String courseCode, @RequestParam(required = false) String department) {
        return new ResponseEntity<Course>(courseService.updateCourse(id, name, courseCode, department), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.deleteCourse(id), HttpStatus.NO_CONTENT);
    }
}
