package com.quan.gradepractice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quan.gradepractice.Entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>  {
    
}
