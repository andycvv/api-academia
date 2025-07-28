package com.academia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "estudiantes")
public class Estudiante {
    @Id
    private String id;
    private String nombres;
    private String apellidos;
    private String dni;
    private String edad;
}
