package com.quanchong.common.util;

import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Slf4j
public class JwtUtils {
	

	/**
	 * token 签名secret
	 */
	public static final String JWT_SECRET = "exFJy1Y07Qxi5HI2vQYoQd7UmYFvHnKG";
	/**
	 * token expire 有效期，单位秒，默认值7200s(2个小时)
	 */
	public static final long JWT_EXPIRE = 30*24*60*60L;
	/**
	 * token appId,简单防止伪造token
	 */
	public static final String JWT_APPID = "mnp_e5f7cae40cb5";
	
	/**
	 * 创建token
	 * @return
	 */
	public static Token createToken(){
		return createToken(null);
	}

	public static Token createToken(String openId){
		String subject = "jwt_appId:"+JWT_APPID;
		subject = subject+ (!StringUtils.isEmpty(openId)?"&openId:"+openId:"");
		long now = System.currentTimeMillis();
		JwtBuilder builder = Jwts.builder()
				.setIssuer("xwl").setIssuedAt(new Date())
				.setSubject(subject)
				.signWith(SignatureAlgorithm.HS256, generalKey())
				.setExpiration(new Date(now+JWT_EXPIRE*1000));
		String tokenStr = builder.compact();
		Token token = new Token();
		token.setToken(tokenStr);
		token.setExpire(now+JWT_EXPIRE*1000);
		return token;
	}

	public static String parseOpenIdFromTokenSubject(String subject) {
		int index = subject.indexOf("openId:");
		if(index > 0){
			// token中存在openId
			return subject.substring(index+7);

		}
		return null;
	}

	/**
	 * 验证token
	 * @param token
	 * @return
	 */
	public static JwtEnum validateToken(String token){
		try{
			Claims claims = parseToken(token);
			log.info("token expiration:"+claims.getExpiration());
			String subject = claims.getSubject();
			log.info("token subject:"+claims.getSubject());
			if(!StringUtils.isEmpty(subject)){
				String jwt_appid = subject.split("&")[0];
				if(JWT_APPID.equals(jwt_appid.split(":")[1])){
					JwtEnum success = JwtEnum.TOKEN;
					success.setMsg(subject);
					return success;
				}
			}
		}catch(ExpiredJwtException e){
			return JwtEnum.EXPIRED_TOKEN;
		}catch(Exception e){
			return JwtEnum.ERROR_TOKEN;
		}
		return JwtEnum.INVALID_TOKEN;
	}
	
	/**
	 * 解析token
	 * @param token
	 * @return
	 */
	public static Claims parseToken(String token){
		return Jwts.parser().setSigningKey(generalKey())
				.parseClaimsJws(token).getBody();
	}
	
	/**
	 * 将JWT_SECRET加密
	 * @return
	 */
	public static SecretKey generalKey(){
		byte[] key = new Base64().decode(JWT_SECRET);
		return new SecretKeySpec(key, "AES");
	}

	@Data
	public static class Token{
		private String token;
		private long expire;
	}

	public static void main(String[] args) {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ4d2wiLCJpYXQiOjE1NzkxNTY2NTgsInN1YiI6Imp3dF9hcHBJZDptbnBfZTVmN2NhZTQwY2I1Jm9wZW5JZDpvVGdSbzVNbVlxVWVzR3YtczduSnNuNDZqTHN3IiwiZXhwIjoxNTgxNzQ4NjU4fQ.DS-kM3BFJcsY8jFN7xPit8oxSLCaSFpuCbGysMrZlXY";
		System.out.println(validateToken(token));
	}
}