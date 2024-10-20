package com.example.practicalguide_2.repository;

import com.example.practicalguide_2.entity.Students;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentsRepositoryTest {

    @Autowired
    private StudentsRepository studentsRepository;

    @Test
    void findAllByStatusAndName() {
        Flux<Students> studentsFlux = studentsRepository
            .findAllByStatusAndName(0L, 10L, "1", "Ivan");

        Disposable done = studentsFlux.doOnComplete(() -> System.out.println("Done")).subscribe(System.out::println);
        System.out.println(done);
    }

}