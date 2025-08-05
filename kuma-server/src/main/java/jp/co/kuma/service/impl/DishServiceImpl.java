package jp.co.kuma.service.impl;

import jp.co.kuma.constant.MessageConstant;
import jp.co.kuma.constant.StatusConstant;
import jp.co.kuma.dto.DishDTO;
import jp.co.kuma.dto.DishPageQueryDTO;
import jp.co.kuma.entity.Dish;
import jp.co.kuma.entity.DishSpec;
import jp.co.kuma.exception.BaseException;
import jp.co.kuma.exception.DeletionNotAllowedException;
import jp.co.kuma.mapper.DishMapper;
import jp.co.kuma.mapper.DishSpecMapper;
import jp.co.kuma.mapper.SetmealDishMapper;
import jp.co.kuma.service.DishService;
import jp.co.kuma.vo.DishVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    
    private final DishMapper dishMapper;
    private final DishSpecMapper dishSpecMapper;
    private final SetmealDishMapper setmealDishMapper;
    
    /**
     * 新規料理を保存　Specも一緒に保存
     *
     * @param dishDTO
     */
    @Transactional
    public void insertWithSpec(DishDTO dishDTO) {
        
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        
        // 新規料理を保存だけ
        dishMapper.insert(dish);
        
        //　DishMapper.xml　＝＞ useGeneratedKeys="true" keyProperty="id"
        Long dishId = dish.getId();
        
        
        // DishSpecの一括插入dish_spec表
        List<DishSpec> dishSpecs = dishDTO.getSpecs();
        if (dishSpecs != null && !dishSpecs.isEmpty()) {
            for (DishSpec spec : dishSpecs) {
                spec.setDishId(dishId);
            }
            dishSpecMapper.insertSpecBatchByDishId(dishSpecs);
        }
    }
    
    /**
     * 料理の一括削除　Specも一緒に削除
     * <p>
     * 注意： 売れている料理は削除できません
     * 注意： セットと关联されている料理は削除できません
     *
     * @param ids
     */
    @Transactional
    public void deleteBatch(List<Long> ids) {
        // 注意：売れている料理は削除できません
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus() == StatusConstant.ENABLE) {
                // 売れている料理は削除できません
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        // 注意：セットと关联されている料理は削除できません
        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
        if (setmealIds != null && !setmealIds.isEmpty()) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        
        // 料理の一括削除
        dishMapper.deleteByIds(ids);
        // Specの一括削除
        dishSpecMapper.deleteByDishIds(ids);
    }
    
    /**
     * 料理の詳細を取得
     *
     * @param id
     * @return
     */
    public DishDTO getByIdWithSpec(Long id) {
        Dish dish = dishMapper.getById(id);
        if (dish != null) {
            DishDTO dishDTO = new DishDTO();
            BeanUtils.copyProperties(dish, dishDTO);
            // Specの取得
            List<DishSpec> dishSpecs = dishSpecMapper.getByDishId(id);
            dishDTO.setSpecs(dishSpecs);
            return dishDTO;
        }
        return null;
    }
    
    /**
     * 料理の更新　Specも一緒に更新
     *
     * @param dishDTO
     */
    @Transactional
    public void updateWithSpec(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        int count = dishMapper.update(dish);
        if (count == 0) {
            throw new BaseException("料理更新のを失败");
        }
        
        // Specの更新
        List<DishSpec> dishSpecs = dishDTO.getSpecs();
        if (dishSpecs != null && !dishSpecs.isEmpty()) {
            // 元Specを削除
            dishSpecMapper.deleteByDishId(dishDTO.getId());
            
            // 新しいSpecを追加
            // 注意： dishIdをセットする必要があります
            dishSpecs.forEach(spec -> {
                spec.setDishId(dishDTO.getId());
            });
            dishSpecMapper.insertSpecBatchByDishId(dishSpecs);
        }
    }
    
    /**
     * カテゴリIDに基づいて料理のリストを取得　statusは　ENABLE　限定
     *
     * @param categoryId
     * @return
     */
    public List<Dish> listByCategoryId(Long categoryId) {
        Dish dish = Dish.builder()
                .categoryId(categoryId)
                .status(StatusConstant.ENABLE)
                .build();
        return dishMapper.list(dish);
    }
    
    /**
     * 料理のページングリストを取得
     *
     * @param dishPageQueryDTO
     * @return
     */
    public List<DishVO> listPage(DishPageQueryDTO dishPageQueryDTO) {
        return dishMapper.listPage(dishPageQueryDTO);
    }
    
    public int count(DishPageQueryDTO dishPageQueryDTO) {
        return dishMapper.count(dishPageQueryDTO);
    }
    
    /**
     * 料理の状態を更新
     *
     * @param status
     * @param id
     */
    public void status(Integer status, Integer id) {
        dishMapper.status(status, id);
    }
}
