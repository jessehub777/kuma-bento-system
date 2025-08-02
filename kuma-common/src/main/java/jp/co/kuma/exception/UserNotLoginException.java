package jp.co.kuma.exception;

/**
 * ユーザーログインしていない例外
 */
public class UserNotLoginException extends jp.co.kuma.exception.BaseException {
    
    public UserNotLoginException() {
    }
    
    public UserNotLoginException(String msg) {
        super(msg);
    }
    
}
