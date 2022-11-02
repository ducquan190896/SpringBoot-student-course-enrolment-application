
package com.quan.gradepractice.Service;
import java.time.LocalDate;
import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quan.gradepractice.Entity.Student;


public interface StudentService {
     Student saveStudent(Student student);
     List<Student> getStudents();
     Student getStudent(Long id);
     @Transactional
     Student updateStudent(Long id, String name, String email, LocalDate dob);
    
     String deleteStudent(Long id);
}
