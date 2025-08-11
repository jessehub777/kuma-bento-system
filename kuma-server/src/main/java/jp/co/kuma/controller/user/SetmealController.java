package jp.co.kuma.controller.user;

import jp.co.kuma.entity.SetmealDish;
import jp.co.kuma.result.Result;
import jp.co.kuma.service.SetmealService;
import jp.co.kuma.vo.SetmealUserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
@Slf4j
@RequiredArgsConstructor
public class SetmealController {
    private final SetmealService setmealService;
    
    /**
     * 分類idでセットのリストを取得
     *
     * @param categoryId 分類ID
     * @return 分類IDに対応するセットのリスト
     */
    @GetMapping("/list")
    public Result<List<SetmealUserVO>> listAll(@RequestParam Long categoryId) {
        List<SetmealUserVO> dishes = setmealService.listAll(categoryId);
        return Result.success(dishes);
    }
    
    
    /**
     * セットIDでセットに含まれる料理のリストを取得
     *
     * @param setmealId セットID
     * @return セットに含まれる料理のリスト
     */
    @GetMapping("/dish/list")
    public Result<List<SetmealDish>> getDishListBySetmealId(@RequestParam Long setmealId) {
        List<SetmealDish> dishes = setmealService.getDishListBySetmealId(setmealId);
        return Result.success(dishes);
    }
}
