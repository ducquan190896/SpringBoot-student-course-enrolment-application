package com.quan.gradepractice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.gradepractice.Entity.StudentIdCard;
import com.quan.gradepractice.Service.StudentIdCardService;

import com.quan.gradepractice.Service.StudentService;

@RestController
@RequestMapping("/api/v1/studentIdCards")
public class StudentIdCardController {
    
    @Autowired
    StudentIdCardService studentIdCardService;

    @Autowired
    StudentService studentService;


    @GetMapping("/")
    public ResponseEntity<List<StudentIdCard>> getCards() {
        return new ResponseEntity<>(studentIdCardService.getCards(), HttpStatus.OK);
    }

    @PostMapping("/student/{studentId}")
    public ResponseEntity<StudentIdCard> saveCard(@PathVariable Long studentId) {
        return new ResponseEntity<>(studentIdCardService.saveCard(studentId), HttpStatus.CREATED);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentIdCard> getCardByStudent(@PathVariable Long studentId) {
        return new ResponseEntity<>(studentIdCardService.getCardByStudentId(studentId), HttpStatus.OK);
    }

    @GetMapping("/cardNumber/{cardNumber}")
    public ResponseEntity<StudentIdCard> getCardById(@PathVariable String cardNumber) {
        return new ResponseEntity<>(studentIdCardService.getCardByCardNumber(cardNumber), HttpStatus.OK);
    }

    @DeleteMapping("/cardNumber/{cardNumber}")
    public ResponseEntity<HttpStatus> deleteCardBycardNumber(@PathVariable String cardNumber) {
        studentIdCardService.deleteCardByCardNumber(cardNumber);
        return new ResponseEntity<HttpStatus>( HttpStatus.NO_CONTENT);
    }
}
