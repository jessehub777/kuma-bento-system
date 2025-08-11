package jp.co.kuma.service.impl;

import jp.co.kuma.constant.MessageConstant;
import jp.co.kuma.dto.SetmealDTO;
import jp.co.kuma.dto.SetmealPageQueryDTO;
import jp.co.kuma.entity.Setmeal;
import jp.co.kuma.entity.SetmealDish;
import jp.co.kuma.exception.DeletionNotAllowedException;
import jp.co.kuma.mapper.SetmealDishMapper;
import jp.co.kuma.mapper.SetmealMapper;
import jp.co.kuma.service.SetmealService;
import jp.co.kuma.vo.SetmealPageQueryVO;
import jp.co.kuma.vo.SetmealUserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SetmealServiceImpl implements SetmealService {
    
    private final SetmealMapper setmealMapper;
    private final SetmealDishMapper setmealDishMapper;
    
    /**
     * 新規セットを追加します
     *
     * @param setmealDTO セット情報
     */
    @Transactional
    public void createWithDish(SetmealDTO setmealDTO) {
        // セットを追加
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        // セットの状態を有効に設定
        setmeal.setStatus(0); // 1: 有効, 0: 無効
        setmealMapper.insert(setmeal);
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes.forEach(setmealDish -> {
            // セット料理のセットIDを設定
            setmealDish.setSetmealId(setmeal.getId());
        });
        // セットの料理を追加
        setmealDishMapper.insertBatch(setmealDishes);
    }
    
    /**
     * セットをページングクエリします
     *
     * @param dto ページングクエリDTO
     * @return セットのリスト
     */
    public List<SetmealPageQueryVO> list(SetmealPageQueryDTO dto) {
        return setmealMapper.listPageQuery(dto);
    }
    
    /**
     * ページングクエリの合計数を取得します
     *
     * @param dto ページングクエリDTO
     * @return 合計数
     */
    public long total(SetmealPageQueryDTO dto) {
        return 0;
    }
    
    /**
     * セットを削除します
     *
     * @param ids 削除するセットのIDリスト
     */
    public void deleteBatch(List<Long> ids) {
        // 販売中のセットを削除できません
        List<Integer> statusList = setmealMapper.getStatusByIds(ids);
        statusList.forEach(status -> {
            if (status == 1) {
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });
        // セット料理を削除
        setmealDishMapper.deleteBatch(ids);
        // セットを削除
        setmealMapper.deleteBatch(ids);
        
    }
    
    /**
     * セットを更新します
     *
     * @param setmealDTO セット情報
     */
    @Transactional
    public void updateById(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.updateById(setmeal);
        // セット料理を更新
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if (setmealDishes != null && !setmealDishes.isEmpty()) {
            List<Long> id = new ArrayList<>();
            id.add(setmeal.getId());
            // 既存のセット料理を削除
            setmealDishMapper.deleteBatch(id);
            setmealDishes.forEach(setmealDish -> {
                // セット料理のセットIDを設定
                setmealDish.setSetmealId(setmeal.getId());
            });
            setmealDishMapper.insertBatch(setmealDishes);
        }
    }
    
    /**
     * セットをIDで取得します
     *
     * @param id セットのID
     * @return セット情報
     */
    public SetmealDTO getById(Long id) {
        Setmeal setmeal = setmealMapper.getById(id);
        SetmealDTO setmealDTO = new SetmealDTO();
        BeanUtils.copyProperties(setmeal, setmealDTO);
        List<SetmealDish> setmealDishes = setmealDishMapper.getDishListBySetmealId(id);
        setmealDTO.setSetmealDishes(setmealDishes);
        return setmealDTO;
    }
    
    /**
     * カテゴリIDでセットを取得します
     *
     * @param categoryId カテゴリID
     * @return セットのリスト
     */
    public List<SetmealUserVO> listAll(Long categoryId) {
        return setmealMapper.listAll(categoryId);
    }
    
    /**
     * セットIDでセットに含まれる料理を取得します
     *
     * @param setmealId セットID
     * @return 料理のリスト
     */
    public List<SetmealDish> getDishListBySetmealId(Long setmealId) {
        return setmealDishMapper.getDishListBySetmealId(setmealId);
    }
}
