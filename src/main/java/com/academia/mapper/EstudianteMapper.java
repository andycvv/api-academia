package com.academia.mapper;

import com.academia.dto.EstudianteCreacionDTO;
import com.academia.dto.EstudianteDTO;
import com.academia.model.Estudiante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstudianteMapper extends GenericMapper<
        Estudiante,
        EstudianteDTO,
        EstudianteCreacionDTO,
        String> {

    @Override
    @Mapping(target = "id", source = "id")
    Estudiante toEntity(String id, EstudianteCreacionDTO dto);
}
