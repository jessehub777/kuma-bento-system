package jp.co.kuma.controller.user;

import jp.co.kuma.dto.OrdersPageQueryDTO;
import jp.co.kuma.dto.OrdersPaymentDTO;
import jp.co.kuma.dto.OrdersSubmitDTO;
import jp.co.kuma.result.Result;
import jp.co.kuma.service.OrderService;
import jp.co.kuma.vo.OrderSubmitVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jp.co.kuma.entity.Orders;

@RestController("userOrderController")
@RequestMapping("/user/order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    
    /**
     * 注文を新規作成する
     *
     * @param ordersSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        OrderSubmitVO orderSubmitVO = orderService.submit(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }
    
    /**
     * 注文を支払う
     *
     * @param ordersPaymentDTO
     * @return
     */
    @PostMapping("/pay")
    public Result pay(@RequestBody OrdersPaymentDTO ordersPaymentDTO) {
        orderService.pay(ordersPaymentDTO);
        return Result.success();
    }
    
    /**
     * 注文リストを取得
     */
    @GetMapping("/list")
    public Result<List<Orders>> getOrderList(OrdersPageQueryDTO ordersPageQueryDTO) {
        List<Orders> orders = orderService.getOrderList(ordersPageQueryDTO);
        return Result.success(orders);
    }
}
