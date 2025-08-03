package jp.co.kuma.service;

import jp.co.kuma.dto.EmployeeDTO;
import jp.co.kuma.dto.EmployeeLoginDTO;
import jp.co.kuma.dto.PasswordEditDTO;
import jp.co.kuma.entity.Employee;
import jp.co.kuma.vo.EmployeePageVO;

import java.util.List;

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
    
    
    /**
     * 社員のページングリストを取得
     *
     * @param offset   オフセット
     * @param pageSize ページサイズ
     * @param name     社員名
     * @return 社員ページリスト
     */
    List<EmployeePageVO> list(int offset, int pageSize, String name);
    
    int count(String name);
    
    /**
     * 社員の更新
     */
    void update(EmployeeDTO employeeDTO);
    
    /**
     * 社員の詳細を取得
     *
     * @param id 社員ID
     * @return 社員情報
     */
    EmployeePageVO get(Long id);
    
    /**
     * パスワード更新
     *
     * @param passwordEditDTO パスワード編集DTO
     */
    void updatePassword(PasswordEditDTO passwordEditDTO);
}

