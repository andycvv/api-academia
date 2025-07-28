package com.academia.controller;

import com.academia.config.WebSecurityConfigTest;
import com.academia.dto.CursoDTO;
import com.academia.dto.EstudianteDTO;
import com.academia.dto.MatriculaCreacionDTO;
import com.academia.dto.MatriculaDTO;
import com.academia.service.IMatriculaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@WebFluxTest(controllers = MatriculaController.class)
@Import(WebSecurityConfigTest.class)
public class MatriculaControllerTest {
    @Autowired
    private WebTestClient client;

    @MockitoBean
    private IMatriculaService service;

    @MockitoBean
    private WebProperties.Resources resources;

    private MatriculaDTO matriculaDTO;
    private MatriculaCreacionDTO matriculaCreacionDTO;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        EstudianteDTO estudiante = new EstudianteDTO();
        estudiante.setId("123");
        CursoDTO curso1 = new CursoDTO();
        curso1.setId("10");
        CursoDTO curso2 = new CursoDTO();
        curso2.setId("11");

        matriculaDTO = new MatriculaDTO("100", LocalDateTime.now(), estudiante, List.of(curso1, curso2), true);
        matriculaCreacionDTO = new MatriculaCreacionDTO(estudiante, List.of(curso1,curso2), true);

        Mockito.when(service.save(matriculaCreacionDTO)).thenReturn(Mono.just(matriculaDTO));
    }

    @Test
    public void saveTest() {
        client.post()
                .uri("/matriculas")
                .body(Mono.just(matriculaCreacionDTO), MatriculaCreacionDTO.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(MatriculaDTO.class);
    }
}
