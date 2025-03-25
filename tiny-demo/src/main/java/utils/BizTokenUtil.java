package utils;



import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * 业务会话token工具库，可从token中解出SessionInfo
 *
 * @author linjing
 * @since 2018/3/1
 */
public class BizTokenUtil {
    private final static byte[] SECURITY_KEY = {0x36, 0x31, 0x33, 0x30, 0x32, 0x32, 0x32, 0x32, 0x32};

    public static SessionInfo unwrapperNoExpires(String token) {
        return unwrapper(token, Integer.MAX_VALUE);
    }

    public static SessionInfo unwrapper(String token) {
        return unwrapper(token, 0);
    }

    public static SessionInfo unwrapper(String token, int allowedSkewSeconds) {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(TextCodec.BASE64.encode(SECURITY_KEY))
                .setAllowedClockSkewSeconds(allowedSkewSeconds)
                .parseClaimsJws(token);

        Claims claims = jws.getBody();

        Map map = claims.get("LoginUser", Map.class);
        JSONObject jsonObject = new JSONObject(map);
        SessionInfo sessionInfo = jsonObject.toJavaObject(SessionInfo.class);

        if (sessionInfo != null) {
            return sessionInfo;
        } else {
            throw new RuntimeException("token格式有误，找不到登录载体:[" + Optional.ofNullable(token).orElse("") + "]");
        }
    }

    public static Date getExpires(String token) {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(TextCodec.BASE64.encode(SECURITY_KEY))
                .parseClaimsJws(token);
        return jws.getBody().getExpiration();
    }

    /**
     * SessionInfo转为token，有效期默认
     *
     * @param currLoginUser
     * @return
     */
    public static String wrapper(SessionInfo currLoginUser) {
        Claims claims = new DefaultClaims();
        claims.setExpiration(new Date(5555555555555L));// 过期时间默认很久：2146-01-18 17:52:35
        claims.put("LoginUser", currLoginUser);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(SECURITY_KEY))
                .compact();
    }

    /**
     * SessionInfo转为token，并设置有效期
     *
     * @param expires       过期时间
     * @param currLoginUser
     * @return
     */
    public static String wrapper(Date expires, SessionInfo currLoginUser) {
        Claims claims = new DefaultClaims();
        claims.setExpiration(expires);
        claims.put("LoginUser", currLoginUser);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(SECURITY_KEY))
                .compact();
    }
}
