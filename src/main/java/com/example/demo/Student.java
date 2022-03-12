package com.example.demo;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Student {
    @Id
    private Long id;
    private String firstname;
    private String lastname;
}
