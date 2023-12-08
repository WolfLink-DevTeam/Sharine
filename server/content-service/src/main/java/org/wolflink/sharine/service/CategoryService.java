package org.wolflink.sharine.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.entity.Category;
import org.wolflink.sharine.exception.ErrorException;
import org.wolflink.sharine.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public List<Category> find(String keywords, Integer current, Integer size) {
        PageRequest pageRequest = PageRequest.of(current, size);
        return categoryRepository.findAllByTitleRegex(keywords, pageRequest);
    }

    public Category find(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ErrorException("数据错误");
        }
        return byId.get();
    }
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
