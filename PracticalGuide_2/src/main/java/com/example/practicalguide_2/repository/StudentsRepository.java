package com.example.practicalguide_2.repository;


import com.example.practicalguide_2.entity.Students;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StudentsRepository extends ReactiveCrudRepository<Students, Long> {

}