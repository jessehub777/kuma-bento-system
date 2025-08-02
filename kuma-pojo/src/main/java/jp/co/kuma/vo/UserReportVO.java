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
public class UserReportVO implements Serializable {
    
    // 日付（カンマ区切り、例：2022-10-01,2022-10-02,2022-10-03）
    private String dateList;
    
    // ユーザー総数（カンマ区切り、例：200,210,220）
    private String totalUserList;
    
    // 新規ユーザー（カンマ区切り、例：20,21,10）
    private String newUserList;
    
}
