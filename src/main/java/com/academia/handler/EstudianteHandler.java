package com.academia.handler;

import com.academia.dto.EstudianteCreacionDTO;
import com.academia.dto.EstudianteDTO;
import com.academia.service.IEstudianteService;
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
public class EstudianteHandler {
    private final IEstudianteService service;
    private final RequestValidator validator;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        String sort = request.queryParam("sort").orElse("edad");
        String direction = request.queryParam("direction").orElse("asc");

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAllSorted(sort, direction), EstudianteDTO.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findById(id), EstudianteDTO.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<EstudianteCreacionDTO> monoDTO = request.bodyToMono(EstudianteCreacionDTO.class);
        return monoDTO
                .flatMap(validator::validate)
                .flatMap(service::save)
                .flatMap(e -> ServerResponse
                        .created(URI.create(request.uri().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(e), EstudianteDTO.class)
                );
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<EstudianteCreacionDTO> monoDTO = request.bodyToMono(EstudianteCreacionDTO.class);

        return monoDTO
                .flatMap(validator::validate)
                .flatMap(e -> service.update(id, e))
                .flatMap(e -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(e), EstudianteDTO.class)
                );
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");

        return service.delete(id).then(ServerResponse
                .noContent()
                .build());
    }
}
