package jp.co.kuma.controller.admin;

import jp.co.kuma.constant.JwtClaimsConstant;
import jp.co.kuma.dto.EmployeeDTO;
import jp.co.kuma.dto.EmployeeLoginDTO;
import jp.co.kuma.entity.Employee;
import jp.co.kuma.properties.JwtProperties;
import jp.co.kuma.result.PageResult;
import jp.co.kuma.result.Result;
import jp.co.kuma.service.EmployeeService;
import jp.co.kuma.utils.JwtUtil;
import jp.co.kuma.vo.EmployeeLoginVO;
import jp.co.kuma.vo.EmployeePageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 社員コントローラー
 */
@RestController
@RequestMapping("/admin/employee")
@RequiredArgsConstructor()
public class EmployeeController {
    
    private final EmployeeService employeeService;
    private final JwtProperties jwtProperties;
    
    /**
     * login
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        Employee employee = employeeService.login(employeeLoginDTO);
        
        //ログインに成功した後、JWTトークンを生成する。
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        
        //ログイン成功後、ユーザー情報をVOに変換する。
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();
        
        return Result.success(employeeLoginVO);
    }
    
    /**
     * logout
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        
        return Result.success();
    }
    
    /**
     * 社員新規作成
     *
     * @param employeeDTO 社員情報
     * @return 成功メッセージ
     */
    @PostMapping
    public Result<String> create(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.create(employeeDTO);
        return Result.success();
    }
    
    /**
     * 社員リストを取得
     *
     * @param page     ページ番号
     * @param pageSize ページサイズ
     * @param name     名前でフィルタリング
     * @return 社員リスト
     */
    @GetMapping("page")
    public Result<PageResult<EmployeePageVO>> list(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String name) {
        int offset = (page - 1) * pageSize;
        
        List<EmployeePageVO> list = employeeService.list(offset, pageSize, name);
        int total = employeeService.count(name);
        
        // ページ結果を作成
        PageResult<EmployeePageVO> pageResult = new PageResult<>(total, list);
        
        return Result.success(pageResult);
    }
    
    @PatchMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        employeeDTO.setId(id);
        employeeService.update(employeeDTO);
        return Result.success();
    }
}

