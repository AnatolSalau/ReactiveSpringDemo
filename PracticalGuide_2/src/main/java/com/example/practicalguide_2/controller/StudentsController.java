package com.example.practicalguide_2.controller;


import com.example.practicalguide_2.entity.Students;
import com.example.practicalguide_2.repository.StudentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class StudentsController {
    private final StudentsRepository studentsRepository;

    @GetMapping("/students/{studentID}")
    Mono<ResponseEntity<Students>> getStudent(@PathVariable Long studentID) {
        return studentsRepository.findById(studentID).map(student -> new ResponseEntity<>(student, HttpStatus.OK));
    }
    @PostMapping("/students")
    Mono<ResponseEntity<Students>> addStudent(@RequestBody Students studentAdd) {
        studentAdd.setRegisteredOn(System.currentTimeMillis());
        studentAdd.setStatus(1);
        return studentsRepository.save(studentAdd).map(student -> new ResponseEntity<>(student, HttpStatus.CREATED));
    }
}