package org.tcpx.sharine.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.entity.Category;
import org.tcpx.sharine.exception.ErrorException;
import org.tcpx.sharine.repository.CategoryRepository;
import org.tcpx.sharine.vo.CategoryVO;
import org.tcpx.sharine.vo.PageVO;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public PageVO<CategoryVO> find(ConditionDTO conditionDTO) {
        String title = conditionDTO.getKeywords();

        PageRequest pageRequest = PageRequest.of(conditionDTO.getCurrent(), conditionDTO.getSize());
        List<Category> categories = categoryRepository.findAllByTitleRegex(title, pageRequest);
        List<CategoryVO> list = categories.stream().map(CategoryVO::of).toList();

        Long count = categoryRepository.countByTitleRegex(title).longValue();

        return PageVO.<CategoryVO>builder().total(count).list(list).build();
    }

    public CategoryVO find(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ErrorException("数据错误");
        }

        return CategoryVO.of(byId.get());
    }
    public List<CategoryVO> findAll() {
        return categoryRepository.findAll().stream().map(CategoryVO::of).toList();
    }
}
