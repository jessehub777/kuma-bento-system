package jp.co.kuma.service.impl;

import jp.co.kuma.constant.StatusConstant;
import jp.co.kuma.dto.CategoryDTO;
import jp.co.kuma.entity.Category;
import jp.co.kuma.mapper.CategoryMapper;
import jp.co.kuma.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryMapper categoryMapper;
    
    /**
     * カテゴリーを新規作成
     *
     * @param categoryDTO カテゴリー情報
     */
    public void create(CategoryDTO categoryDTO) {
        Category category = new Category();
        //2つの異なるエンティティ間の変換を簡素化するために、オブジェクトのプロパティコピーを使用する。
        BeanUtils.copyProperties(categoryDTO, category);
        category.setStatus(StatusConstant.DISABLE); // デフォルトで無効化
        
        categoryMapper.create(category);
    }
    
    /**
     * カテゴリーリストを取得
     *
     * @param offset   オフセット
     * @param pageSize ページサイズ
     * @param name     名前でフィルタリング
     * @param type     タイプでフィルタリング
     * @return カテゴリーリスト
     */
    public List<Category> listPage(int offset, int pageSize, String name, Integer type) {
        return categoryMapper.listPage(offset, pageSize, name, type);
    }
    
    
    /**
     * カテゴリーリストの件数を取得
     *
     * @param name 名前でフィルタリング
     * @param type タイプでフィルタリング
     * @return 件数
     */
    public int count(String name, Integer type) {
        return categoryMapper.count(name, type);
    }
    
    /**
     * カテゴリーを更新
     *
     * @param category カテゴリー情報
     */
    public void update(Category category) {
        categoryMapper.update(category);
    }
    
    /**
     * カテゴリーを取得
     *
     * @param id カテゴリーID
     * @return カテゴリー情報
     */
    public Category get(Long id) {
        return categoryMapper.get(id);
    }
    
    /**
     * カテゴリーを削除
     *
     * @param id カテゴリーID
     */
    public void delete(Long id) {
        categoryMapper.delete(id);
    }
    
    /**
     * カテゴリーリストを取得
     *
     * @return カテゴリーリスト
     */
    public List<Category> listAll(Integer type) {
        return categoryMapper.listAll(type);
    }
    
    
}
