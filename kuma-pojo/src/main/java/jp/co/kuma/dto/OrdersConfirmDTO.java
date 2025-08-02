package jp.co.kuma.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersConfirmDTO implements Serializable {
    
    private Long id;
    // 注文状態 1:未払い 2:注文待ち 3:注文済み 4:配達中 5:完了 6:キャンセル 7:返金
    private Integer status;
    
}
