package org.wolflink.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wolflink.sharine.controller.BaseController;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.service.CategoryService;

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
     * 获取全部分类信息
     *
     * @return 分区数据
     */
    @GetMapping("/all")
    public ResultPack findAll() {
        return ok(categoryService.findAll());
    }
}
