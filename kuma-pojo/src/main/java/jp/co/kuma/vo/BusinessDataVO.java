package jp.co.kuma.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * データ概要
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDataVO implements Serializable {
    
    private Double turnover;// 売上高
    
    private Integer validOrderCount;// 有効注文数
    
    private Double orderCompletionRate;// 注文完了率
    
    private Double unitPrice;// 平均客単価
    
    private Integer newUsers;// 新規ユーザー数
    
}
