package com.quan.gradepractice.Service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.gradepractice.Entity.Student;
import com.quan.gradepractice.Entity.StudentIdCard;
import com.quan.gradepractice.Exception.NotFoundException;
import com.quan.gradepractice.Repository.StudentIdCardRepo;
import com.quan.gradepractice.Repository.StudentRepo;

@Service
public class StudentIdCardServiceImp implements StudentIdCardService{
    
    @Autowired
    StudentIdCardRepo studentIdCardRepo;
    @Autowired
    StudentRepo studentRepo;

    public   List<StudentIdCard> getCards() {
        return studentIdCardRepo.findAll();
    }

    public StudentIdCard saveCard(Long studentId) {
        Optional<Student> entity = studentRepo.findById(studentId);
        if(!entity.isPresent()) throw new NotFoundException("student id " + studentId + " not found");
        
        Student student = entity.get();

        Optional<StudentIdCard> cardEntity = studentIdCardRepo.findByStudentId(studentId);
        if(cardEntity.isPresent()) {
            throw new NotFoundException("the card with the student id " + studentId + " is already exist");
        }
        String cardNumber = UUID.randomUUID().toString();
        StudentIdCard card = new StudentIdCard(cardNumber, student);
        return studentIdCardRepo.save(card);
    }

    public  StudentIdCard getCardByStudentId(Long studentId) {
        Optional<Student> entity = studentRepo.findById(studentId);
        if(!entity.isPresent()) throw new NotFoundException("student id " + studentId + " not found");

        Optional<StudentIdCard> cardEntity = studentIdCardRepo.findByStudentId(studentId);
        if(cardEntity.isPresent()) {
            return cardEntity.get();
        }
        throw new NotFoundException("the card with student id " + studentId + " not found in the database, please create new card eventhoud the student is exist");
    }
    public StudentIdCard getCardByCardNumber(String cardNumber){
       

        Optional<StudentIdCard> cardEntity = studentIdCardRepo.findByCardNumber(cardNumber);
        if(cardEntity.isPresent()) {
            return cardEntity.get();
        }
        throw new NotFoundException("the card with card number  (" + cardNumber + ") not found in the database");
    }

    public void deleteCardByCardNumber(String cardNumber) {


        studentIdCardRepo.deleteByCardNumber(cardNumber);
    }
}
