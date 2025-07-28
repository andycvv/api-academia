package com.academia.service;

import com.academia.dto.EstudianteCreacionDTO;
import com.academia.dto.EstudianteDTO;
import reactor.core.publisher.Flux;

public interface IEstudianteService extends IGenericService<EstudianteDTO, EstudianteCreacionDTO, String> {
    Flux<EstudianteDTO> findAllSorted(String sort, String direction);
}
