package com.quan.gradepractice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quan.gradepractice.Entity.Course;
import com.quan.gradepractice.Entity.Grade;
import com.quan.gradepractice.Entity.Student;
import com.quan.gradepractice.Exception.NotFoundException;
import com.quan.gradepractice.Repository.CourseRepo;
import com.quan.gradepractice.Repository.GradeRepo;
import com.quan.gradepractice.Repository.StudentRepo;

@Service
public class GradeServiceImp implements GradeService {
    
    @Autowired
    GradeRepo gradeRepo;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    StudentRepo studentRepo;

    public List<Grade> getGrades() {
        return gradeRepo.findAll();
    } 

   
    
    public Grade saveGrade(Long studentId, Long courseId, int score) {
        Optional<Grade> gradeEntity = gradeRepo.findByStudentIdAndCourseId(studentId, courseId);
        if(gradeEntity.isPresent()) {
            throw new NotFoundException("the grade with student id " + studentId + " and course id " + courseId + " is already exist");
        }

        Optional<Student> entityS = studentRepo.findById(studentId);
        if(!entityS.isPresent()) {
            throw new NotFoundException("the grade with student id " + studentId + " not found"); 
        }
        Student student = entityS.get();
        Optional<Course> courseS = courseRepo.findById(courseId);
        if(!courseS.isPresent()) {
            throw new NotFoundException("the grade with course id " + courseId + " not found"); 
        }
        Course course = courseS.get();
        Grade grade = new Grade(score, student, course);
        return gradeRepo.save(grade);


    }

      public List<Grade> getGradesByStudentId(Long studentId) {
        Optional<Student> entity = studentRepo.findById(studentId);
        if(!entity.isPresent()) {
            throw new NotFoundException("the grades with student id " + studentId + " not found");
        }
        return gradeRepo.findByStudentId(studentId);
    }

    public List<Grade> getGradesByCourseId(Long courseId) {
        Optional<Course> entity = courseRepo.findById(courseId);
        if(!entity.isPresent()) {
            throw new NotFoundException("the grades with student id " + courseId + " not found");
        }
        return gradeRepo.findByCourseId(courseId);
    }

    public Grade getGrade(Long studentId, Long courseId) {
        Optional<Grade> entity = gradeRepo.findByStudentIdAndCourseId(studentId, courseId);
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new NotFoundException("the grade with the student id " + studentId + " and course id " + courseId + " not found the database");
    }

    @Transactional
    public Grade updateGrade(Long studentId, Long courseId, int score) {
        Grade grade = getGrade(studentId, courseId);
        grade.setScore(score);
        return gradeRepo.save(grade);
    }

    public void deleteGrade(Long studentId, Long courseId) {
        Optional<Grade> entity = gradeRepo.findByStudentIdAndCourseId(studentId, courseId);
        if(!entity.isPresent()) {
            throw new NotFoundException("the grade with the student id " + studentId + " and course id " + courseId + " not found the database");
        }
        gradeRepo.deleteByStudentIdAndCourseId(studentId, courseId);
    }
}
