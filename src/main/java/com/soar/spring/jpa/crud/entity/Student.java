package com.soar.spring.jpa.crud.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="student",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
        )
)
public class Student {

    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="student_sequence"
    )
    private Long studentId;
    private String firstName;
    private String lastName;

    @Column(
            name="email_address",
            nullable = false
    )
    private String emailId;

    @Embedded
    private Guardian guardian;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getStudentId(), student.getStudentId()) &&
                Objects.equals(getFirstName(), student.getFirstName()) &&
                Objects.equals(getLastName(), student.getLastName()) &&
                Objects.equals(getEmailId(), student.getEmailId()) &&
                Objects.equals(getGuardian(), student.getGuardian());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getFirstName(), getLastName(), getEmailId(), getGuardian());
    }
}
