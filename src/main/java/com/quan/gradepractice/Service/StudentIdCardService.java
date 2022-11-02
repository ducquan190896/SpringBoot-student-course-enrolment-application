package com.quan.gradepractice.Service;

import java.util.List;

import com.quan.gradepractice.Entity.StudentIdCard;

public interface StudentIdCardService {
    List<StudentIdCard> getCards();
    StudentIdCard saveCard(Long studentId);
    StudentIdCard getCardByStudentId(Long studentId);
    StudentIdCard getCardByCardNumber(String cardNumber);
    void deleteCardByCardNumber(String cardNumber);
}
