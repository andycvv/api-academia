package com.academia.service.impl;

import com.academia.exception.ResourceNotFoundException;
import com.academia.mapper.EstudianteMapper;
import com.academia.mapper.GenericMapper;
import com.academia.model.Estudiante;
import com.academia.repository.IGenericRepository;
import com.academia.service.IGenericService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public abstract class GenericServiceImpl<
        T,
        TDTO,
        TCreacionDTO,
        TID> implements IGenericService<TDTO, TCreacionDTO, TID>{

    protected abstract IGenericRepository<T, TID> repo();
    protected abstract GenericMapper<T, TDTO, TCreacionDTO, TID> mapper();

    @Override
    public Mono<TDTO> findById(TID id) {
        return repo().findById(id)
                .map(mapper()::toDTO)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("ID no encontrado")));
    }

    @Override
    public Flux<TDTO> findAll() {
        return repo().findAll().map(mapper()::toDTO);
    }

    @Override
    public Mono<TDTO> save(TCreacionDTO dto) {
        return repo().save(mapper().toEntity(dto)).map(mapper()::toDTO);
    }

    @Override
    public Mono<TDTO> update(TID id, TCreacionDTO dto) {
        return repo().findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("ID no encontrado")))
                .flatMap(e -> repo().save(mapper().toEntity(id, dto)))
                .map(mapper()::toDTO);
    }

    @Override
    public Mono<Void> delete(TID id) {
        return repo().findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("ID no encontrado")))
                .flatMap(repo()::delete);
    }
}
