package jp.co.kuma.controller.admin;

import jp.co.kuma.constant.JwtClaimsConstant;
import jp.co.kuma.dto.EmployeeDTO;
import jp.co.kuma.dto.EmployeeLoginDTO;
import jp.co.kuma.entity.Employee;
import jp.co.kuma.properties.JwtProperties;
import jp.co.kuma.result.Result;
import jp.co.kuma.service.EmployeeService;
import jp.co.kuma.utils.JwtUtil;
import jp.co.kuma.vo.EmployeeLoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    
    @PostMapping
    public Result<String> create(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.create(employeeDTO);
        return Result.success();
    }
    

}
