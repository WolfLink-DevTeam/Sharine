package org.wolflink.sharine.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.action.ObjectParseAction;
import org.wolflink.sharine.dto.ConditionDTO;
import org.wolflink.sharine.entity.Category;
import org.wolflink.sharine.exception.ErrorException;
import org.wolflink.sharine.repository.CategoryRepository;
import org.wolflink.sharine.vo.CategoryVO;
import org.wolflink.sharine.vo.PageVO;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ObjectParseAction objectParseAction;

    public PageVO<CategoryVO> find(ConditionDTO conditionDTO) {
        String title = conditionDTO.getKeywords();

        PageRequest pageRequest = PageRequest.of(conditionDTO.getCurrent(), conditionDTO.getSize());
        List<Category> categories = categoryRepository.findAllByTitleRegex(title, pageRequest);
        List<CategoryVO> list = categories.stream().map(objectParseAction::parse).toList();

        Long count = categoryRepository.countByTitleRegex(title).longValue();

        return PageVO.<CategoryVO>builder().total(count).list(list).build();
    }

    public CategoryVO find(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ErrorException("数据错误");
        }
        return objectParseAction.parse(byId.get());
    }
    public List<CategoryVO> findAll() {
        return categoryRepository.findAll().stream().map(objectParseAction::parse).toList();
    }
}
