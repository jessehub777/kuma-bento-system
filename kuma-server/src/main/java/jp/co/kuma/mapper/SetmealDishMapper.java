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
     * セットに含まれる料理を一括で追加する
     *
     * @param setmealDishList
     */
    void insertBatch(List<SetmealDish> setmealDishList);
    
    /**
     * セットIDでセット料理を取得する
     *
     * @param setmealId
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);
    
    
    /**
     * セット料理を削除する
     *
     * @param ids 削除するセット料理のIDリスト
     */
    void deleteBatch(List<Long> ids);
}
