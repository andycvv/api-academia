package com.academia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDTO {
    private String id;
    private LocalDateTime fechaMatricula;
    private EstudianteDTO estudiante;
    private List<CursoDTO> cursos;
    private Boolean estado;
}
