package com.academia.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@NoRepositoryBean
public interface IGenericRepository<T, TID> extends ReactiveCrudRepository<T, TID> {
}
