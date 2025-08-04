package jp.co.kuma.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    // 名称
    private String name;
    
    // 注文ID
    private Long orderId;
    
    // 料理ID
    private Long dishId;
    
    // セットID
    private Long setmealId;
    
    // 味
    private String dishSpec;
    
    // 数量
    private Integer number;
    
    // 金額
    private BigDecimal amount;
    
    // 画像
    private String image;
}
