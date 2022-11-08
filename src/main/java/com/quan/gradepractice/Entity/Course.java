package com.quan.gradepractice.Entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quan.gradepractice.Exception.EntityNotFoundException;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@Entity(name = "Course")
@Table(name = "course")
public class Course {
    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "name cannot be blank")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "course code cannot be blank")
    @Column(name = "courseCode", nullable = false, unique = true)
    private String courseCode;

    @NotBlank(message = "department code cannot be blank")
    @Column(name = "department", nullable = false)
    private String department;

    @JsonIgnore
    @OneToMany(
        mappedBy = "course",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
        
    )
    private Set<Grade> grades = new HashSet<>();

    @JsonIgnore
    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY,
        mappedBy = "course"
    )
    private Set<Enrolment> enrolments = new HashSet<>();

    public Course( String name,
             String courseCode,
            String department) {
        this.name = name;
        this.courseCode = courseCode;
        this.department = department;
    }

    public void addGradetoCourse(Grade grade) {
        if(!this.grades.contains(grade)) {
            this.grades.add(grade);
            grade.setCourse(this);
            
        } else {
            throw new EntityNotFoundException("the grade id " + grade.getId() + " is exist already");
        }
        
    }
    public void removeGradefromCourse(Grade grade) {
        if(this.grades.contains(grade)) {
            this.grades.remove(grade);
            grade.setCourse(null);
        } else {
            throw new EntityNotFoundException("the grade id " + grade.getId() + " not found");
        }
        
    }
    public void addEnrolmentToCourse(Enrolment enrolment) {
        if(!this.enrolments.contains(enrolment)) {
            this.enrolments.add(enrolment);
            enrolment.setCourse(this);
            enrolment.getStudent().getEnrolments().add(enrolment);
        } else {
            throw new EntityNotFoundException("the enrolment id " + enrolment.getId() + " is already exist");
        }
      
    }
    public void removeEnrolmentFromCourse(Enrolment enrolment) {
        if(this.enrolments.contains(enrolment)) {
            this.enrolments.remove(enrolment);
            enrolment.getStudent().getEnrolments().remove(enrolment);
            enrolment.setCourse(null);
        } else {
            throw new EntityNotFoundException("the enrolment id " + enrolment.getId() + " not found");
        }
      
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", courseCode=" + courseCode + ", department=" + department
                + ", grades=" + grades + ", enrolments=" + enrolments + "]";
    }

    
    
}
