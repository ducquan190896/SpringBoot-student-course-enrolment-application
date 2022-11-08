package com.quan.gradepractice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quan.gradepractice.Entity.Course;
import com.quan.gradepractice.Exception.EntityNotFoundException;
import com.quan.gradepractice.Repository.CourseRepo;
import com.quan.gradepractice.Repository.StudentRepo;

@Service
public class CourseServiceImp  implements CourseService{
    
    @Autowired
    CourseRepo courseRepo;

    public List<Course> getCourses() {
        return  courseRepo.findAll();
    }

    public Course getCourse(Long id) {
        Optional<Course> entity = courseRepo.findById(id);
        Course course = checkCourse(id, entity);
        return course;
    }

    private Course checkCourse(Long id, Optional<Course> entity ) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("the course id " + id + " not found");
    }

    public Course saveCourse(Course course) {
        Optional<Course> entity = courseRepo.checkCourse(course.getName(), course.getCourseCode(), course.getDepartment());
        if(entity.isPresent()) {
            throw new EntityNotFoundException("the course is already exist, cannot add this one to the database");
        }
        return courseRepo.save(course);
    }

    @Transactional
   public Course updateCourse(Long id, String name, String courseCode, String department) {
        Optional<Course> entity = courseRepo.findById(id);
        Course course = checkCourse(id, entity);
        if(name != null) {
            course.setName(name);
        }
        if(courseCode != null) {
            course.setCourseCode(courseCode);
        }
        if(department != null) {
            course.setDepartment(department);
        }
        return courseRepo.save(course);
   }
   public String deleteCourse(Long id) {
    Optional<Course> entity = courseRepo.findById(id);
    Course course = checkCourse(id, entity);
    courseRepo.deleteById(id);
    return "course id " + id + " is already deleted successfully";
   }
}
