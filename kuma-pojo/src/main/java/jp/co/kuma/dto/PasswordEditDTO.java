package jp.co.kuma.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PasswordEditDTO implements Serializable {
    
    // 旧パスワード
    private String oldPassword;
    
    // 新パスワード
    private String newPassword;
    
}
