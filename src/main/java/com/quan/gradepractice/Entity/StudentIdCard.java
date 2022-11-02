package com.quan.gradepractice.Entity;

import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "StudentIdCard")
@Table(name = "studentIdCard")
public class StudentIdCard {
    
    @Id
    @SequenceGenerator(
        name = "studentIdCard_sequence",
        sequenceName = "studentIdCard_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "studentIdCard_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "card_number", unique = true)
    private String cardNumber;

    // in OneToOne relationship, to avoid json recursion problem, i use @JsonMangedRefence in the entity which manages its relationship, and the opposite side, i use @JsonBackReference. I never use @jsonignore.
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
        name = "student_id",
        referencedColumnName = "id"
    )
    private Student student;


    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }
    // public StudentIdCard(Student student) {
    //     String cardId = UUID.randomUUID().toString().replaceAll("-", "");
    //     this.cardNumber = cardId;
    //     this.student = student;
    // }




}
