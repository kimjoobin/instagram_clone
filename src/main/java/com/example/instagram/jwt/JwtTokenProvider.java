package com.example.instagram.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Log4j2
public class JwtTokenProvider {

    private final Key key;
    private final long accessTokenExpiredTime;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.expiration}") long accessTokenExpiredTime
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpiredTime = accessTokenExpiredTime;
    }

    /**
     * Jwt 생성
     * @param authentication
     * @return
     */
    public String generateAccessToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date().getTime() + accessTokenExpiredTime)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmail(String token) {
        return parseClaims(token).getSubject();
    }

    /**
     * Jwt로 사용자 정보 조회(이메일)
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        // 토큰 복호화
        Claims claims = parseClaims(token);
        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        String authString = claims.get("auth").toString();
        if (StringUtils.hasText(authString)) {
            Collection<? extends GrantedAuthority> authorities = Arrays.stream(authString.split(","))
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            UserDetails principal = new User(claims.getSubject(), "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, "", authorities);
        } else {
            throw new RuntimeException("권한 정보가 비어있습니다.");
        }
    }


    /**
     * Jwt 유효성 검사
     * @param accessToken
     * @return
     */
    public boolean validateToken(String accessToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJwt(accessToken)
                    .getBody();
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
        }
        return false;
    }

    /**
     * Jwt 클레임 추출
     * @param accessToken
     * @return
     */
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJwt(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
