package jp.co.kuma.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersRejectionDTO implements Serializable {
    
    private Long id;
    
    // 注文拒否理由
    private String rejectionReason;
    
}
