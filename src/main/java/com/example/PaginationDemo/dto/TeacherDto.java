package com.example.PaginationDemo.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class TeacherDto {


    private Long teacherId;

    @NotNull(message = "Teacher name can't be null.")
    @NotBlank(message = "Teacher name can't be blank.")
    private String teacherName;
}

