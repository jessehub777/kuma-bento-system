package jp.co.kuma.service.impl;

import jp.co.kuma.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    
    private final StringRedisTemplate redisTemplate;
    
    
    /**
     * 店の営業状態を更新します。
     *
     * @param status 状態値
     */
    public void updateStatus(String status) {
        redisTemplate.opsForValue().set("SHOP_STATUS", status);
    }
    
    /**
     * 店の営業状態を取得します。
     *
     * @return 状態値
     */
    public String getStatus() {
        return redisTemplate.opsForValue().get("SHOP_STATUS");
    }
}
