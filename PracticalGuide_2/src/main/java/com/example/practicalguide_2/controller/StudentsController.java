package com.example.practicalguide_2.controller;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import com.example.practicalguide_2.dto.GeneralResponse;
import com.example.practicalguide_2.entity.Students;
import com.example.practicalguide_2.repository.CourseWorkRepository;
import com.example.practicalguide_2.repository.StudentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class StudentsController {
    private final StudentsRepository studentsRepository;

    private final CourseWorkRepository courseWorkRepository;

    @GetMapping("/students/{studentID}")
    Mono<ResponseEntity<Students>> getStudent(@PathVariable Long studentID) {
        return studentsRepository.findById(studentID).map(student -> new ResponseEntity<>(student, HttpStatus.OK));
    }

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<Students> getStudents(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Long limit,
        @RequestParam Map<String, String> filterParams
    ) {
        String status = filterParams.getOrDefault("status", "1");
        String name = filterParams.getOrDefault("name", "User");
        if (name != null) {
            name = "%" + name + "%";
        }

        long offset = (page - 1) * limit;
        System.out.println();
        return studentsRepository.findAllByStatusAndName(offset, limit, status, name).delayElements(Duration.ofSeconds(2L));
    }

    @PostMapping("/students")
    Mono<ResponseEntity<Students>> addStudent(@RequestBody Students studentAdd) {
        studentAdd.setRegisteredOn(System.currentTimeMillis());
        studentAdd.setStatus(1);
        return studentsRepository.save(studentAdd).map(student -> new ResponseEntity<>(student, HttpStatus.CREATED));
    }

    @PutMapping("/students/{studentID}")
    Mono<ResponseEntity<GeneralResponse<Students>>> updateStudent(@PathVariable Long studentID, @RequestBody Students newStudentData) {

        return studentsRepository.findById(studentID)
            .switchIfEmpty(Mono.error(new Exception(String.format("Student with ID %d not found", studentID))))
            .flatMap(foundStudent -> {
                //here we are just updating the name. You can add others
                foundStudent.setName(newStudentData.getName());

                return studentsRepository.save(foundStudent);
            }).map(student -> {
                HashMap<String, Students> data = new HashMap<>();
                data.put("student", student);

                return new ResponseEntity<>(
                    GeneralResponse.<Students>builder()
                        .success(true)
                        .message("Student updated successfully")
                        .data(data)
                        .build(),
                    HttpStatus.ACCEPTED
                );
            }).onErrorResume(e -> {
                return Mono.just(
                    new ResponseEntity<>(
                        GeneralResponse.<Students>builder()
                            .success(false)
                            .message(e.getMessage())
                            .build(),
                        HttpStatus.NOT_FOUND
                    )
                );
            });

    }

    @DeleteMapping("/students/{studentID}")
    @Transactional
    Mono<ResponseEntity<GeneralResponse<Students>>> deleteStudent(@PathVariable Long studentID) {
        return studentsRepository.findById(studentID)
            .switchIfEmpty(Mono.error(new Exception(String.format("Student with ID %d not found", studentID))))
            .flatMap(foundStudent -> {
                return courseWorkRepository.deleteByStudentID(studentID)
                    .then(studentsRepository.deleteById(studentID))
                    .thenReturn(foundStudent);
            })
            .map(deletedStudent -> {
                HashMap<String, Students> data = new HashMap<>();
                data.put("student", deletedStudent);

                return new ResponseEntity<>(
                    GeneralResponse.<Students>builder()
                        .success(true)
                        .message("Student deleted successfully")
                        .data(data)
                        .build(),
                    HttpStatus.ACCEPTED
                );
            })
            .onErrorResume(e -> {
                return Mono.just(
                    new ResponseEntity<>(
                        GeneralResponse.<Students>builder()
                            .success(false)
                            .message(e.getMessage())
                            .build(),
                        HttpStatus.NOT_FOUND
                    )
                );
            });
    }
}