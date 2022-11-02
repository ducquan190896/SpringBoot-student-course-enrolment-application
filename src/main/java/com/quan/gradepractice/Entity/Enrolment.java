package com.quan.gradepractice.Entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "Enrolment")
@Table(name = "enrolment", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "course_id"})
})
public class Enrolment {
    @Id
    @EmbeddedId
    private EnrolmentID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("studentId")
    @JoinColumn(
        name = "student_id",
        referencedColumnName = "id"
    )
    private Student student;


    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("courseId")
    @JoinColumn(
        name = "course_id",
        referencedColumnName = "id"
    )
    private Course course;

    @Column(name = "create_At")
    private LocalDateTime localDateTime;

    public Enrolment(Student student, Course course, LocalDateTime localDateTime) {
        this.student = student;
        this.course = course;
        this.localDateTime = localDateTime;
        this.id = new EnrolmentID(student.getId(), course.getId());
    }

    public Enrolment(EnrolmentID id, Student student, Course course, LocalDateTime localDateTime) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Enrolment [id=" + id + ", student=" + student + ", course=" + course + ", localDateTime="
                + localDateTime + "]";
    }

   


}
