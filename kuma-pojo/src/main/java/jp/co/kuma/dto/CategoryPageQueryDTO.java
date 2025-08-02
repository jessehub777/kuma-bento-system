package jp.co.kuma.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryPageQueryDTO implements Serializable {
    
    // ページ番号
    private int page;
    
    // 1ページあたりの件数
    private int pageSize;
    
    // カテゴリ名
    private String name;
    
    // カテゴリタイプ 1:料理カテゴリ 2:セットカテゴリ
    private Integer type;
    
}
