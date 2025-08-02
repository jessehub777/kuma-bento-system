package jp.co.kuma.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersCancelDTO implements Serializable {
    
    private Long id;
    // 注文キャンセル理由
    private String cancelReason;
    
}
