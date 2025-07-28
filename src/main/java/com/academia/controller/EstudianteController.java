package com.academia.controller;

import com.academia.dto.EstudianteCreacionDTO;
import com.academia.dto.EstudianteDTO;
import com.academia.service.IEstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {
    private final IEstudianteService service;

    @GetMapping
    public Mono<ResponseEntity<Flux<EstudianteDTO>>> findAll(
            @RequestParam(defaultValue = "edad") String sort,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return Mono.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.findAllSorted(sort, direction))
        );
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<EstudianteDTO>> findById(@PathVariable("id") String id) {
        return service.findById(id)
                .map(e ->
                    ResponseEntity
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(e)
                );
    }

    @PostMapping
    public Mono<ResponseEntity<EstudianteDTO>> save(@Valid @RequestBody EstudianteCreacionDTO dto,
                                    ServerHttpRequest request) {
        return service.save(dto)
                .map(e -> ResponseEntity
                        .created(URI.create(request.getURI().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                );
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<EstudianteDTO>> update(@PathVariable("id") String id,
                                                      @Valid @RequestBody EstudianteCreacionDTO dto) {
        return service.update(id, dto)
                .map(e -> ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                );
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return service.delete(id).thenReturn(ResponseEntity
                .noContent()
                .build()
        );
    }
}
