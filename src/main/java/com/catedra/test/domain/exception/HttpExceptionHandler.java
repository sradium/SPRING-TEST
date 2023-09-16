package com.catedra.test.domain.exception;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class HttpExceptionHandler extends AbstractErrorWebExceptionHandler {

    public HttpExceptionHandler(ErrorAttributes errorAttributes, Resources resources,
            ApplicationContext applicationContext, ServerCodecConfigurer configuration) {
        super(errorAttributes, resources, applicationContext);
        this.setMessageReaders(configuration.getReaders());
        this.setMessageWriters(configuration.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Map<String, Object> errorAttributes = this.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        return ServerResponse.status((int) errorAttributes.get("status")).
            contentType(MediaType.APPLICATION_JSON).
            bodyValue(errorAttributes);
    }

}
