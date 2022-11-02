package com.quan.gradepractice.Entity;

import javax.persistence.*;
import javax.validation.constraints.*;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "grade", uniqueConstraints = {@UniqueConstraint(columnNames = {"student_id", "course_id"})})
public class Grade {
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

    @Min(value = 0, message = "score must be larger than 0")
    @Max(value = 10, message = "score must be smaller than 10")
  
    @Column(name = "score", nullable = false)
    private int score;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
        name = "student_id",
        referencedColumnName = "id"
    )
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
        name = "course_id",
        referencedColumnName = "id"
    )
    private Course course;

    public Grade(
             int score,
            Student student, Course course) {
        this.score = score;
        this.student = student;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Grade [id=" + id + ", score=" + score + ", student=" + student + ", course=" + course + "]";
    }

    
}
