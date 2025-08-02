package jp.co.kuma.dto;

import jp.co.kuma.entity.OrderDetail;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdersDTO implements Serializable {
    
    private Long id;
    
    // 注文番号
    private String number;
    
    // 注文状態 1:未払い 2:配達待ち 3:配達済み 4:完了 5:キャンセル
    private Integer status;
    
    // 注文ユーザーID
    private Long userId;
    
    // 住所ID
    private Long addressBookId;
    
    // 注文時間
    private LocalDateTime orderTime;
    
    // 精算時間
    private LocalDateTime checkoutTime;
    
    // 支払方法 
    private Integer payMethod;
    
    // 実際受領金額
    private BigDecimal amount;
    
    // 備考
    private String remark;
    
    // ユーザー名
    private String userName;
    
    // 電話番号
    private String phone;
    
    // 住所
    private String address;
    
    // 受取人
    private String consignee;
    
    private List<OrderDetail> orderDetails;
    
}
