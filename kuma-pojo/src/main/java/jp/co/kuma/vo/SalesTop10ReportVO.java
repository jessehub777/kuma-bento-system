package jp.co.kuma.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesTop10ReportVO implements Serializable {
    
    // 商品名リスト（カンマ区切り、例：魚香肉絲,宮保鶏丁,水煮魚）
    private String nameList;
    
    // 売上数量リスト（カンマ区切り、例：260,215,200）
    private String numberList;
    
}
