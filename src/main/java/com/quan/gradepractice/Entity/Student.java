package com.quan.gradepractice.Entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quan.gradepractice.Exception.NotFoundException;
import com.quan.gradepractice.Repository.CourseRepo;
import com.quan.gradepractice.Repository.EnrolmentRepo;

import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Student")
@Table(name = "student")
public class Student {
   
 

    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName =  "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
         generator =  "student_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "name cannot be blank")
    @Column(name = "name", nullable = false)
    private String name;

    @Email
    @NotBlank(message = "email cannot be blank")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Past(message = "The birth date must be in the past")
   
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Transient
    private int age;

    @JsonIgnore
    @OneToMany(mappedBy = "student", 
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch =  FetchType.LAZY
    )
    private Set<Grade> grades = new HashSet<>();

    @JsonIgnore
    @OneToMany(
        mappedBy = "student",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private Set<Enrolment> enrolments = new HashSet<>();

    @JsonBackReference
    @OneToOne(
        mappedBy = "student",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private StudentIdCard studentIdCard;

    public Student(String name,String email,LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = Period.between(dob, LocalDate.now()).getYears();
    }

    
    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void addGradeToStudent(Grade grade) {
        if(!this.grades.contains(grade)) {
            this.grades.add(grade);
            grade.setStudent(this);
        } else {
            throw new NotFoundException("the grade id " + grade.getId() + " is exist already");
        }
        
    }
    public void addEnrolmentToStudent(Enrolment enrolment) {
        if(!this.enrolments.contains(enrolment)) {
            this.enrolments.add(enrolment);
            enrolment.setStudent(this);
            enrolment.getCourse().getEnrolments().add(enrolment);
        } else {
            throw new NotFoundException("the enrolment id " + enrolment.getId() + " is already exist");
        }
        
   }
   public void removeEnrolmentFromStudent(Enrolment enrolment) {
        if(this.enrolments.contains(enrolment)) {
            this.enrolments.remove(enrolment);
            enrolment.getCourse().getEnrolments().remove(enrolment);
            enrolment.setStudent(null);
           
            
        } else {
            throw new NotFoundException("the enrolment id " + enrolment.getId() + " not found");
        }
    
   }

   public void addStudentIdCardToStudent(StudentIdCard studentIdCard) {
    if(this.studentIdCard == null) {
        this.studentIdCard = studentIdCard;
        studentIdCard.setStudent(this);
    } else {
        throw new NotFoundException("this student id already has a student card, cannot add anymore");
    }
       
   }
   public void removeStudentIdCardToStudent() {
    if(this.studentIdCard != null) {
        this.studentIdCard = null;
        
    } else {
        throw new NotFoundException("this student id has no student card, cannot remove it");
    }
       
   }

    @Override
    public String toString() {
        return "Student [id=" + this.id + ", name=" + this.name + ", email=" + this.email + ", dob=" + this.dob + ", age=" + this.age + "]";
    }
   
    


}
