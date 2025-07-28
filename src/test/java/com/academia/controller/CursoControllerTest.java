package com.academia.controller;

import com.academia.config.WebSecurityConfigTest;
import com.academia.dto.CursoCreacionDTO;
import com.academia.dto.CursoDTO;
import com.academia.model.Curso;
import com.academia.service.ICursoService;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@WebFluxTest(controllers = CursoController.class)
@Import(WebSecurityConfigTest.class)
public class CursoControllerTest {
    @Autowired
    private WebTestClient client;
    @MockitoBean
    private ICursoService service;
    @MockitoBean
    private WebProperties.Resources resources;

    private Curso c1;
    private Curso c2;
    private CursoDTO c1DTO;
    private CursoDTO c2DTO;
    private CursoCreacionDTO c1CreacionDTO;
    private CursoCreacionDTO c2CreacionDTO;
    private List<CursoDTO> cursos;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        c1 = new Curso("1", "Gestion", "GN", true);
        c2 = new Curso("2", "Analisis", "AS", true);
        c1DTO = new CursoDTO("1", "Gestion", "GN", true);
        c2DTO = new CursoDTO("2", "Analisis", "AS", true);
        c1CreacionDTO = new CursoCreacionDTO("Gestion", "GN", true);
        c2CreacionDTO = new CursoCreacionDTO("Analisis", "AS", true);
        cursos = Arrays.asList(c1DTO, c2DTO);

        Mockito.when(service.findAll()).thenReturn(Flux.fromIterable(cursos));
        Mockito.when(service.findById(c1.getId())).thenReturn(Mono.just(c1DTO));
        Mockito.when(service.save(c1CreacionDTO)).thenReturn(Mono.just(c1DTO));
        Mockito.when(service.update(c1.getId(), c1CreacionDTO)).thenReturn(Mono.just(c1DTO));
        Mockito.when(service.delete(c1.getId())).thenReturn(Mono.empty());
    }

    @Test
    public void findAll() {
        client.get()
                .uri("/cursos")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CursoDTO.class)
                .hasSize(2)
                .contains(c1DTO, c2DTO);
    }

    @Test
    public void findById() {
        client.get()
                .uri("/cursos/".concat(c1.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CursoDTO.class);
    }

    @Test
    public void save() {
        client.post()
                .uri("/cursos")
                .body(Mono.just(c1CreacionDTO), CursoCreacionDTO.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CursoDTO.class);
    }

    @Test
    public void update() {
        client.put()
                .uri("/cursos/".concat(c1.getId()))
                .body(Mono.just(c1CreacionDTO), CursoCreacionDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CursoDTO.class);
    }

    @Test
    public void delete() {
        client.delete()
                .uri("/cursos/".concat(c1.getId()))
                .exchange()
                .expectStatus().isNoContent();
    }
}
