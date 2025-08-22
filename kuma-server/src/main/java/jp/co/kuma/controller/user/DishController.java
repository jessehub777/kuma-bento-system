package jp.co.kuma.controller.user;

import jp.co.kuma.result.Result;
import jp.co.kuma.service.DishService;
import jp.co.kuma.vo.DishUserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;
    
    /**
     * 分類idで料理のリストを取得
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @Cacheable(cacheNames = "dishList", key = "#categoryId")
    public Result<List<DishUserVO>> listAll(@RequestParam Long categoryId) {
        List<DishUserVO> dishes = dishService.listAll(categoryId);
        return Result.success(dishes);
    }
}
