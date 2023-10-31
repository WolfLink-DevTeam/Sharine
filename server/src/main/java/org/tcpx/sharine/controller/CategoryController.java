package org.tcpx.sharine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.service.CategoryService;

@RestController("/categories")
public class CategoryController extends BaseController {

    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 搜索分类信息
     *
     * @param conditionDTO 查询条件
     * @return 分区数据
     */
    @GetMapping("")
    public Object find(ConditionDTO conditionDTO) {
        return ok(categoryService.find(conditionDTO));
    }
}
