package jp.co.kuma.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    //種類: 1料理カテゴリ 2セットカテゴリ
    private Integer type;
    
    // カテゴリ名
    private String name;
    
    // 並び順
    private Integer sort;
    
    // カテゴリ状態 0:無効 1:有効
    private Integer status;
    
    // 作成時間
    private LocalDateTime createTime;
    
    // 更新時間
    private LocalDateTime updateTime;
    
    // 作成者
    private Long createUser;
    
    // 更新者
    private Long updateUser;
}
