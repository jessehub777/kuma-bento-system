package jp.co.kuma.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 套餐
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Setmeal implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    // カテゴリID
    private Long categoryId;
    
    // セット名
    private String name;
    
    // セット価格
    private BigDecimal price;
    
    // ステータス 0:無効 1:有効
    private Integer status;
    
    private String description;
    
    private String image;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
    
    private Long createUser;
    
    private Long updateUser;
}
