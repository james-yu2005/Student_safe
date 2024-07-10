package com.james.__hour_tutorial.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "zirstname cannot be empty")
        String firstname,
        @NotEmpty(message = "Yo, lastname cannot be empty")
        String lastname,
        String email,
        Integer schoolId) {

}
