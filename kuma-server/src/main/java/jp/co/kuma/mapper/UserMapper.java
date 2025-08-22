package jp.co.kuma.mapper;

import jp.co.kuma.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * ユーザーをメールアドレスで取得します。
     *
     * @param email メールアドレス
     * @return ユーザー情報
     */
    @Select("SELECT * FROM user WHERE email = #{email}")
    User getByEmail(String email);
}
