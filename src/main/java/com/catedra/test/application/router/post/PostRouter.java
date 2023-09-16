package com.catedra.test.application.router.post;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.catedra.test.application.handler.post.PostHandler;

@Configuration
public class PostRouter {

    private static final String POST = "/api/v1/post";

    private static final String ID = "/{id}";

    @Bean
    RouterFunction<ServerResponse> postRouterFunction(PostHandler handler) {
        return RouterFunctions.route()
                .GET(POST, handler::findAll)
                .GET(POST + ID, handler::findById)
                .POST(POST, handler::save)
                .PUT(POST + ID, handler::update)
                .DELETE(POST + ID, handler::deleteById)
                .build();
    }
}
