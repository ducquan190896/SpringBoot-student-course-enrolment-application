package com.quan.gradepractice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quan.gradepractice.Entity.Grade;

@Repository
public interface GradeRepo extends JpaRepository<Grade, Long> {
    
    @Query(value = "select * from grade where student_id = ?1", nativeQuery = true)
    List<Grade> findByStudentId(Long studentId);

    @Query(value = "select * from grade where course_id = ?1", nativeQuery = true)
    List<Grade> findByCourseId(Long courseId);

    @Query(value = "select * from grade where student_id = ?1 and course_id = ?2", nativeQuery = true) 
    Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);

    @Transactional
    @Modifying
    @Query(value = "delete  from grade where student_id = ?1 and course_id = ?2", nativeQuery = true) 
    void deleteByStudentIdAndCourseId(Long studentId, Long courseId);
}
