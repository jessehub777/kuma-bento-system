package jp.co.kuma.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetmealUserVO {
    private Long id;
    
    // カテゴリID
    private Long categoryId;
    
    // セット名
    private String name;
    
    // セット価格
    private BigDecimal price;
    
    // 説明情報
    private String description;
    
    // 画像
    private String image;
    
}
