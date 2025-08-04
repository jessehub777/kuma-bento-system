package jp.co.kuma.service;

import jp.co.kuma.dto.CategoryDTO;
import jp.co.kuma.entity.Category;

import java.util.List;

public interface CategoryService {
    
    
    /**
     * カテゴリーを新規作成
     *
     * @param categoryDTO カテゴリー情報
     */
    void create(CategoryDTO categoryDTO);
    
    /**
     * カテゴリーリストを取得
     *
     * @param offset   オフセット
     * @param pageSize ページサイズ
     * @param name     名前でフィルタリング
     * @param type     タイプでフィルタリング
     * @return カテゴリーリスト
     */
    List<Category> list(int offset, int pageSize, String name, Integer type);
    
    /**
     * カテゴリーリストの件数を取得
     *
     * @param name 名前でフィルタリング
     * @param type タイプでフィルタリング
     * @return 件数
     */
    int count(String name, Integer type);
    
    /**
     * カテゴリーを更新
     *
     * @param category カテゴリー情報
     */
    void update(Category category);
    
    /**
     * カテゴリーを取得
     *
     * @param id カテゴリーID
     * @return カテゴリー情報
     */
    Category get(Long id);
    
    /**
     * カテゴリーを削除
     *
     * @param id カテゴリーID
     */
    void delete(Long id);
}

