package jp.co.kuma.mapper;

import jp.co.kuma.annotation.AutoFill;
import jp.co.kuma.dto.SetmealPageQueryDTO;
import jp.co.kuma.entity.Setmeal;
import jp.co.kuma.enumeration.OperationType;
import jp.co.kuma.vo.SetmealPageQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {
    
    /**
     * 新規セットを追加1
     *
     * @param setmeal セット情報
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Setmeal setmeal);
    
    /**
     * セット料理をページングクエリする
     *
     * @param dto ページングクエリDTO
     * @return セット料理のリスト
     */
    List<SetmealPageQueryVO> listPageQuery(SetmealPageQueryDTO dto);
    
    /**
     * セット自身を削除する
     *
     * @param ids 削除するセット料理のIDリスト
     */
    void deleteBatch(List<Long> ids);
    
    /**
     * セット販売状態をクエリする
     *
     * @param ids 削除するセット料理のIDリスト
     */
    List<Integer> getStatusByIds(List<Long> ids);
    
    /**
     * セットを更新します
     *
     * @param setmeal 削除するセット料理のIDリスト
     */
    @AutoFill(value = OperationType.UPDATE)
    void updateById(Setmeal setmeal);
    
    /**
     * セットをIDで取得します
     *
     * @param id セットのID
     * @return セット情報
     */
    @Select("select id,category_id,name,price,status,description,image from setmeal where id = #{id}")
    Setmeal getById(Long id);
}
