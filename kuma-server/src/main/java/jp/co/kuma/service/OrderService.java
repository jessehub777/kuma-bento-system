package jp.co.kuma.service;

import jp.co.kuma.dto.OrdersPaymentDTO;
import jp.co.kuma.dto.OrdersSubmitDTO;
import jp.co.kuma.vo.OrderSubmitVO;

public interface OrderService {
    /**
     * 注文を新規作成する
     *
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);
    
    void pay(OrdersPaymentDTO ordersPaymentDTO);
}
