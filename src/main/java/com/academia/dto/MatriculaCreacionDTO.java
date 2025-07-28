package com.academia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatriculaCreacionDTO {
    @NotNull(message = "El campo Estudiante es obligatorio")
    private EstudianteDTO estudiante;
    @NotNull(message = "El campo Cursos es obligatorio")
    private List<CursoDTO> cursos;
    @NotNull(message = "El campo Estado es obligatorio")
    private Boolean estado;
}
