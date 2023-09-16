package com.catedra.test.application.router.user;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.catedra.test.application.handler.user.UserHandler;

@Configuration
public class UserRouter {

    private static final String USER = "/api/v1/user";

    private static final String ID = "/{id}";

    private static final String POSTS = "/posts";

    @Bean
    WebProperties.Resources userResources() {
        return new WebProperties.Resources();
    }

    @Bean
    RouterFunction<ServerResponse> userRouterFunction(UserHandler handler) {
        return RouterFunctions.route()
                .GET(USER, handler::findAll)
                .GET(USER + ID, handler::findById)
                .POST(USER, handler::save)
                .PUT(USER + ID, handler::update)
                .DELETE(USER + ID, handler::deleteById)
                .GET(USER + ID + POSTS, handler::findPostsById)
                .build();
    }
}
