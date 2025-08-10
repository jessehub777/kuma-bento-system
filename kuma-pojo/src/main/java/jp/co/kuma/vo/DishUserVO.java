package jp.co.kuma.vo;

import jp.co.kuma.entity.DishSpec;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishUserVO implements Serializable {
    
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
    
    // 料理に関連する味
    private List<DishSpec> specs = new ArrayList<>();
}
