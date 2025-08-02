package jp.co.kuma.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrdersSubmitDTO implements Serializable {
    // 住所録ID
    private Long addressBookId;
    // 支払方法
    private int payMethod;
    // 備考
    private String remark;
    // 配達予定時間
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;
    // 配送状態 1:即時配達 0:指定時間
    private Integer deliveryStatus;
    // 食器数量
    private Integer tablewareNumber;

    // 包装費
    private Integer packAmount;
    // 合計金額
    private BigDecimal amount;
}
