package org.tcpx.sharine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.service.CategoryService;

@RestController("/category")
public class CategoryController extends BaseController {

    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/find")
    public Object find(ConditionDTO conditionDTO) {
        return ok(categoryService.find(conditionDTO));
    }

    @GetMapping("/{categoryId}/videos")
    public Object findVideos(@PathVariable Long categoryId, ConditionDTO conditionDTO) {
        conditionDTO.setId(categoryId);

        return ok(categoryService.findVideos(conditionDTO));
    }
}
