package jp.co.kuma.exception;

/**
 * アカウント存在しない例外
 */
public class AccountNotFoundException extends jp.co.kuma.exception.BaseException {
    
    public AccountNotFoundException() {
    }
    
    public AccountNotFoundException(String msg) {
        super(msg);
    }
    
}
