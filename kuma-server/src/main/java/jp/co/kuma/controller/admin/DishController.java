package jp.co.kuma.controller.admin;


import jp.co.kuma.dto.DishDTO;
import jp.co.kuma.dto.DishPageQueryDTO;
import jp.co.kuma.entity.Dish;
import jp.co.kuma.result.PageResult;
import jp.co.kuma.result.Result;
import jp.co.kuma.service.DishService;
import jp.co.kuma.vo.DishVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品管理
 */
@RestController("adminDishController")
@RequiredArgsConstructor
@RequestMapping("/admin/dish")
public class DishController {
    
    private final DishService dishService;
    
    /**
     * 料理を新規作成
     */
    @PostMapping()
    public Result<String> insertWithSpec(@RequestBody DishDTO dishDTO) {
        dishService.insertWithSpec(dishDTO);
        return Result.success();
    }
    
    /**
     * 料理のページネーション　クエリー
     */
    @GetMapping("/page")
    public Result<PageResult<DishVO>> listPage(DishPageQueryDTO dto) {
        int offset = (dto.getPage() - 1) * dto.getPageSize();
        
        List<DishVO> list = dishService.listPage(offset, dto.getPageSize(), dto.getName(), dto.getCategoryId(), dto.getStatus());
        int total = dishService.count(dto.getName(), dto.getCategoryId(), dto.getStatus());
        
        // ページ結果を作成
        PageResult<DishVO> pageResult = new PageResult<>(total, list);
        return Result.success(pageResult);
    }
    
    /**
     * 料理の一括削除
     * f　@RequestParam List<Long> ids     <=   1，2，3
     */
    @DeleteMapping()
    public Result<String> delete(@RequestParam List<Long> ids) {
        dishService.deleteBatch(ids);
        return Result.success();
    }
    
    /**
     * 料理の詳細を取得
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<DishDTO> getByIdWithSpec(@PathVariable Long id) {
        DishDTO dishDTO = dishService.getByIdWithSpec(id);
        return Result.success(dishDTO);
    }
    
    /**
     * 料理の更新
     *
     * @param dishDTO
     * @return
     */
    @PatchMapping()
    public Result<String> update(@RequestBody DishDTO dishDTO) {
        dishService.updateWithSpec(dishDTO);
        return Result.success();
    }
    
    /**
     * カテゴリIDに基づいて料理のリストを取得
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    public Result<List<Dish>> listByCategoryId(Long categoryId) {
        List<Dish> list = dishService.listByCategoryId(categoryId);
        return Result.success(list);
    }
    
    /**
     * 料理の状態を更新
     *
     * @param id
     * @param status
     * @return
     */
    @PatchMapping("/status/{id}")
    public Result<String> status(@PathVariable Integer id, @RequestParam Integer status) {
        dishService.status(status, id);
        return Result.success();
    }
}
