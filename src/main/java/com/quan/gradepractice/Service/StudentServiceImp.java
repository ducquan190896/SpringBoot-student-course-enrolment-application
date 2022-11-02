package com.quan.gradepractice.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quan.gradepractice.Entity.Student;
import com.quan.gradepractice.Exception.NotFoundException;
import com.quan.gradepractice.Repository.StudentRepo;

@Service
public class StudentServiceImp implements StudentService{
    
    @Autowired
    StudentRepo studentRepo;

    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }
    @Transactional
   public Student updateStudent(Long id, String name, String email , LocalDate dob) {
        Optional<Student> entity = studentRepo.findById(id);
        
         Student student =  checkStudent(id, entity);
        
        if(name != null) {
            student.setName(name);
        }
        if(email != null) {
            student.setEmail(email);
        }
        if(dob != null) {
            student.setDob(dob);
        }
        return  studentRepo.save(student);
    }

    public Student getStudent(Long id) {
        Optional<Student> entity = studentRepo.findById(id);
        
        Student student =  checkStudent(id, entity);
        return student;
    }


    private Student checkStudent(Long id ,Optional<Student> entity) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new NotFoundException("The student id + " + id +" not found in the database");
    }
    
    
    public String deleteStudent(Long id) {
        Optional<Student> entity = studentRepo.findById(id);
        Student student = checkStudent(id, entity);
        studentRepo.delete(student);
        return "student id " + id + " is deleted successfully";
    }
}

