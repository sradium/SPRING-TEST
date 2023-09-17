package com.catedra.test.domain.user.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.catedra.test.domain.post.entity.Post;
import com.catedra.test.domain.user.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long>{

    Mono<User> findByEmail(String email);

    @Query("SELECT * FROM users WHERE id != :id AND email = :email")
    Mono<User> findExistingEmail(Long id, String email);

    @Query("SELECT * FROM posts WHERE user_id = :id")
    Flux<Post> findPostsByUserId(Long id);
}
