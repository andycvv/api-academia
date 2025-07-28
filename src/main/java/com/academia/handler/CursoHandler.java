package com.academia.handler;

import com.academia.dto.CursoCreacionDTO;
import com.academia.dto.CursoDTO;
import com.academia.service.ICursoService;
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
public class CursoHandler {
    private final ICursoService service;
    private final RequestValidator validator;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), CursoDTO.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findById(id), CursoDTO.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<CursoCreacionDTO> monoDTO = request.bodyToMono(CursoCreacionDTO.class);
        return monoDTO
                .flatMap(validator::validate)
                .flatMap(service::save)
                .flatMap(e -> ServerResponse
                        .created(URI.create(request.uri().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(e), CursoDTO.class)
                );
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<CursoCreacionDTO> monoDTO = request.bodyToMono(CursoCreacionDTO.class);

        return monoDTO
                .flatMap(validator::validate)
                .flatMap(e -> service.update(id, e))
                .flatMap(e -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(e), CursoDTO.class)
                );
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");

        return service.delete(id).then(ServerResponse
                .noContent()
                .build());
    }
}
