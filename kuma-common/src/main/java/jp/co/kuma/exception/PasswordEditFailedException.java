package jp.co.kuma.exception;

/**
 * パスワード変更失敗例外
 */
public class PasswordEditFailedException extends jp.co.kuma.exception.BaseException {
    
    public PasswordEditFailedException(String msg) {
        super(msg);
    }
    
}
