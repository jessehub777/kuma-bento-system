package jp.co.kuma.controller.user;

import jp.co.kuma.result.Result;
import jp.co.kuma.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userShopController")
@RequestMapping("/user/shop")
@Slf4j
@RequiredArgsConstructor
public class ShopController {
    
    private final ShopService shopService;
    
    /**
     * 店の営業状態を取得します。 Redis から
     */
    @GetMapping("/status")
    public Result<String> getStatus() {
        String status = shopService.getStatus();
        return Result.success(status);
    }
}
