package jp.co.kuma.mapper;

import jp.co.kuma.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressBookMapper {
    /**
     * ユーザーIDに基づいてアドレス帳を取得します。
     *
     * @param userId ユーザーID
     * @return アドレス帳のリスト
     */
    @Select("SELECT * FROM address_book WHERE user_id = #{userId}")
    List<AddressBook> getByUserId(Long userId);
    
    /**
     * id に基づいて アドレス帳 を取得します。
     *
     * @param id
     * @return AddressBook
     */
    @Select("SELECT * FROM address_book WHERE id = #{id}")
    AddressBook getById(Long id);
}
