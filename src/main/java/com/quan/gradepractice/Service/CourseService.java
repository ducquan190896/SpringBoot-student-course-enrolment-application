package com.quan.gradepractice.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.quan.gradepractice.Entity.Course;

public interface CourseService {

    List<Course> getCourses();
    Course getCourse(Long id);
    Course saveCourse(Course course);

    @Transactional
    Course updateCourse(Long id, String name, String courseCode, String department);

    String deleteCourse(Long id);
}
