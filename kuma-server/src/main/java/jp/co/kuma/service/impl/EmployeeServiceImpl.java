package jp.co.kuma.service.impl;

import jp.co.kuma.constant.MessageConstant;
import jp.co.kuma.constant.PasswordConstant;
import jp.co.kuma.constant.StatusConstant;
import jp.co.kuma.dto.EmployeeDTO;
import jp.co.kuma.dto.EmployeeLoginDTO;
import jp.co.kuma.entity.Employee;
import jp.co.kuma.exception.AccountLockedException;
import jp.co.kuma.exception.AccountNotFoundException;
import jp.co.kuma.exception.PasswordErrorException;
import jp.co.kuma.mapper.EmployeeMapper;
import jp.co.kuma.service.EmployeeService;
import jp.co.kuma.vo.EmployeePageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    
    private final EmployeeMapper employeeMapper;
    
    /**
     * 社員ログイン処理
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();
        
        //1、usernameに基づいてdbのデータを検索する。
        Employee employee = employeeMapper.getByUsername(username);
        
        //2、　存在チェック
        if (employee == null) {
            //アカウントが存在しない場合
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        
        //2、　passwordチェック
        //　passwordはMD5で暗号化されているため、入力されたpasswordも同様に暗号化する。
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //　パスワードが間違っている場合
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        
        if (employee.getStatus() == StatusConstant.DISABLE) {
            //　アカウントがロックされている場合
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }
        
        //3、ログイン成功　エンティティを返す
        return employee;
    }
    
    /**
     * 社員新規作成
     */
    public void create(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        //2つの異なるエンティティ間の変換を簡素化するために、オブジェクトのプロパティコピーを使用する。
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setStatus(StatusConstant.ENABLE);
        //　パスワードはMD5で暗号化されているため、デフォルトパスワードも同様に暗号化する。
        //　デフォルトパスワードは、PasswordConstantクラスで定義されている。
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        
        employeeMapper.create(employee);
    }
    
    /**
     * 社員リストを取得
     *
     * @param offset   オフセット
     * @param pageSize ページサイズ
     * @param name     名前でフィルタリング
     * @return 社員リスト
     */
    public List<EmployeePageVO> list(int offset, int pageSize, String name) {
        List<EmployeePageVO> employees = employeeMapper.list(offset, pageSize, name);
//        employees.forEach(employee -> {
//            employee.setPassword(null);
//        }); // パスワードを隠す
        return employees;
    }
    
    /**
     * 社員リストを取得
     *
     * @param name 名前でフィルタリング
     * @return count
     */
    public int count(String name) {
        return employeeMapper.countByName(name);
    }
    
    /**
     * 社員の更新
     */
    public void update(EmployeeDTO employeeDTO) {
        // パスワードが更新される場合、MD5で暗号化する
        if (employeeDTO.getPassword() != null && !employeeDTO.getPassword().isEmpty()) {
            employeeDTO.setPassword(DigestUtils.md5DigestAsHex(employeeDTO.getPassword().getBytes()));
        }
        //更新される場合、Entityを使った方がよい
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employeeMapper.update(employee);
    }
    
    /**
     * 社員の詳細を取得
     *
     * @param id 社員ID
     * @return 社員情報
     */
    public EmployeePageVO get(Long id) {
        Employee employee = employeeMapper.get(id);
        EmployeePageVO employeePageVO = new EmployeePageVO();
        BeanUtils.copyProperties(employee, employeePageVO);
        return employeePageVO;
    }
    
}
