package jp.co.kuma.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 套餐菜品关系
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetmealDish implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    // セットID
    private Long setmealId;
    
    // 料理ID
    private Long dishId;
    
    // 料理名（冗長フィールド）
    private String name;
    
    // 料理元価格
    private BigDecimal price;
    
    // 数量
    private Integer copies;
}
