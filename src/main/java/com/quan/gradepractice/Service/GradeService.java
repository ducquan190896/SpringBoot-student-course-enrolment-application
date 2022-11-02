package com.quan.gradepractice.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.quan.gradepractice.Entity.Grade;

public interface GradeService {
    
    List<Grade> getGrades();
    List<Grade> getGradesByStudentId(Long studentId);
    List<Grade> getGradesByCourseId(Long CourseId);
    Grade saveGrade(Long studentId, Long courseId, int score);
    Grade getGrade(Long studentId, Long courseId);
    @Transactional
    Grade updateGrade(Long studentId, Long courseId, int score);
    void deleteGrade(Long studentId, Long courseId);
}
