package jp.co.kuma.exception;

/**
 * 業務例外
 */
public class BaseException extends RuntimeException {
    
    public BaseException() {
    }
    
    public BaseException(String msg) {
        super(msg);
    }
    
}
