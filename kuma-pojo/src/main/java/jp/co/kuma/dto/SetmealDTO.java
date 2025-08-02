package jp.co.kuma.dto;

import jp.co.kuma.entity.SetmealDish;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class SetmealDTO implements Serializable {
    
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
    
    // セット料理関係
    private List<SetmealDish> setmealDishes = new ArrayList<>();
    
}
