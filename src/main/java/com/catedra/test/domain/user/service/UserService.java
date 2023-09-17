package com.catedra.test.domain.user.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.catedra.test.domain.user.entity.User;
import com.catedra.test.domain.user.repository.UserRepository;

import com.catedra.test.application.validation.user.UserDto;
import com.catedra.test.domain.exception.HttpException;
import com.catedra.test.domain.post.entity.Post;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final static String USER_NOT_FOUND = "User not found";
    private final static String USER_ALREADY_EXISTS = "User email already exists";


    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(Long id) {
        return userRepository.findById(id).
            switchIfEmpty(Mono.error(new HttpException(HttpStatus.NOT_FOUND, USER_NOT_FOUND)));
    }

    public Mono<User> save(UserDto user) {
        Mono<Boolean> userExists = userRepository.findByEmail(user.getEmail()).hasElement();
        return userExists.flatMap(exists -> exists ? Mono.error(new HttpException(HttpStatus.BAD_REQUEST, USER_ALREADY_EXISTS)) : userRepository.save(User.builder()
            .name(user.getName())
            .email(user.getEmail())
            .password(user.getPassword())
            .build()));
    }

    public Mono<User> update(Long id, UserDto user) {
        Mono<Boolean> emailExists = userRepository.findExistingEmail(id, user.getEmail()).hasElement();
        return emailExists.flatMap(exists -> exists ? Mono.error(new HttpException(HttpStatus.BAD_REQUEST, USER_ALREADY_EXISTS)) : userRepository.findById(id)
                .map(u -> {
                    u.setName(user.getName());
                    u.setEmail(user.getEmail());
                    u.setPassword(user.getPassword());
                    return u;
                })
                .flatMap(userRepository::save).switchIfEmpty(Mono.error(new HttpException(HttpStatus.NOT_FOUND, USER_NOT_FOUND))));
    }

    public Mono<Void> deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    public Flux<Post> findPostsById(Long id) {
        return userRepository.findPostsByUserId(id);
    }
}
