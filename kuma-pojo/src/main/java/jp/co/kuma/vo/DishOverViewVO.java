package jp.co.kuma.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 料理概要
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishOverViewVO implements Serializable {
    // 販売中数量
    private Integer sold;
    
    // 販売停止数量
    private Integer discontinued;
}
