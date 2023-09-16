package com.catedra.test.domain.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;

@Component
public class HttpErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, options);
        Throwable throwable = getError(request);
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            errorAttributes.put("status", httpException.getHttpStatus().value());
            errorAttributes.put("error", httpException.getHttpStatus().getReasonPhrase());
            errorAttributes.put("message", httpException.getMessage());
        }
        return errorAttributes;
    }
}
