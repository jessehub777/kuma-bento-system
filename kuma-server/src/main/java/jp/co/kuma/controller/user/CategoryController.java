package jp.co.kuma.controller.user;

import jp.co.kuma.entity.Category;
import jp.co.kuma.result.Result;
import jp.co.kuma.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userCategoryController")
@RequestMapping("/user/category")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    
    @GetMapping("/list")
    public Result<List<Category>> listAll(Integer type) {
        List<Category> categories = categoryService.listAll(type);
        return Result.success(categories);
    }
}
