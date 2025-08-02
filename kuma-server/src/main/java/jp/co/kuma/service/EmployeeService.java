package jp.co.kuma.service;

import jp.co.kuma.dto.EmployeeLoginDTO;
import jp.co.kuma.entity.Employee;

public interface EmployeeService {
    
    /**
     * 社員ログイン処理
     *
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);
    
}
