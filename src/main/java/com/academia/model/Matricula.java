package com.academia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "matriculas")
public class Matricula {
    @Id
    private String id;
    private LocalDateTime fechaMatricula;
    private Estudiante estudiante;
    private List<Curso> cursos;
    private Boolean estado;
}
