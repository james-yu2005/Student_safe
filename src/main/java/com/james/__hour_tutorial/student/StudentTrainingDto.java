package com.james.__hour_tutorial.student;

import jakarta.validation.constraints.NotEmpty;

import java.lang.reflect.Member;

public record StudentTrainingDto(
        @NotEmpty(message = "Please enter student firstname")
        String firstname,
        @NotEmpty(message = "Please enter student lastname")
        String lastname,
        @NotEmpty(message = "Please enter training time (whether half or full day)")
        String trainingTime) {

}
