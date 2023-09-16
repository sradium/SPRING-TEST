package com.catedra.test.application.handler.post;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.catedra.test.domain.post.entity.Post;
import com.catedra.test.domain.post.service.PostService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostHandler {

    private final PostService postService;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().
            contentType(MediaType.APPLICATION_JSON).
            body(postService.findAll(), Post.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return ServerResponse.ok().
            contentType(MediaType.APPLICATION_JSON).
            body(postService.findById(id), Post.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Post> postMono = request.bodyToMono(Post.class);
        return postMono.flatMap(post ->
            ServerResponse.ok().
                contentType(MediaType.APPLICATION_JSON).
                body(postService.save(post), Post.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        Mono<Post> postMono = request.bodyToMono(Post.class);
        return postMono.flatMap(post ->
            ServerResponse.ok().
                contentType(MediaType.APPLICATION_JSON).
                body(postService.update(id, post), Post.class));
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return ServerResponse.ok().
            contentType(MediaType.APPLICATION_JSON).
            body(postService.deleteById(id), Void.class);
    }
}
