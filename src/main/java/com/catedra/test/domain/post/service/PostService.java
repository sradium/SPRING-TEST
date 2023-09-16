package com.catedra.test.domain.post.service;

import org.springframework.stereotype.Service;

import com.catedra.test.domain.post.repository.PostRepository;
import com.catedra.test.domain.post.entity.Post;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Flux<Post> findAll() {
        return postRepository.findAll();
    }

    public Mono<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public Mono<Post> save(Post post) {
        return postRepository.save(post);
    }

    public Mono<Post> update(Long id, Post post) {
        return postRepository.findById(id)
                .map(p -> {
                    p.setTitle(post.getTitle());
                    p.setContent(post.getContent());
                    p.setUser(post.getUser());
                    return p;
                })
                .flatMap(postRepository::save);
    }

    public Mono<Void> deleteById(Long id) {
        return postRepository.deleteById(id);
    }
}
