package com.academia.service.impl;

import com.academia.dto.EstudianteCreacionDTO;
import com.academia.dto.EstudianteDTO;
import com.academia.exception.ResourceNotFoundException;
import com.academia.mapper.EstudianteMapper;
import com.academia.mapper.GenericMapper;
import com.academia.model.Estudiante;
import com.academia.repository.IEstudianteRepository;
import com.academia.repository.IGenericRepository;
import com.academia.service.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl extends GenericServiceImpl<
        Estudiante,
        EstudianteDTO,
        EstudianteCreacionDTO,
        String> implements IEstudianteService {

    private final IEstudianteRepository repo;
    private final EstudianteMapper mapper;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    protected IGenericRepository<Estudiante, String> repo() {
        return repo;
    }

    @Override
    protected GenericMapper<Estudiante, EstudianteDTO, EstudianteCreacionDTO, String> mapper() {
        return mapper;
    }

    @Override
    public Flux<EstudianteDTO> findAllSorted(String sort, String direction) {
        Sort sortOrder = Sort.by(Sort.Direction.fromString(direction), sort);
        Query query = new Query().with(sortOrder);

        return reactiveMongoTemplate.find(query, Estudiante.class)
                .map(mapper::toDTO);
    }
}
