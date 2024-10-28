package com.example.practicalguide_2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class StudentsRepositoryTest {
    @Autowired
    StudentsRepository studentsRepository;
    @Test
    void findAllByStatusAndName() {
        studentsRepository.findAllByStatusAndName(1L, 10L, null, null)
            .doOnComplete(() -> System.out.println("Completed"))
            .subscribe();
    }

}