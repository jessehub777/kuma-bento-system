package jp.co.kuma.mapper;

import jp.co.kuma.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper {
    
    void insertItem(OrderDetail item);
}
