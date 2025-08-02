package jp.co.kuma.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePageQueryDTO implements Serializable {
    
    // 従業員名
    private String name;
    
    // ページ番号
    private int page;
    
    // 1ページあたりの件数
    private int pageSize;
    
}
