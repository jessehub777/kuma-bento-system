package jp.co.kuma.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {
    
    /**
     * 订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消
     */
    public static final Integer PENDING_PAYMENT = 1;
    public static final Integer TO_BE_CONFIRMED = 2;
    public static final Integer CONFIRMED = 3;
    public static final Integer DELIVERY_IN_PROGRESS = 4;
    public static final Integer COMPLETED = 5;
    public static final Integer CANCELLED = 6;
    
    /**
     * 支付状态 0未支付 1已支付 2退款
     */
    public static final Integer UN_PAID = 0;
    public static final Integer PAID = 1;
    public static final Integer REFUND = 2;
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    // 注文番号
    private String number;
    
    // 注文状態 1:未払い 2:注文待ち 3:注文済み 4:配達中 5:完了 6:キャンセル 7:返金
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
    
    // 支払状態 0:未払い 1:支払い済み 2:返金
    private Integer payStatus;
    
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
    
    // 注文キャンセル理由
    private String cancelReason;
    
    // 注文拒否理由
    private String rejectionReason;
    
    // 注文キャンセル時間
    private LocalDateTime cancelTime;
    
    // 配達予定時間
    private LocalDateTime estimatedDeliveryTime;
    
    // 配送状態 1:即時配達 0:指定時間
    private Integer deliveryStatus;
    
    // 配達時間
    private LocalDateTime deliveryTime;
    
    // 包装費
    private int packAmount;
    
    // 食器数量
    private int tablewareNumber;

}
