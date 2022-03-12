package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@DataJdbcTest
class StudentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setup() {
        jdbcTemplate.execute("drop table student if exists ");
        jdbcTemplate.execute("""
                create table student(id serial, firstname varchar(255), lastname varchar(255))
                """);

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
        Assertions.assertThat(hippo).isEqualTo(2);
    }

}