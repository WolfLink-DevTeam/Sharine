package org.wolflink.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;

@AllArgsConstructor
public abstract class RestfulController<T,ID> extends BaseController {

    private final JpaRepository<T,ID> repository;
    @GetMapping("/{id}")
    public T get(@PathVariable ID id) {
        return repository.findById(id).orElseThrow(()->new WarnException(StatusCodeEnum.DATA_NOT_EXIST));
    }
    @PostMapping
    ResultPack post(@RequestBody T t) {
        repository.save(t);
        return ok();
    }
    @DeleteMapping("/{id}")
    ResultPack del(@PathVariable ID id) {
        repository.deleteById(id);
        return ok();
    }
}
