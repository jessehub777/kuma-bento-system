package jp.co.kuma.annotation;

import jp.co.kuma.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * カスタムアノテーション。特定のメソッドが機能フィールドの自動入力処理を必要とすることを示す
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    // データベース操作タイプ UPDATE INSERT
    OperationType value();
}
