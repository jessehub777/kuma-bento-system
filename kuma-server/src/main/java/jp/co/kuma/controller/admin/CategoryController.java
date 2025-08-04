package jp.co.kuma.controller.admin;

import jp.co.kuma.dto.CategoryDTO;
import jp.co.kuma.dto.CategoryPageQueryDTO;
import jp.co.kuma.entity.Category;
import jp.co.kuma.result.PageResult;
import jp.co.kuma.result.Result;
import jp.co.kuma.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 社員コントローラー
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
@RequiredArgsConstructor()
public class CategoryController {
    
    private final CategoryService categoryService;
    
    /**
     * カテゴリー新規作成
     *
     * @param categoryDTO カテゴリー情報
     * @return 成功メッセージ
     */
    @PostMapping
    public Result<String> create(@RequestBody CategoryDTO categoryDTO) {
        categoryService.create(categoryDTO);
        return Result.success();
    }
    
    /**
     * カテゴリー　リストを取得
     *
     * @param dto ページ番号 ページサイズ
     */
    @GetMapping("/page")
    public Result<PageResult<Category>> list(CategoryPageQueryDTO dto) {
        int offset = (dto.getPage() - 1) * dto.getPageSize();
        
        List<Category> list = categoryService.list(offset, dto.getPageSize(), dto.getName(), dto.getType());
        int total = categoryService.count(dto.getName(), dto.getType());
        
        // ページ結果を作成
        PageResult<Category> pageResult = new PageResult<>(total, list);
        return Result.success(pageResult);
    }
    
    /**
     * カテゴリーを更新
     *
     * @param id カテゴリーID
     * @return 成功メッセージ
     */
    @PatchMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody Category category) {
        categoryService.update(category);
        return Result.success();
    }
    
    /**
     * カテゴリーを取得
     *
     * @param id カテゴリーID
     * @return カテゴリー情報
     */
    @GetMapping("/{id}")
    public Result<Category> get(@PathVariable Long id) {
        Category category = categoryService.get(id);
        return Result.success(category);
    }
    
    /**
     * カテゴリーを削除
     *
     * @param id カテゴリーID
     * @return 成功メッセージ
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam Long id) {
        categoryService.delete(id);
        return Result.success("カテゴリーを削除しました");
    }
    
    
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type) {
        List<Category> list = categoryService.list(0, 10000, null, type);
        return Result.success(list);
    }
    
    
}

