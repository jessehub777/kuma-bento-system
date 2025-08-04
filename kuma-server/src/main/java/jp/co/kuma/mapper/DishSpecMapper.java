package jp.co.kuma.mapper;

import jp.co.kuma.dto.DishDTO;
import jp.co.kuma.entity.DishSpec;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishSpecMapper {
    
    /**
     * 一括插入DishSpec
     *
     * @param dishSpecs
     */
    void insertSpecBatchByDishId(List<DishSpec> dishSpecs);
    
    /**
     * DishSpecを削除
     *
     * @param dishId
     */
    @Delete("delete from dish_spec where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);
    
    /**
     * DishSpecを一括削除
     *
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);
    
    /**
     * DishSpecを取得
     *
     * @param dishId
     * @return
     */
    @Select("select * from dish_spec where dish_id = #{dishId}")
    List<DishSpec> getByDishId(Long dishId);
    
    /**
     * DishSpecを更新
     *
     * @param dishDTO
     */
    void updateByDishId(DishDTO dishDTO);
}
