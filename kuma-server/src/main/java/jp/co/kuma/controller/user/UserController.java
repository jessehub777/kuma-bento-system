package jp.co.kuma.controller.user;

import jp.co.kuma.constant.JwtClaimsConstant;
import jp.co.kuma.dto.UserLoginDTO;
import jp.co.kuma.entity.AddressBook;
import jp.co.kuma.entity.User;
import jp.co.kuma.properties.JwtProperties;
import jp.co.kuma.result.Result;
import jp.co.kuma.service.UserService;
import jp.co.kuma.utils.JwtUtil;
import jp.co.kuma.vo.UserLoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    
    private final JwtProperties jwtProperties;
    private final UserService userService;
    
    /**
     * ユーザーログイン
     *
     * @param userLoginDTO ログイン情報
     * @return ログイン結果
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        User user = userService.login(userLoginDTO);
        
        //ログインに成功した後、JWTトークンを生成する。
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);
        
        // ユーザーのアドレス帳リストを取得
        List<AddressBook> addressBookList = userService.getAddressBookListByUserId(user.getId());
        
        //ログイン成功後、ユーザー情報をVOに変換する。
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .name(user.getName())
                .token(token)
                .phone(user.getPhone())
                .email(user.getEmail())
                .addressBookList(addressBookList)
                .build();
        
        return Result.success(userLoginVO);
    }
}
