package jp.co.kuma.mapper;


import jp.co.kuma.annotation.AutoFill;
import jp.co.kuma.dto.DishPageQueryDTO;
import jp.co.kuma.entity.Dish;
import jp.co.kuma.enumeration.OperationType;
import jp.co.kuma.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishMapper {
    
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);
    
    
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);
    
    
    void deleteByIds(List<Long> ids);
    
    
    @AutoFill(value = OperationType.UPDATE)
    int update(Dish dish);
    
    
    List<Dish> list(Dish dish);
    
    
    @Update("update dish set status = #{status} where id = #{id}")
    void status(Integer status, Integer id);
    
    
    List<DishVO> listPage(DishPageQueryDTO dishPageQueryDTO);
    
    int count(DishPageQueryDTO dishPageQueryDTO);
}
