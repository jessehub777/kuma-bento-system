package jp.co.kuma.handler;

import jp.co.kuma.constant.MessageConstant;
import jp.co.kuma.exception.BaseException;
import jp.co.kuma.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * プロジェクト内でスローされた業務例外を処理するグローバル例外ハンドラ
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    /**
     * 業務例外をキャッチする
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex) {
        log.error("例外情報：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }
    
    /**
     * ユーザーIDの重複があった場合にSQL例外をキャッチし、処理してフロントエンドに返す。
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        // Duplicate entry '123' for key 'employee.idx_username'
        if (message.contains("Duplicate entry")) {
            String username = message.split(" ")[2];
            String msg = username + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        } else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
        
    }
}
