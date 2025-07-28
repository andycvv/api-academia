package com.academia.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteCreacionDTO {
    @NotBlank(message = "El campo Nombres es obligatorio")
    private String nombres;
    @NotBlank(message = "El campo Apellidos es obligatorio")
    private String apellidos;
    @Length(min = 8, max = 8, message = "El campo DNI debe tener 8 dígitos")
    private String dni;
    @NotNull(message = "La edad es obligatoria")
    @Min(value = 5, message = "El campo Edad debe ser como mínimo 5")
    private Integer edad;
}
