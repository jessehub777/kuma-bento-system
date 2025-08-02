package jp.co.kuma.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.kuma.constant.JwtClaimsConstant;
import jp.co.kuma.context.BaseContext;
import jp.co.kuma.properties.JwtProperties;
import jp.co.kuma.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JWTトークンを検証するインターセプター
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenUserInterceptor implements HandlerInterceptor {
    
    private final JwtProperties jwtProperties;
    
    /**
     * JWTトークンを検証する
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //現在のリクエストがControllerのメソッドか、それ以外のリソースかを判定する
        if (!(handler instanceof HandlerMethod)) {
            //現在インターセプトしたのが動的メソッドでない場合は、そのまま処理を通す
            return true;
        }
        
        //1、リクエストヘッダーからトークンを取得する
        String token = request.getHeader(jwtProperties.getUserTokenName());
        
        //2、トークンを検証する
        try {
            log.info("User-JWT検証:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            
            // JWT内のトークンを逆解析して取得し、ThreadLocal.set(id) に保存する
            BaseContext.setCurrentId(userId);
            
            log.info("ユーザーid：{}", userId);
            //3、OK 、処理を通す
            return true;
        } catch (Exception ex) {
            log.error("User-JWT検証失敗: {}", ex.getMessage());
            response.setStatus(401);
            return false;
        }
    }
    
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContext.removeCurrentId();
    }
}
