package jp.co.kuma.vo;

import jp.co.kuma.entity.DishFlavor;
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
public class DishVO implements Serializable {
    
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
    // 更新時間
    private LocalDateTime updateTime;
    // カテゴリ名
    private String categoryName;
    // 料理に関連する味
    private List<DishFlavor> flavors = new ArrayList<>();
    
    //private Integer copies;
}
