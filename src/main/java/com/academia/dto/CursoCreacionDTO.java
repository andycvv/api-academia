package com.academia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoCreacionDTO {
    @NotBlank(message = "El campo Nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El campo Siglas es obligatorio")
    private String siglas;
    @NotNull(message = "El campo Estado es obligatorio")
    private Boolean estado;
}
