package com.example.instagram.jwt;

import com.example.instagram.enums.UserRole;
import com.example.instagram.service.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String accessToken = authorization.substring(7);

            // 토큰이 유효할 경우
            if (accessToken != null && jwtTokenProvider.validateToken(accessToken)) {

                String email = jwtTokenProvider.getEmail(accessToken);

                UserDetails userDetails = customUserDetailService.loadUserByUsername(email);

                // 인증용 객체 생성
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                            userDetails, UserRole.ROLE_USER, userDetails.getAuthorities()
                );

                // Security context에 접근권한 설정
                SecurityContextHolder.getContext()
                        .setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        // 다음 필터로 넘김
        filterChain.doFilter(request, response);
    }
}
