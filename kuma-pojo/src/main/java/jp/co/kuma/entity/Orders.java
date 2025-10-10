package jp.co.kuma.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 注文
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {
    
    /**
     * 注文タイプ 1店内でお食事 2お持ち帰り 3デリバリー
     */
    public static final Integer IN_SHOP = 1;
    public static final Integer TAKE_OUT = 2;
    public static final Integer DELIVERY = 3;
    
    /**
     * 注文状態 1払い待ち 2受付待ち 3受付済み 4 配送中 5完了 6キャンセル済み
     */
    public static final Integer ORDER_PENDING_PAYMENT = 1;
    public static final Integer ORDER_TO_BE_CONFIRMED = 2;
    public static final Integer ORDER_CONFIRMED = 3;
    public static final Integer ORDER_DELIVERY_IN_PROGRESS = 4;
    public static final Integer ORDER_COMPLETED = 5;
    public static final Integer ORDER_CANCELLED = 6;
    
    /**
     * 支払いタイプ  1:カウンター 2:クレジットカード 3:電子マネー
     */
    public static final Integer PAY_TYPE_COUNTER = 1;
    public static final Integer PAY_TYPE_CREDIT = 2;
    public static final Integer PAY_TYPE_EMONEY = 3;
    
    /**
     * 支払い状態 0未払い  1支払い済み 2返金
     */
    public static final Integer PAYMENT_UN_PAID = 0;
    public static final Integer PAYMENT_PAID = 1;
    public static final Integer PAYMENT_REFUND = 2;
    
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
    
    // 注文方法
    private Integer orderType;
    
    // 支払い方法
    private Integer payType;
    
    // 支払状態 0:未払い 1:支払い済み 2:返金
    private Integer payStatus;
    
    // 実際受領金額
    private BigDecimal amount;
    
    // テーブル番号
    private Integer tableNumber;
    
    // 食器数量
    private Integer tablewareNumber;
    
    // 備考
    private String remark;
    
    // 包装費
    private BigDecimal packAmount;
    
    // 受取人
    private String consignee;
    
    // 注文拒否理由
    private String rejectionReason;
    
    // 注文キャンセル時間
    private LocalDateTime cancelTime;
    
}
