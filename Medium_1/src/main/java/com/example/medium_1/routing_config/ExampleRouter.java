package com.example.medium_1.routing_config;

import com.example.medium_1.routing_handler.ExampleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ExampleRouter {
    @Bean
    public RouterFunction<ServerResponse> routeExample (ExampleHandler exampleHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/example").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), exampleHandler::hello);
    }
}
