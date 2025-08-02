package jp.co.kuma.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 料理
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    // 料理名
    private String name;
    
    // 料理カテゴリID
    private Long categoryId;
    
    // 料理価格
    private BigDecimal price;
    
    // 画像
    private String image;
    
    // 説明情報
    private String description;
    
    // 0:販売停止 1:販売中
    private Integer status;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
    
    private Long createUser;
    
    private Long updateUser;
    
}
