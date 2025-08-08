package jp.co.kuma.controller.admin;

import jp.co.kuma.result.Result;
import jp.co.kuma.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
@RequiredArgsConstructor
public class ShopController {
    
    private final ShopService shopService;
    
    /**
     * 店の営業状態を更新します。
     *
     * @param status 状態值
     * @return Result<String>
     */
    @PatchMapping("/status/{status}")
    public Result<String> updateStatus(@PathVariable String status) {
        shopService.updateStatus(status);
        return Result.success();
    }
    
    /**
     * 店の営業状態を取得します。
     */
    @GetMapping("/status")
    public Result<String> getStatus() {
        String status = shopService.getStatus();
        return Result.success(status);
    }
}
