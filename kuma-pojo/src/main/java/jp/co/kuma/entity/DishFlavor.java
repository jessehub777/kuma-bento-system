package jp.co.kuma.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 料理の味
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishFlavor implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    // 料理ID
    private Long dishId;
    
    // 味の名称
    private String name;
    
    // 味データリスト
    private String value;
    
}
