package com.currency.http;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Util {

    private static final Mono<ServerResponse> badRequest = ServerResponse.badRequest().build();
    private static final Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    public static Mono<ServerResponse> sendOk(Mono<?> publisher, Class clazz) {
        return ServerResponse.ok()
                .body(BodyInserters.fromPublisher(publisher, clazz))
                .switchIfEmpty(notFound);
    }

    public static Mono<ServerResponse> sendOkList(Flux<?> publisher, Class clazz) {
        return ServerResponse.ok()
                .body(BodyInserters.fromPublisher(publisher, clazz));
    }

}
