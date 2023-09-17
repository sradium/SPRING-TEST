package com.catedra.test;

import com.catedra.test.application.validation.user.UserDto;
import com.catedra.test.domain.user.entity.User;
import com.catedra.test.domain.user.service.UserService;

import reactor.test.StepVerifier;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;


import com.catedra.test.domain.user.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldReturnUser() {
        UserDto user = new UserDto();
        user.setName("John Doe");
        user.setEmail("JohnDoe@test.com");
        user.setPassword("12345678");

        StepVerifier.create(userService.save(user))
                .expectNextMatches(createdUser -> createdUser.getId() != null && createdUser.getName().equals("John Doe")
                        && createdUser.getEmail().equals("JohnDoe@test.com") && createdUser.getPassword().equals("12345678")).verifyComplete();
    }

    @Test
    public void shouldDeleteUser() {
        Mono<User> user = userRepository.findByEmail("JohnDoe@test.com");
        StepVerifier.create(userService.deleteById(user.block().getId())).verifyComplete();
    }
}
