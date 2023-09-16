package com.catedra.test.application.handler.user;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

//TODO: Change the user entity to user response
import com.catedra.test.domain.post.entity.Post;
import com.catedra.test.domain.user.entity.User;
import com.catedra.test.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().
            contentType(MediaType.APPLICATION_JSON).
            body(userService.findAll(), User.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return ServerResponse.ok().
            contentType(MediaType.APPLICATION_JSON).
            body(userService.findById(id), User.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        return userMono.flatMap(user ->
            ServerResponse.ok().
                contentType(MediaType.APPLICATION_JSON).
                body(userService.save(user), User.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        Mono<User> userMono = request.bodyToMono(User.class);
        return userMono.flatMap(user ->
            ServerResponse.ok().
                contentType(MediaType.APPLICATION_JSON).
                body(userService.update(id, user), User.class));
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return ServerResponse.ok().
            contentType(MediaType.APPLICATION_JSON).
            body(userService.deleteById(id), Void.class);
    }

    public Mono<ServerResponse> findPostsById(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return ServerResponse.ok().
            contentType(MediaType.APPLICATION_JSON).
            body(userService.findPostsById(id), Post.class);
    }
}
