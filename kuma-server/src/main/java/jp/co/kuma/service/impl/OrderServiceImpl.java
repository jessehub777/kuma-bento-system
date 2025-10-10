package jp.co.kuma.service.impl;

import jp.co.kuma.context.BaseContext;
import jp.co.kuma.dto.OrdersPaymentDTO;
import jp.co.kuma.dto.OrdersSubmitDTO;
import jp.co.kuma.entity.*;
import jp.co.kuma.mapper.*;
import jp.co.kuma.service.OrderService;
import jp.co.kuma.vo.OrderSubmitVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper ordersMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final SetmealMapper setmealMapper;
    private final DishMapper dishMapper;
    private final AddressBookMapper addressBookMapper;
    
    @Transactional
    public OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO) {
        Long userId = BaseContext.getCurrentId();
        // 単価、金額をやり直す
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderDetail item : ordersSubmitDTO.getOrderDetails()) {
            if (item.getSetmealId() != null) {
                Setmeal setmeal = setmealMapper.getById(item.getSetmealId());
                item.setPrice(setmeal.getPrice());
            } else if (item.getDishId() != null) {
                Dish dish = dishMapper.getById(item.getDishId());
                item.setPrice(dish.getPrice());
            }
            item.setAmount(item.getPrice().multiply(BigDecimal.valueOf(item.getNumber())));
            totalAmount = totalAmount.add(item.getAmount());
        }
        // 合計金額
        ordersSubmitDTO.setAmount(totalAmount);
        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersSubmitDTO, orders);
        
        // 注文番号を生成する
        String number = System.currentTimeMillis() + "U" + userId;
        orders.setNumber(number);
        
        // 注文タイプ 1店内でお食事 2お持ち帰り 3デリバリー
        // 注文状態 1未払い 2受付待ち 3受付済み 4 配送中 5完了 6キャンセル済み
        // 支払い状態 0未払い  1支払い済み 2返金
        if (ordersSubmitDTO.getOrderType().equals(Orders.DELIVERY)) {     // 3デリバリー
            AddressBook addressBook = addressBookMapper.getById(ordersSubmitDTO.getAddressBookId());
            orders.setConsignee(addressBook.getConsignee());
        }
        //  0未払い
        orders.setPayStatus(Orders.PAYMENT_UN_PAID);
        // 1払い待ち
        orders.setStatus(Orders.ORDER_PENDING_PAYMENT);
        
        orders.setUserId(userId);
        orders.setOrderTime(LocalDateTime.now());
        ordersMapper.insert(orders);
        
        ordersSubmitDTO.getOrderDetails().forEach(item -> {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(item, orderDetail);
            orderDetail.setOrderId(orders.getId());
            orderDetailMapper.insertItem(orderDetail);
        });
        
        OrderSubmitVO orderSubmitVO = new OrderSubmitVO();
        BeanUtils.copyProperties(orders, orderSubmitVO);
        return orderSubmitVO;
    }
    
    public void pay(@NotNull OrdersPaymentDTO ordersPaymentDTO) {
        Orders orders = ordersMapper.getOrderByNumber(ordersPaymentDTO.getOrderNumber());
        // PayType  1:カウンター 2:クレジットカード 3:電子マネー
        // デモ用のみで、支払い成功をシミュレーションします
        if (orders.getPayType().equals(Orders.PAY_TYPE_COUNTER)) {
            // 2受付待ち
            orders.setStatus(Orders.ORDER_TO_BE_CONFIRMED);
            // 0未払い
            orders.setPayStatus(Orders.PAYMENT_UN_PAID);
        } else if (orders.getPayType().equals(Orders.PAY_TYPE_CREDIT)) {
            // 2受付待ち
            orders.setStatus(Orders.ORDER_TO_BE_CONFIRMED);
            // 1支払い済み
            orders.setPayStatus(Orders.PAYMENT_PAID);
        } else if (orders.getPayType().equals(Orders.PAY_TYPE_EMONEY)) {
            // 2受付待ち
            orders.setStatus(Orders.ORDER_TO_BE_CONFIRMED);
            // 1支払い済み
            orders.setPayStatus(Orders.PAYMENT_PAID);
        }
        ordersMapper.updateStatusAndPaystatus(orders);
    }
    
}
