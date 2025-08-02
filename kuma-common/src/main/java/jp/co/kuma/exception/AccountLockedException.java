package jp.co.kuma.exception;

/**
 * アカウントロック例外
 */
public class AccountLockedException extends jp.co.kuma.exception.BaseException {
    
    public AccountLockedException() {
    }
    
    public AccountLockedException(String msg) {
        super(msg);
    }
    
}
