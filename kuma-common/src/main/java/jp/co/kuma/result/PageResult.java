package jp.co.kuma.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * ページング検索結果のラップ
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult implements Serializable {
    
    private long total; // 総レコード数
    
    private List records; // 現在ページのデータ集合
    
}
