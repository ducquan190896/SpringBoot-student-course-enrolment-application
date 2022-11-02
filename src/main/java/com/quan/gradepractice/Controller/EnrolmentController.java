package com.quan.gradepractice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.gradepractice.Entity.Enrolment;
import com.quan.gradepractice.Service.EnrolmentService;

@RestController
@RequestMapping("/api/v1/enrolments")
public class EnrolmentController {
    

    @Autowired
    EnrolmentService enrolmentService;

    @GetMapping("/")
    public ResponseEntity<List<Enrolment>> getEnrolments() {
        return new ResponseEntity<>(enrolmentService.getEnrolments(), HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Enrolment> getEnrolments( @PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(enrolmentService.getEnrolment(studentId, courseId), HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrolment>> getEnrolmentsByStudentId( @PathVariable Long studentId ) {
        return new ResponseEntity<>(enrolmentService.getEnrolmentByStudentId(studentId), HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrolment>> getEnrolmentsByCourseId( @PathVariable Long courseId ) {
        return new ResponseEntity<>(enrolmentService.getEnrolmentByCourseId(courseId), HttpStatus.OK);
    }

    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Enrolment> saveEnrolment( @PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<Enrolment>(enrolmentService.saveEnrolment(studentId, courseId), HttpStatus.CREATED);
    }

    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<HttpStatus> deleteEnrolment(@PathVariable Long studentId, @PathVariable Long courseId) {
        enrolmentService.deleteEnrolment(studentId, courseId);
        return new ResponseEntity<HttpStatus>( HttpStatus.NO_CONTENT);
    }
}
