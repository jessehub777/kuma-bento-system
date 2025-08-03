package jp.co.kuma.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EmployeeDTO implements Serializable {
    
    private Long id;
    
    private String username;
    
    private String name;
    
    private String phone;
    
    private Integer status;
    
    private String password;
    
    private Long updateUser;
    
    private LocalDateTime updateTime;
    
}
