package com.quan.gradepractice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quan.gradepractice.Entity.Grade;
import com.quan.gradepractice.Service.GradeService;

@RestController
@RequestMapping("/api/v1/grades")
public class GradeController {
    
    @Autowired
    GradeService gradeService;

    @GetMapping("/")
    public ResponseEntity<List<Grade>> getGrades() {
        return new ResponseEntity<>(gradeService.getGrades(), HttpStatus.OK);
    } 
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getGradesByStudent(@PathVariable Long studentId) {
        return new ResponseEntity<>(gradeService.getGradesByStudentId(studentId), HttpStatus.OK);
    } 
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getGradesByCourse(@PathVariable Long courseId) {
        return new ResponseEntity<>(gradeService.getGradesByCourseId(courseId), HttpStatus.OK);
    } 

    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> saveGrade(@RequestParam int score, @PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<Grade>(gradeService.saveGrade(studentId, courseId, score), HttpStatus.CREATED);
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> getGrade( @PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<Grade>(gradeService.getGrade(studentId, courseId), HttpStatus.OK);
    }
   
    @PutMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> updateGrade(@RequestParam int score, @PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<Grade>(gradeService.updateGrade(studentId, courseId, score), HttpStatus.OK);
    }
    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<HttpStatus> deleteGrade( @PathVariable Long studentId, @PathVariable Long courseId) {
        gradeService.deleteGrade(studentId, courseId);
        return new ResponseEntity<HttpStatus>( HttpStatus.OK);
    }
}
