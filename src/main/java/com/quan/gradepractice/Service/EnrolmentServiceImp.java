package com.quan.gradepractice.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quan.gradepractice.Entity.Course;
import com.quan.gradepractice.Entity.Enrolment;
import com.quan.gradepractice.Entity.Student;
import com.quan.gradepractice.Exception.NotFoundException;
import com.quan.gradepractice.Repository.CourseRepo;
import com.quan.gradepractice.Repository.EnrolmentRepo;
import com.quan.gradepractice.Repository.StudentRepo;

@Service
public class EnrolmentServiceImp implements EnrolmentService {
    @Autowired
    EnrolmentRepo enrolmentRepo;
    @Autowired 
    CourseRepo courseRepo;
    @Autowired
    StudentRepo studentRepo;

    public List<Enrolment> getEnrolments() {
        return enrolmentRepo.findAll();
    }

    public Enrolment getEnrolment(Long studentId, Long courseId) {
        Optional<Enrolment> entity = enrolmentRepo.getEnrolment(studentId, courseId);
        Enrolment enrolment = checkEnrolment(studentId, courseId, entity);
        return enrolment;
    }

    private Enrolment checkEnrolment(Long studentId, Long courseId, Optional<Enrolment> entity) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new NotFoundException("the enrolment with the student id " + studentId + " and the course id " + courseId + " not found");
    }

    public Enrolment saveEnrolment(Long studentId, Long courseId) {
       Optional<Enrolment> entity = enrolmentRepo.getEnrolment(studentId, courseId);
       if(entity.isPresent()) {
       
        throw new NotFoundException("the enrolment with the student id " + studentId + " and the course id " + courseId + "  already exists");
       }

        
        Optional<Student> studentEntity = studentRepo.findById(studentId);
        if(!studentEntity.isPresent()) throw new NotFoundException("the student id " + studentId + " not found");
        Student student = studentEntity.get();
        Optional<Course> courseEntity = courseRepo.findById(courseId);
        if(!courseEntity.isPresent()) throw new NotFoundException("the course id "+ courseId + " not found");
        Course course = courseEntity.get();
        LocalDateTime ldt = LocalDateTime.now();
        Enrolment enrolment = new Enrolment(student, course, ldt);
        return enrolmentRepo.save(enrolment);
        

    }

   
   public void deleteEnrolment(Long studentId, Long courseId) {
        Enrolment enrolment = getEnrolment(studentId, courseId);
        
            enrolmentRepo.deleteEnrolment(studentId, courseId);;
        

    }

    public List<Enrolment> getEnrolmentByCourseId(Long courseId) {
        return enrolmentRepo.getEnrolmentByCourseId(courseId);
    }

    public  List<Enrolment> getEnrolmentByStudentId(Long studentId) {
        return enrolmentRepo.getEnrolmentByStudentId(studentId);
    }
}
