package com.academia.mapper;

import com.academia.dto.RolCreacionDTO;
import com.academia.dto.RolDTO;
import com.academia.model.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper extends GenericMapper<Rol, RolDTO, RolCreacionDTO, String> {
}
