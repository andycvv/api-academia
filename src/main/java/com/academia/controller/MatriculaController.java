package com.academia.controller;

import com.academia.dto.MatriculaCreacionDTO;
import com.academia.dto.MatriculaDTO;
import com.academia.service.IMatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {
    private final IMatriculaService service;

    @PostMapping
    public Mono<ResponseEntity<MatriculaDTO>> save(@Valid @RequestBody MatriculaCreacionDTO dto,
                                    ServerHttpRequest request) {
        return service.save(dto)
                .map(e -> ResponseEntity
                        .created(URI.create(request.getURI().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                );
    }
}
