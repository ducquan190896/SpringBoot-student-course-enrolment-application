package com.quan.gradepractice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quan.gradepractice.Entity.Student;
import com.quan.gradepractice.Entity.StudentIdCard;

@Repository
public interface StudentIdCardRepo extends JpaRepository<StudentIdCard, Long> {
    
    @Query(value = "select * from student_id_card where student_id = ?1", nativeQuery =  true)
    Optional<StudentIdCard> findByStudentId(Long studentId);

    @Query(value = "select * from student_id_card where card_number = ?1", nativeQuery = true)
    Optional<StudentIdCard> findByCardNumber(String cardNumber);
    
    @Transactional
    @Modifying
    @Query(value = "Delete from student_id_card where card_number = ?1", nativeQuery = true)
    void deleteByCardNumber(String cardNumber);
}
