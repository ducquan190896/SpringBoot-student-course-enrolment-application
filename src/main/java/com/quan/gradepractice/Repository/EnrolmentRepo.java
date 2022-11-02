package com.quan.gradepractice.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.gradepractice.Entity.Enrolment;

@Repository
public interface EnrolmentRepo extends JpaRepository<Enrolment, Long>  {

    @Query(value = "select * from enrolment where student_id = ?1 and course_id = ?2", nativeQuery = true)
    Optional<Enrolment> getEnrolment(Long studentId, Long courseId);

    @Transactional
    @Modifying
    @Query(value =  "DELETE from enrolment where student_id = ?1 and course_id = ?2", nativeQuery = true)
    void deleteEnrolment(Long studentId, Long courseId);

    @Query(value = "select * from enrolment where student_id = ?1", nativeQuery =  true)
    List<Enrolment> getEnrolmentByStudentId(Long studentId);

    @Query(value = "select * from enrolment where course_id = ?1", nativeQuery =  true)
    List<Enrolment> getEnrolmentByCourseId(Long courseId);
}
