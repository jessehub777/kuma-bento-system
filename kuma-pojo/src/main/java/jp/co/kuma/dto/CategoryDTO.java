package jp.co.kuma.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {
    
    // 主キー
    private Long id;
    
    // 種類 1:料理カテゴリ 2:セットカテゴリ
    private Integer type;
    
    // カテゴリ名
    private String name;
    
    // 並び順
    private Integer sort;
    
}
