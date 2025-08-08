package jp.co.kuma.controller.admin;

import jp.co.kuma.dto.SetmealDTO;
import jp.co.kuma.dto.SetmealPageQueryDTO;
import jp.co.kuma.result.PageResult;
import jp.co.kuma.result.Result;
import jp.co.kuma.service.SetmealService;
import jp.co.kuma.vo.SetmealPageQueryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * セット管理
 */
@RestController("adminSetmealController")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/setmeal")
public class SetmealController {
    
    private final SetmealService setmealService;
    
    /**
     * 新規セットを追加します
     */
    @PostMapping
    public Result<String> create(@RequestBody SetmealDTO setmealDTO) {
        setmealService.createWithDish(setmealDTO);
        return Result.success();
    }
    
    /**
     * セットをページングクエリします
     */
    @GetMapping("/page")
    public Result<PageResult<SetmealPageQueryVO>> list(SetmealPageQueryDTO dto) {
        dto.setOffset((dto.getPage() - 1) * dto.getPageSize());
        List<SetmealPageQueryVO> list = setmealService.list(dto);
        long total = setmealService.total(dto);
        PageResult<SetmealPageQueryVO> pageResult = new PageResult<>(total, list);
        return Result.success(pageResult);
    }
    
    /**
     * セットを削除します
     * DELETEリクエスト パラメータはURLにあり、@RequestBodyは使用できません
     *
     * @param ids 削除するセットのIDリスト
     */
    @DeleteMapping
    public Result<String> deleteBatch(@RequestParam List<Long> ids) {
        setmealService.deleteBatch(ids);
        return Result.success();
    }
    
    /**
     * セットを更新します
     *
     * @param setmealDTO
     * @return
     */
    @PatchMapping()
    public Result<String> update(@RequestBody SetmealDTO setmealDTO) {
        setmealService.updateById(setmealDTO);
        return Result.success();
    }
    
    /**
     * セットをIDで取得します
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SetmealDTO> getById(@PathVariable Long id) {
        SetmealDTO setmealDTO = setmealService.getById(id);
        return Result.success(setmealDTO);
    }
    
}
