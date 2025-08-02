package jp.co.kuma.vo;

import jp.co.kuma.entity.SetmealDish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetmealVO implements Serializable {
    
    private Long id;
    
    // カテゴリID
    private Long categoryId;
    
    // セット名
    private String name;
    
    // セット価格
    private BigDecimal price;
    
    // ステータス 0:無効 1:有効
    private Integer status;
    
    // 説明情報
    private String description;
    
    // 画像
    private String image;
    
    // 更新時間
    private LocalDateTime updateTime;
    
    // カテゴリ名
    private String categoryName;
    
    // セットと料理の関連関係
    private List<SetmealDish> setmealDishes = new ArrayList<>();
}
