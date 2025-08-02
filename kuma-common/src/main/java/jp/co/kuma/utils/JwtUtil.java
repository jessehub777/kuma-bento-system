package jp.co.kuma.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * jwtを生成
     * Hs256アルゴリズムを使用し、秘密鍵は固定キーを利用
     *
     * @param secretKey jwt秘密鍵
     * @param ttlMillis jwt有効期限（ミリ秒）
     * @param claims    設定情報
     * @return
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // 署名時に使用するアルゴリズム（header部分）
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        
        // JWT生成時刻
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);
        
        // jwtのbodyを設定
        JwtBuilder builder = Jwts.builder()
                // プライベートクレームがある場合、必ず先に設定する。標準クレームより後に設定すると上書きされる
                .setClaims(claims)
                // 署名アルゴリズムと秘密鍵を設定
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                // 有効期限を設定
                .setExpiration(exp);
        
        return builder.compact();
    }
    
    /**
     * Token解密
     *
     * @param secretKey jwt秘钥 此秘钥一定要保留好在服务端, 不能暴露出去, 否则sign就可以被伪造, 如果对接多个客户端建议改造成多个
     * @param token     加密后的token
     * @return
     */
    public static Claims parseJWT(String secretKey, String token) {
        // 得到DefaultJwtParser
        Claims claims = Jwts.parser()
                // 设置签名的秘钥
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                // 设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }
    
}
