package jp.co.kuma.exception;

/**
 * セット有効化失敗例外
 */
public class SetmealEnableFailedException extends jp.co.kuma.exception.BaseException {
    
    public SetmealEnableFailedException() {
    }
    
    public SetmealEnableFailedException(String msg) {
        super(msg);
    }
}
