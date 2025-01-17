package com.imanolortiz.api_gateway.services;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouterValidator validator;

    private final JwtUtils jwtUtils;

    public AuthenticationFilter(RouterValidator validator, JwtUtils jwtUtils){
        super(Config.class);
        this.validator = validator;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();

            System.out.println("Processing request for path: " + path);
            System.out.println("Is secured? " + validator.isSecured.test(request));
            System.out.println("Headers: " + request.getHeaders());

            if (validator.isSecured.test(request)) {
                System.out.println("Secured path: " + path);

                if (authMissing(request)) {
                    System.out.println("Authorization header missing for path: " + path);
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }

                String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                } else {
                    System.out.println("Invalid authorization header for path: " + path);
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }

                if (jwtUtils.isExpired(authHeader)) {
                    System.out.println("Token expired for path: " + path);
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }

                String userId = jwtUtils.extractUserId(authHeader).toString();
                ServerHttpRequest mutatedRequest = request.mutate()
                        .header("userIdRequest", userId)
                        .build();

                return chain.filter(exchange.mutate().request(mutatedRequest).build());
            }

            System.out.println("Public path: " + path);
            return chain.filter(exchange);
        };
    }


    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private boolean authMissing(ServerHttpRequest request){
        return !request.getHeaders().containsKey("Authorization");
    }


    public static class Config{}

}