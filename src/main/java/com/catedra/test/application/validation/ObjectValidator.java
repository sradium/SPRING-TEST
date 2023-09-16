package com.catedra.test.application.validation;

import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.catedra.test.domain.exception.HttpException;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Component
@RequiredArgsConstructor
public class ObjectValidator {

    private final Validator validator;

    @SneakyThrows
    public <T> T validate (T Object){
        Set<ConstraintViolation<T>>  violations = validator.validate(Object);
        if(!violations.isEmpty()){
            throw new HttpException(HttpStatus.BAD_REQUEST, violations.toString());
        } else {
            return Object;
        }
    }
}
