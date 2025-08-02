package jp.co.kuma.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderStatisticsVO implements Serializable {
    // 受注待ち件数
    private Integer toBeConfirmed;
    
    // 配達待ち件数
    private Integer confirmed;
    
    // 配達中件数
    private Integer deliveryInProgress;
}
