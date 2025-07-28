package com.academia.controller;

import com.academia.config.WebSecurityConfigTest;
import com.academia.dto.EstudianteCreacionDTO;
import com.academia.dto.EstudianteDTO;
import com.academia.model.Estudiante;
import com.academia.service.IEstudianteService;
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

@WebFluxTest(controllers = EstudianteController.class)
@Import(WebSecurityConfigTest.class)
public class EstudianteControllerTest {
    @Autowired
    private WebTestClient client;
    @MockitoBean
    private IEstudianteService service;
    @MockitoBean
    private WebProperties.Resources resources;

    private Estudiante c1;
    private Estudiante c2;
    private EstudianteDTO c1DTO;
    private EstudianteDTO c2DTO;
    private EstudianteCreacionDTO c1CreacionDTO;
    private EstudianteCreacionDTO c2CreacionDTO;
    private List<EstudianteDTO> estudiantes;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        c1 = new Estudiante("1", "Juan Pepe", "Arias Aliaga", "98293242", 19);
        c2 = new Estudiante("2", "Maria Fernanda", "Lopez Flores", "92839232", 22);
        c1DTO = new EstudianteDTO("1", "Juan Pepe", "Arias Aliaga", "98293242", 19);
        c2DTO = new EstudianteDTO("2", "Maria Fernanda", "Lopez Flores", "92839232", 22);
        c1CreacionDTO = new EstudianteCreacionDTO("Juan Pepe", "Arias Aliaga", "98293242", 19);
        c2CreacionDTO = new EstudianteCreacionDTO("Maria Fernanda", "Lopez Flores", "92839232", 22);
        estudiantes = Arrays.asList(c1DTO, c2DTO);

        Mockito.when(service.findAllSorted("edad", "asc")).thenReturn(Flux.fromIterable(estudiantes));
        Mockito.when(service.findById(c1.getId())).thenReturn(Mono.just(c1DTO));
        Mockito.when(service.save(c1CreacionDTO)).thenReturn(Mono.just(c1DTO));
        Mockito.when(service.update(c1.getId(), c1CreacionDTO)).thenReturn(Mono.just(c1DTO));
        Mockito.when(service.delete(c1.getId())).thenReturn(Mono.empty());
    }

    @Test
    public void findAllSorted() {
        client.get()
                .uri("/estudiantes?sort=edad&direction=asc")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(EstudianteDTO.class)
                .hasSize(2)
                .contains(c1DTO, c2DTO);
    }

    @Test
    public void findById() {
        client.get()
                .uri("/estudiantes/".concat(c1.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(EstudianteDTO.class);
    }

    @Test
    public void save() {
        client.post()
                .uri("/estudiantes")
                .body(Mono.just(c1CreacionDTO), EstudianteCreacionDTO.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(EstudianteDTO.class);
    }

    @Test
    public void update() {
        client.put()
                .uri("/estudiantes/".concat(c1.getId()))
                .body(Mono.just(c1CreacionDTO), EstudianteCreacionDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(EstudianteDTO.class);
    }

    @Test
    public void delete() {
        client.delete()
                .uri("/estudiantes/".concat(c1.getId()))
                .exchange()
                .expectStatus().isNoContent();
    }
}
