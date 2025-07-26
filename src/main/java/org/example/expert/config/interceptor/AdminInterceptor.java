package org.example.expert.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userRole = (String) request.getAttribute("userRole");
        String requestURI = request.getRequestURI();

        if (userRole == null) {
            log.warn("null 비정상 접근 {}.", requestURI);
            log.info("HttpServletRequest {}, HttpServletResponse {}, Object {}", request, response, handler);
            log.info("현재 시각: {}", new SimpleDateFormat("yyyy-MM-dd│HH:mm:ss.SSS").format(new java.util.Date(System.currentTimeMillis())));
            return false;
        }
        if (!userRole.equals("ADMIN")) {
            log.warn("not admin 비정상 접근 {}.", requestURI);
            log.info("HttpServletRequest {}, HttpServletResponse {}, Object {}", request, response, handler);
            log.info("현재 시각: {}", new SimpleDateFormat("yyyy-MM-dd│HH:mm:ss.SSS").format(new java.util.Date(System.currentTimeMillis())));
            return false;
        }

        log.info("정상 접근 {}.", requestURI);
        log.info("HttpServletRequest {}, HttpServletResponse {}, Object {}", request, response, handler);
        log.info("현재 시각: {}", new SimpleDateFormat("yyyy-MM-dd│HH:mm:ss.SSS").format(new java.util.Date(System.currentTimeMillis())));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);


    }


}
