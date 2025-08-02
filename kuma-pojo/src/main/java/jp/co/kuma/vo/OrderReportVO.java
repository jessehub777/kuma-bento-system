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
public class OrderReportVO implements Serializable {
    
    // 日付（カンマ区切り、例：2022-10-01,2022-10-02,2022-10-03）
    private String dateList;
    
    // 日毎の注文数（カンマ区切り、例：260,210,215）
    private String orderCountList;
    
    // 日毎の有効注文数（カンマ区切り、例：20,21,10）
    private String validOrderCountList;
    
    // 注文総数
    private Integer totalOrderCount;
    
    // 有効注文数
    private Integer validOrderCount;
    
    // 注文完了率
    private Double orderCompletionRate;
    
}
