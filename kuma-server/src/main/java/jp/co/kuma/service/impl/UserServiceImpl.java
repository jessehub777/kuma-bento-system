package jp.co.kuma.service.impl;

import jp.co.kuma.constant.MessageConstant;
import jp.co.kuma.dto.UserLoginDTO;
import jp.co.kuma.entity.AddressBook;
import jp.co.kuma.entity.User;
import jp.co.kuma.exception.AccountNotFoundException;
import jp.co.kuma.exception.PasswordErrorException;
import jp.co.kuma.mapper.AddressBookMapper;
import jp.co.kuma.mapper.UserMapper;
import jp.co.kuma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserMapper userMapper;
    private final AddressBookMapper addressBookMapper;
    
    /**
     * ユーザーを登録します
     *
     * @param userLoginDTO ログイン情報
     * @return 登録されたユーザー情報
     */
    public User login(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPasswd();
        
        //1、emailに基づいてdbのデータを検索する。
        User user = userMapper.getByEmail(email);
        
        //2、　存在チェック
        if (user == null) {
            //アカウントが存在しない場合
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        
        //2、　passwordチェック
        //　passwordはMD5で暗号化されているため、入力されたpasswordも同様に暗号化する。
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPasswd())) {
            //　パスワードが間違っている場合
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        
        //3、ログイン成功　エンティティを返す
        return user;
    }
    
    /**
     * ユーザーのアドレス帳を取得します。
     *
     * @param userId ユーザーID
     * @return アドレス帳のリスト
     */
    public List<AddressBook> getAddressBookListByUserId(Long userId) {
        return addressBookMapper.getByUserId(userId);
    }
}
