package com.academia.service;

import com.academia.dto.EstudianteCreacionDTO;
import com.academia.dto.EstudianteDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IGenericService<TDTO, TCreacionDTO, TID> {
    Mono<TDTO> findById(TID id);
    Flux<TDTO> findAll();
    Mono<TDTO> save(TCreacionDTO dto);
    Mono<TDTO> update(TID id, TCreacionDTO dto);
    Mono<Void> delete(TID id);
}
