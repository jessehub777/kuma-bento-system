package jp.co.kuma.mapper;

import jp.co.kuma.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    
    /**
     * 根据 多个菜品id 查询 多个套餐id
     *
     * @param dishIds
     * @return
     */
    // select setmeal_id from setmeal_dish where dish_id in (1, 2, 3)
    public List<Long> getSetmealIdsByDishIds(List<Long> dishIds);
    
    /**
     * 批量插入套餐下的菜品
     *
     * @param setmealDishList
     */
    void insertBatch(List<SetmealDish> setmealDishList);
    
    
    /**
     * 根据套餐id删除套餐和菜品的关联关系
     *
     * @param ids
     */
    void deleteBySetmealIds(List<Long> ids);
    
    /**
     * 根据套餐id查询套餐和菜品的关联关系
     *
     * @param setmealId
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);
    
}
