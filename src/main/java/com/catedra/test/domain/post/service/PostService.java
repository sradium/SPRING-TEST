package com.catedra.test.domain.post.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.catedra.test.domain.post.repository.PostRepository;
import com.catedra.test.application.validation.post.PostDto;
import com.catedra.test.domain.exception.HttpException;
import com.catedra.test.domain.post.entity.Post;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final static String POST_NOT_FOUND = "Post not found";

    public Flux<Post> findAll() {
        return postRepository.findAll();
    }

    public Mono<Post> findById(Long id) {
        return postRepository.findById(id)
            .switchIfEmpty(Mono.error(new HttpException(HttpStatus.NOT_FOUND, POST_NOT_FOUND)));
    }

    public Mono<Post> save(PostDto post) {
        return postRepository.save(Post.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .user_id(post.getUser_id())
            .build());
    }

    public Mono<Post> update(Long id, PostDto post) {
        return postRepository.findById(id)
                .map(p -> {
                    p.setTitle(post.getTitle());
                    p.setContent(post.getContent());
                    return p;
                })
                .flatMap(postRepository::save).switchIfEmpty(Mono.error(new HttpException(HttpStatus.NOT_FOUND, POST_NOT_FOUND)));
    }

    public Mono<Void> deleteById(Long id) {
        Mono<Boolean> postExists = postRepository.findById(id).hasElement();
        return postExists.flatMap(exists -> exists ? postRepository.deleteById(id) : Mono.error(new HttpException(HttpStatus.NOT_FOUND, POST_NOT_FOUND)));
    }
}
