package com.academia.mapper;


public interface GenericMapper<T, TDO, TCreacionDTO, TID> {
    TDO toDTO(T estudiante);
    T toEntity(TCreacionDTO dto);
    T toEntity(TID id, TCreacionDTO dto);
}
