package com.academia.controller;

import com.academia.dto.CursoCreacionDTO;
import com.academia.dto.CursoDTO;
import com.academia.service.ICursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {
    private final ICursoService service;

    @GetMapping
    public Mono<ResponseEntity<Flux<CursoDTO>>> findAll() {
        return Mono.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.findAll())
        );
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CursoDTO>> findById(@PathVariable("id") String id) {
        return service.findById(id)
                .map(e ->
                    ResponseEntity
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(e)
                );
    }

    @PostMapping
    public Mono<ResponseEntity<CursoDTO>> save(@Valid @RequestBody CursoCreacionDTO dto,
                                    ServerHttpRequest request) {
        return service.save(dto)
                .map(e -> ResponseEntity
                        .created(URI.create(request.getURI().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                );
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<CursoDTO>> update(@PathVariable("id") String id,
                                                      @Valid @RequestBody CursoCreacionDTO dto) {
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
