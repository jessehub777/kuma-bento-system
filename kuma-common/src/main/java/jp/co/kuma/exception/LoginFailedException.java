package jp.co.kuma.exception;

/**
 * ログイン失敗
 */
public class LoginFailedException extends jp.co.kuma.exception.BaseException {
    public LoginFailedException(String msg) {
        super(msg);
    }
}
