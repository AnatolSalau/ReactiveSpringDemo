package com.example.practicalguide_2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table("students")
public class Students {


    @Column("id")
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE
    )
    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("registered_on")
    private Long registeredOn;

    @Column("status")
    private Integer status;

    @Column("version")
    @Version
    private Integer version;
}