package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
class StudentRepositoryTest {

    @Autowired
    private JdbcClient jdbcClient;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setup() {

        jdbcClient.sql("drop table student if exists ")
                .update();
        jdbcClient.sql("create table student(id serial, firstname varchar(255), lastname varchar(255))")
                .update();

        studentRepository.save(Student.builder()
                .firstname("Hippo")
                .lastname("Hippy")
                .build());

        studentRepository.save(Student.builder()
                .firstname("Hippo")
                .lastname("Hippier")
                .build());
    }

    @Test
    void shouldGetStudents() {
        int hippo = studentRepository.findStudentByFirstname("Hippo").size();
        assertThat(hippo).isEqualTo(2);
    }

}