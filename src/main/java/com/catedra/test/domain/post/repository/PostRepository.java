package com.catedra.test.domain.post.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.catedra.test.domain.post.entity.Post;

@Repository
public interface PostRepository extends ReactiveCrudRepository<Post, Long> {
}
