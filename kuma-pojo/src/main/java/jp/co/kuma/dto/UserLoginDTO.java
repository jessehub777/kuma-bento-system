package jp.co.kuma.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Cエンドユーザーログイン
 */
@Data
public class UserLoginDTO implements Serializable {
    
    private String mail; // メールアドレス
    
    private String passwd; // パスワード
    
}
