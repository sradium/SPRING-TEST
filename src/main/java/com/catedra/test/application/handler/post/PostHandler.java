package com.catedra.test.application.handler.post;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.catedra.test.application.validation.post.PostDto;
import com.catedra.test.application.validation.validator.ObjectValidator;
import com.catedra.test.domain.post.entity.Post;
import com.catedra.test.domain.post.service.PostService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostHandler {

    private final PostService postService;
    private final ObjectValidator objectValidator;

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
        Mono<PostDto> postDtoMono = request.bodyToMono(PostDto.class)
            .doOnNext(objectValidator::validate);
        return postDtoMono.flatMap(postDto ->
            ServerResponse.ok().
                contentType(MediaType.APPLICATION_JSON).
                body(postService.save(postDto), Post.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        Mono<PostDto> postDtoMono = request.bodyToMono(PostDto.class)
            .doOnNext(objectValidator::validate);
        return postDtoMono.flatMap(postDto ->
            ServerResponse.ok().
                contentType(MediaType.APPLICATION_JSON).
                body(postService.update(id, postDto), Post.class));
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));
        return ServerResponse.ok().
            contentType(MediaType.APPLICATION_JSON).
            body(postService.deleteById(id), Void.class);
    }
}
