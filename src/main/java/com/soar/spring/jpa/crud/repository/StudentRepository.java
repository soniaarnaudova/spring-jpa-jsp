package com.soar.spring.jpa.crud.repository;

import com.soar.spring.jpa.crud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.emailId = ?1")
    Student findStudentByEmail(String email);

}
