package com.academia.handler;

import com.academia.dto.MatriculaCreacionDTO;
import com.academia.dto.MatriculaDTO;
import com.academia.service.IMatriculaService;
import com.academia.validator.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class MatriculaHandler {
    private final IMatriculaService service;
    private final RequestValidator validator;

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<MatriculaCreacionDTO> monoDTO = request.bodyToMono(MatriculaCreacionDTO.class);
        return monoDTO
                .flatMap(validator::validate)
                .flatMap(service::save)
                .flatMap(e -> ServerResponse
                        .created(URI.create(request.uri().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(e), MatriculaDTO.class)
                );
    }
}
