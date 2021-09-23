package com.example.PaginationDemo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "course")
@NoArgsConstructor
@Getter
@Setter
public class Course
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long courseId;

    @Column
    private String courseName;

    @Column
    private String courseDuration;

    @Column
    private String courseDescription;


}
