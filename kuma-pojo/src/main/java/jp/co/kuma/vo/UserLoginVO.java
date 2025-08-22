package jp.co.kuma.vo;

import jp.co.kuma.entity.AddressBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO implements Serializable {
    
    private Long id;
    private String email;
    private String name;
    private String phone;
    private String token;
    private List<AddressBook> addressBookList = new ArrayList<>();
    
}
