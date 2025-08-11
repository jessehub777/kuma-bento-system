package jp.co.kuma.service;


import jp.co.kuma.dto.SetmealDTO;
import jp.co.kuma.dto.SetmealPageQueryDTO;
import jp.co.kuma.entity.SetmealDish;
import jp.co.kuma.vo.SetmealPageQueryVO;
import jp.co.kuma.vo.SetmealUserVO;

import java.util.List;

public interface SetmealService {
    
    void createWithDish(SetmealDTO setmealDTO);
    
    /**
     * セットをページングクエリします
     *
     * @param dto ページングクエリDTO
     * @return セットのリスト
     */
    List<SetmealPageQueryVO> list(SetmealPageQueryDTO dto);
    
    /**
     * ページングクエリの合計数を取得します
     *
     * @param dto ページングクエリDTO
     * @return 合計数
     */
    long total(SetmealPageQueryDTO dto);
    
    /**
     * セットを削除します
     *
     * @param ids 削除するセットのIDリスト
     */
    void deleteBatch(List<Long> ids);
    
    
    /**
     * セットを更新します
     *
     * @param setmealDTO セット情報
     */
    void updateById(SetmealDTO setmealDTO);
    
    /**
     * セットをIDで取得します
     *
     * @param id セットのID
     * @return セット情報
     */
    SetmealDTO getById(Long id);
    
    /**
     * カテゴリIDでセットを取得します
     *
     * @param categoryId カテゴリID
     * @return セットのリスト
     */
    List<SetmealUserVO> listAll(Long categoryId);
    
    /**
     * セットIDでセットに含まれる料理を取得します
     *
     * @param setmealId セットID
     * @return 料理のリスト
     */
    List<SetmealDish> getDishListBySetmealId(Long setmealId);
}
