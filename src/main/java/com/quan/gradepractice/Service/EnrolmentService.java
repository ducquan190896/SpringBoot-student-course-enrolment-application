package com.quan.gradepractice.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.quan.gradepractice.Entity.Enrolment;

public interface EnrolmentService {
    List<Enrolment> getEnrolments();
    Enrolment getEnrolment(Long studentId, Long courseId);
    Enrolment saveEnrolment(Long studentId, Long courseId);
  

    void deleteEnrolment(Long studentId, Long courseId);
    List<Enrolment> getEnrolmentByStudentId(Long studentId);
    List<Enrolment> getEnrolmentByCourseId(Long courseId);
}
