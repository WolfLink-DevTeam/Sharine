package org.tcpx.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.service.CategoryService;

/**
 * 视频分类控制器
 * 包含以下接口：
 * 根据页码和视频类别分页检索视频信息
 */
@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController extends BaseController {

    final CategoryService categoryService;

    /**
     * 搜索分类信息
     *
     * @param conditionDTO 查询条件
     * @return 分区数据
     */
    @GetMapping
    public Object find(ConditionDTO conditionDTO) {
        return ok(categoryService.find(conditionDTO));
    }
    @GetMapping("/all")
    public Object findAll() {
        return ok(categoryService.findAll());
    }
}
