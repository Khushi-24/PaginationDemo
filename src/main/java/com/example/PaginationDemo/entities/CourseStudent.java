package com.example.PaginationDemo.entities;

import com.example.PaginationDemo.dto.CourseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
//
//@NamedNativeQuery(name = "CourseStudent.getStudentByCourseCourseId",
//        query = "SELECT p.first_name as first, p.last_name as last FROM Chess_Player p WHERE id = :id",
//        resultSetMapping = "Mapping.PlayerNameDto")
//@SqlResultSetMapping(name = "Mapping.PlayerNameDto",
//        classes = @ConstructorResult(targetClass = CourseDto.class,
//                columns = {@ColumnResult(name = "first"),
//                        @ColumnResult(name = "last")}))
//

@Entity(name = "courseStudent")
@Getter
@Setter
@NoArgsConstructor
public class CourseStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseStudentId;

    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "courseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student student;


    public CourseStudent(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
}
