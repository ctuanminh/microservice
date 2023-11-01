package com.fatechjsc.apigateway.configs;

import com.fatechjsc.apigateway.exceptions.ResponseException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.*;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.net.ConnectException;
import java.util.function.Supplier;

@Component
public class ApiGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).onErrorResume(Exception.class, ex -> handleError(ex, exchange));
    }

    private Mono<Void> handleError(Throwable ex, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        response.getHeaders().add("Content-Type", "application/json");
        String errorMessage = "Có lỗi trong quá trình sử dụng";

        if (ex instanceof ServerWebInputException) {
            errorMessage = "Lỗi dữ liệu đầu vào không hợp lệ"; // Thay đổi thông báo cho lỗi cụ thể
        }

        // Tạo một đối tượng JSON chứa thông báo lỗi
        String errorResponse = "{\n"
                + "  \"message\":\"" + errorMessage + "\",\n"
                + "  \"status\":\"" + HttpStatus.INTERNAL_SERVER_ERROR + "\"\n"
                + "}";
        byte[] errorResponseBytes = errorResponse.getBytes();
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        DataBuffer buffer = dataBufferFactory.wrap(errorResponseBytes);
        return response.writeWith(Mono.just(buffer));
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
