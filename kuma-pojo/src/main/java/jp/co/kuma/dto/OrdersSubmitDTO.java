package jp.co.kuma.dto;

import jp.co.kuma.entity.OrderDetail;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrdersSubmitDTO implements Serializable {
    // 住所ID
    private Long addressBookId;
    
    // 食事方法
    private Integer orderType;
    
    // 支払方法
    private Integer payType;
    
    // テーブル番号
    private Integer tableNumber;
    
    // 備考
    private String remark;
    
    // 食器数量
    private Integer tablewareNumber;
    
    // 包装費
    private BigDecimal packAmount;
    
    // 合計金額
    private BigDecimal amount;
    
    // 注文明細
    private List<OrderDetail> orderDetails;
}
