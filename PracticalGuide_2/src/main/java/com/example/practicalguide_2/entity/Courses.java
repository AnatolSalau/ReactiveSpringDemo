package com.example.practicalguide_2.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table("courses")
public class Courses {
    @Id
    @Column("id")
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Column("course_name")
    private String coursename;
}