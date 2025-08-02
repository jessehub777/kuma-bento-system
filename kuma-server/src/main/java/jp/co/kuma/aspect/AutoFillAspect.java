package jp.co.kuma.aspect;

import jp.co.kuma.annotation.AutoFill;
import jp.co.kuma.constant.AutoFillConstant;
import jp.co.kuma.context.BaseContext;
import jp.co.kuma.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * カスタムアスペクト。共通フィールドの自動入力機能を実現
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /**
     * ポイントカット
     */
    @Pointcut("execution(* jp.co.kuma.mapper.*.*(..)) && @annotation(jp.co.kuma.annotation.AutoFill)")
    public void autoFillPointcut() {
    }
    
    /**
     * 前処理通知
     */
    @Before("autoFillPointcut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("自動入力機能を実行");
        // 現在インターセプトされているメソッドのアノテーションの値を取得
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); // メソッドシグネチャオブジェクトを取得
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class); // メソッド上のアノテーションを取得
        OperationType operationType = autoFill.value(); // アノテーションの値を取得
        
        // 現在インターセプトされているメソッドの引数を取得
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        // 最初の引数を取得
        Object entity = args[0];
        
        //準備する値
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();
        
        // 操作タイプに応じて値を設定
        if (OperationType.INSERT.equals(operationType)) {
            // 挿入操作
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                
                // 反射を使ってフィールドに値を設定
                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
                
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            
            
        } else if (OperationType.UPDATE.equals(operationType)) {
            // 更新操作
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                
                // 反射を使ってフィールドに値を設定
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
                
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            
        }
    }
}
