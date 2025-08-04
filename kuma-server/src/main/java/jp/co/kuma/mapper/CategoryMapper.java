package jp.co.kuma.mapper;

import jp.co.kuma.annotation.AutoFill;
import jp.co.kuma.entity.Category;
import jp.co.kuma.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    
    @Insert("INSERT INTO category (name, type, sort, status,create_time,create_user,update_time,update_user) VALUES (#{name}, #{type}, #{sort}, #{status},#{createTime},#{createUser},#{updateTime},#{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void create(Category category);
    
    /**
     * カテゴリーリストを取得
     *
     * @param offset   オフセット
     * @param pageSize ページサイズ
     * @param name     名前でフィルタリング
     * @param type     タイプでフィルタリング
     * @return カテゴリーリスト
     */
    List<Category> list(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("name") String name, @Param("type") Integer type);
    
    int count(@Param("name") String name, @Param("type") Integer type);
    
    
    /**
     * カテゴリーを更新
     *
     * @param category カテゴリー情報
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);
    
    /**
     * カテゴリーを取得
     *
     * @param id カテゴリーID
     * @return カテゴリー情報
     */
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category get(Long id);
    
    /**
     * カテゴリーを削除
     *
     * @param id カテゴリーID
     */
    @Delete("DELETE FROM category WHERE id = #{id}")
    void delete(Long id);
}

