package com.academia.mapper;

import com.academia.dto.UsuarioCreacionDTO;
import com.academia.dto.UsuarioDTO;
import com.academia.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends GenericMapper<Usuario, UsuarioDTO, UsuarioCreacionDTO, String> {
}
