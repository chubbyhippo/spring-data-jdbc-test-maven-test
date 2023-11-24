package com.example.demo;

import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record Student(
        @Id Long id,
        String firstname,
        String lastname
) {
}
