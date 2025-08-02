package jp.co.kuma.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 注文概要データ
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderOverViewVO implements Serializable {
    // 受注待ち件数
    private Integer waitingOrders;
    
    // 配達待ち件数
    private Integer deliveredOrders;
    
    // 完了件数
    private Integer completedOrders;
    
    // キャンセル件数
    private Integer cancelledOrders;
    
    // 全注文数
    private Integer allOrders;
}
