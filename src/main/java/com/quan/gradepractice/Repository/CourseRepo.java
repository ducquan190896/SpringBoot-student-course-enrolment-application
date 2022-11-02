package com.quan.gradepractice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.gradepractice.Entity.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    
    @Query(value = "select * from course where course_code = ?2 and name = ?1 and department = ?3", nativeQuery = true)
    Optional<Course> checkCourse(String name, String courseCode, String department);
}
