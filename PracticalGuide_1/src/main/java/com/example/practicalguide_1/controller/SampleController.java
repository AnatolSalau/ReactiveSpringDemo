package com.example.practicalguide_1.controller;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
public class SampleController {
    @GetMapping("/sample1")
    Mono<String> sample1() {
        return Mono.just("Hello world");
    }

    @GetMapping(value = "/sample3", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<Integer> sample3() {
        return Flux
                .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .delayElements(Duration.ofSeconds(1L))
                .log();
    }

    @GetMapping(value = "/sample2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<ServerSentEvent<String>> sample2() {
        Flux<Integer> integerFlux = Flux
                .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .delayElements(Duration.ofSeconds(1L));

        Flux<ServerSentEvent<String>> message = integerFlux
                .map(i -> {
                            return ServerSentEvent.<String>builder()
                                    .id(String.valueOf(i))
                                    .event("message")
                                    .data("Event #" + i + " at " + LocalDateTime.now())
                                    .build();
                        }

                )
                .log();
        return message;
    }
}