package com.academia.mapper;

import com.academia.dto.CursoCreacionDTO;
import com.academia.dto.CursoDTO;
import com.academia.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CursoMapper extends GenericMapper<
        Curso,
        CursoDTO,
        CursoCreacionDTO,
        String> {

    @Override
    @Mapping(target = "id", source = "id")
    Curso toEntity(String id, CursoCreacionDTO dto);
}
