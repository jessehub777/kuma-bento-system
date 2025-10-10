package jp.co.kuma.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersPaymentDTO implements Serializable {
    // 注文番号
    private String orderNumber;
    
}
