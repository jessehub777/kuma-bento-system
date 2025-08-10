package jp.co.kuma.service;


import jp.co.kuma.dto.DishDTO;
import jp.co.kuma.dto.DishPageQueryDTO;
import jp.co.kuma.entity.Dish;
import jp.co.kuma.vo.DishUserVO;
import jp.co.kuma.vo.DishVO;

import java.util.List;

public interface DishService {
    
    /**
     * 新規料理を保存
     *
     * @param dishDTO
     */
    void insertWithSpec(DishDTO dishDTO);
    
    /**
     * 料理の一括削除
     */
    void deleteBatch(List<Long> ids);
    
    /**
     * 料理の詳細を取得
     *
     * @param id
     * @return
     */
    DishDTO getByIdWithSpec(Long id);
    
    /**
     * 料理の更新
     *
     * @param dishDTO
     */
    void updateWithSpec(DishDTO dishDTO);
    
    /**
     * 料理のstatusを更新
     *
     * @param status
     * @param id
     */
    void status(Integer status, Integer id);
    
    /**
     * カテゴリIDで料理を取得
     *
     * @param categoryId
     * @return
     */
    List<Dish> listByCategoryId(Long categoryId);
    
    /**
     * 料理のページングリストを取得
     *
     * @param dishPageQueryDTO
     * @return
     */
    List<DishVO> listPage(DishPageQueryDTO dishPageQueryDTO);
    
    int count(DishPageQueryDTO dishPageQueryDTO);
    
    /**
     * ユーザー側の料理のリーストを取得
     *
     * @return
     */
    List<DishUserVO> listAll(Long categoryId);
}
