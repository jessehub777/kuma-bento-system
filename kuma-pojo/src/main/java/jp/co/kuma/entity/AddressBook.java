package jp.co.kuma.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * アドレス帳
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressBook implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    // ユーザーID
    private Long userId;
    
    // 受取人
    private String consignee;
    
    // 電話番号
    private String phone;

    // 省レベル名称
    private String provinceName;
    
    // 市レベル名称
    private String cityName;
    
    // 区レベル名称
    private String districtName;
    
    // 詳細住所
    private String detail;
    
    // タグ
    private String label;
    
    // デフォルトかどうか 0:否 1:是
    private Integer isDefault;
}
