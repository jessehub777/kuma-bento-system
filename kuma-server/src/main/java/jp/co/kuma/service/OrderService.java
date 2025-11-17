package jp.co.kuma.service;

import jp.co.kuma.dto.OrdersPageQueryDTO;
import jp.co.kuma.dto.OrdersPaymentDTO;
import jp.co.kuma.dto.OrdersSubmitDTO;
import jp.co.kuma.vo.OrderSubmitVO;

import java.util.List;
import jp.co.kuma.entity.Orders;

public interface OrderService {
    /**
     * 注文を新規作成する
     *
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);
    
    void pay(OrdersPaymentDTO ordersPaymentDTO);
    
    List<Orders> getOrderList(OrdersPageQueryDTO ordersPageQueryDTO);
}
