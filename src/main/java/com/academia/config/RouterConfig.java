package com.academia.config;

import com.academia.handler.CursoHandler;
import com.academia.handler.EstudianteHandler;
import com.academia.handler.MatriculaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> cursoRutas(CursoHandler handler) {
        return route(GET("/v2/cursos"), handler::findAll)
                .andRoute(GET("/v2/cursos/{id}"), handler::findById)
                .andRoute(POST("/v2/cursos"), handler::save)
                .andRoute(PUT("/v2/cursos/{id}"), handler::update)
                .andRoute(DELETE("/v2/cursos/{id}"), handler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> estudianteRutas(EstudianteHandler handler) {
        return route(GET("/v2/estudiantes"), handler::findAll)
                .andRoute(GET("/v2/estudiantes/{id}"), handler::findById)
                .andRoute(POST("/v2/estudiantes"), handler::save)
                .andRoute(PUT("/v2/estudiantes/{id}"), handler::update)
                .andRoute(DELETE("/v2/estudiantes/{id}"), handler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> matriculaRutas(MatriculaHandler handler) {
        return route(POST("/v2/matriculas"), handler::save);
    }
}
