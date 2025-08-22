package jp.co.kuma.controller.user;

import jp.co.kuma.result.Result;
import jp.co.kuma.service.CategoryService;
import jp.co.kuma.vo.CategoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
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
    
    /**
     * 分類type で分類リストを取得
     *
     * @param type 分类类型
     * @return Result<List < CategoryVO>>
     */
    @GetMapping("/list")
    @Cacheable(cacheNames = "category", key = "#type") // category::1　のような形
    public Result<List<CategoryVO>> listAll(Integer type) {
        List<CategoryVO> categories = categoryService.listAll(type);
        return Result.success(categories);
    }
}
