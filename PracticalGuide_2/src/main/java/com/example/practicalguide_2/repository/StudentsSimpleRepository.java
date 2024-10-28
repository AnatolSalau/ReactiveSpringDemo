package com.example.practicalguide_2.repository;

import com.example.practicalguide_2.entity.Students;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("studentsSimpleRepository")
@Qualifier("studentsSimpleRepository")
public interface StudentsSimpleRepository extends JpaRepository<Students, Long> {
}
