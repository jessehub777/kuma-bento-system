package jp.co.kuma.exception;

public class DeletionNotAllowedException extends jp.co.kuma.exception.BaseException {
    
    public DeletionNotAllowedException(String msg) {
        super(msg);
    }
    
}
