package jp.co.kuma.result;

import lombok.Data;

import java.io.Serializable;

/**
 * バックエンド共通返却結果
 *
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {
    
    private Integer code; // コード：1成功、0や他の数字は失敗
    private String msg; // エラーメッセージ
    private T data; // データ
    
    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 1;
        return result;
    }
    
    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 1;
        return result;
    }
    
    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }
    
}
