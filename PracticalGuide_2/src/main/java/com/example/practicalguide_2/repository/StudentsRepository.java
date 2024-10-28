package com.example.practicalguide_2.repository;


import com.example.practicalguide_2.entity.Students;
import jakarta.transaction.Transactional;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentsRepository extends ReactiveCrudRepository<Students, Long> {

    @Transactional
    @Override
    default <S extends Students> Mono<S> save(S entity) {
        return null;
    }


    @Query("select * from students where status = :status and name like :name LIMIT :limit OFFSET :offset")
    Flux<Students> findAllByStatusAndName(Long status, String name, Long limit, Long offset);
    //Flux<Students> findAllByStatusAndName(Long offset, Long limit, String status, String name);
}