package com.academia.repository;

import com.academia.model.Curso;
import com.academia.model.Estudiante;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ICursoRepository extends IGenericRepository<Curso, String> {
}
