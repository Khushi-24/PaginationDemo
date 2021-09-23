package com.example.PaginationDemo.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name ="student")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column
    private String studentName;

    @Column
    private String studentDivision;

    @Column
    private Integer studentAge;

}
