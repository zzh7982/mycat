package com.sbjava.cat.interceptor;

import com.sbjava.cat.service.impl.UserSessionService;
import com.sbjava.cat.utils.CatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: TokenInterceptor
 *
 * @author ralf
 * @version [1.0, 2018/6/15]
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private UserSessionService userSessionService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            throw new CatException("请登陆", -200);
        }
        request.setAttribute("userInfo",userSessionService.getUserByToken(token));
        return true;
    }
}
