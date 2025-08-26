package jp.co.kuma.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    //種類: 1料理カテゴリ 2セットカテゴリ
    private Integer type;
    
    // カテゴリ名
    private String name;
    
    // 並び順
    private Integer sort;
}
