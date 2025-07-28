package com.academia.mapper;


public interface GenericMapper<T, TDO, TCreacionDTO, TID> {
    TDO toDTO(T entidad);
    T toEntity(TCreacionDTO dto);
    T toEntity(TID id, TCreacionDTO dto);
}
