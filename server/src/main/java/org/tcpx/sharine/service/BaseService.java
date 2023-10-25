package org.tcpx.sharine.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class BaseService<T, ID> {

    protected JpaRepository<T, ID> repository;

    public void insert(T entity) {
        repository.save(entity);
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }

    public Optional<T> find(ID id) {
        return repository.findById(id);
    }
}
