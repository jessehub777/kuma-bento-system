package jp.co.kuma.exception;

/**
 * パスワードエラー例外
 */
public class PasswordErrorException extends jp.co.kuma.exception.BaseException {
    
    public PasswordErrorException() {
    }
    
    public PasswordErrorException(String msg) {
        super(msg);
    }
    
}
