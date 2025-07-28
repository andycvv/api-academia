package com.academia.service;

import com.academia.dto.MatriculaCreacionDTO;
import com.academia.dto.MatriculaDTO;
import reactor.core.publisher.Flux;

public interface IMatriculaService extends IGenericService<MatriculaDTO, MatriculaCreacionDTO, String> {
}
