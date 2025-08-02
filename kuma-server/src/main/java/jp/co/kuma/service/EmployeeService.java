package jp.co.kuma.service;

import jp.co.kuma.dto.EmployeeDTO;
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
    
    /**
     * 社員新規作成
     *
     * @param employeeDTO
     * @return
     */
    void create(EmployeeDTO employeeDTO);
    
}
