package jp.co.kuma.mapper;

import jp.co.kuma.dto.OrdersPageQueryDTO;
import jp.co.kuma.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * orderを新規作成する
     *
     * @param orders
     * @return
     */
    void insert(Orders orders);
    
    Orders getOrderByNumber(String orderNumber);
    
    void updateStatusAndPaystatus(Orders orders);
    
    List<Orders> listByCondition(OrdersPageQueryDTO ordersPageQueryDTO);
}
