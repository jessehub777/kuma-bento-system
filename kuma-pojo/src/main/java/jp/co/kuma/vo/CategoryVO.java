package jp.co.kuma.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVO {
    
    private Long id;
    
    //種類: 1料理カテゴリ 2セットカテゴリ
    private Integer type;
    
    // カテゴリ名
    private String name;
    
    // 並び順
    private Integer sort;
}
