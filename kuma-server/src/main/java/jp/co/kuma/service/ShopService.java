package jp.co.kuma.service;


public interface ShopService {
    
    
    /**
     * 店の営業状態を更新します。
     *
     * @param status 状態值
     */
    void updateStatus(String status);
    
    /**
     * 店の営業状態を取得します。
     *
     * @return 状態值
     */
    String getStatus();
}
