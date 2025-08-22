package jp.co.kuma.service;

import jp.co.kuma.dto.UserLoginDTO;
import jp.co.kuma.entity.AddressBook;
import jp.co.kuma.entity.User;

import java.util.List;

public interface UserService {
    
    /**
     * ユーザーを登録します
     *
     * @param userLoginDTO
     */
    User login(UserLoginDTO userLoginDTO);
    
    /**
     * ユーザーのアドレス帳を取得します。
     *
     * @param userId ユーザーID
     * @return アドレス帳のリスト
     */
    List<AddressBook> getAddressBookListByUserId(Long userId);
}
