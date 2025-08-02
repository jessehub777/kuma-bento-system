package jp.co.kuma.vo;

import jp.co.kuma.entity.OrderDetail;
import jp.co.kuma.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO extends Orders implements Serializable {
    
    // 注文料理情報
    private String orderDishes;
    
    // 注文詳細
    private List<OrderDetail> orderDetailList;
    
}
