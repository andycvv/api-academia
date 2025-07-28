package com.academia.mapper;

import com.academia.dto.CursoDTO;
import com.academia.dto.EstudianteDTO;
import com.academia.dto.MatriculaCreacionDTO;
import com.academia.dto.MatriculaDTO;
import com.academia.model.Curso;
import com.academia.model.Estudiante;
import com.academia.model.Matricula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { CursoMapper.class, EstudianteMapper.class })
public interface MatriculaMapper extends GenericMapper<
        Matricula,
        MatriculaDTO,
        MatriculaCreacionDTO,
        String> {
    @Override
    @Mapping(target = "fechaMatricula", expression = "java(java.time.LocalDateTime.now())")
    Matricula toEntity(MatriculaCreacionDTO matriculaCreacionDTO);

    @Override
    @Mapping(target = "id", source = "id")
    Matricula toEntity(String id, MatriculaCreacionDTO dto);
}
