package com.academia.service.impl;

import com.academia.dto.MatriculaCreacionDTO;
import com.academia.dto.MatriculaDTO;
import com.academia.mapper.MatriculaMapper;
import com.academia.mapper.GenericMapper;
import com.academia.model.Matricula;
import com.academia.repository.IMatriculaRepository;
import com.academia.repository.IGenericRepository;
import com.academia.service.IMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl extends GenericServiceImpl<
        Matricula,
        MatriculaDTO,
        MatriculaCreacionDTO,
        String> implements IMatriculaService {

    private final IMatriculaRepository repo;
    private final MatriculaMapper mapper;

    @Override
    protected IGenericRepository<Matricula, String> repo() {
        return repo;
    }

    @Override
    protected GenericMapper<Matricula, MatriculaDTO, MatriculaCreacionDTO, String> mapper() {
        return mapper;
    }
}
