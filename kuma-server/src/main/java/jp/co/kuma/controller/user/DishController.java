package jp.co.kuma.controller.user;

import jp.co.kuma.entity.Dish;
import jp.co.kuma.result.Result;
import jp.co.kuma.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    
    @GetMapping("/list")
    public Result<List<Dish>> listAll(@RequestParam Long categoryId) {
        List<Dish> dishes = dishService.listAll(categoryId);
        return Result.success(dishes);
    }
}
