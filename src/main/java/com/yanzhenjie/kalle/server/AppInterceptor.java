/*
 * AUTHOR：YanZhenjie
 *
 * DESCRIPTION：create the File, and add the content.
 *
 * Copyright © jiebao.io. All Rights Reserved
 *
 */
package com.yanzhenjie.kalle.server;

import com.yanzhenjie.kalle.server.annotation.UserLogin;
import com.yanzhenjie.kalle.server.exception.NotLoginException;
import com.yanzhenjie.kalle.server.util.AppUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by YanZhenjie on 2017/11/15.
 */
public class AppInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler != null && handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            UserLogin userLogin = handlerMethod.getMethodAnnotation(UserLogin.class);
            if (userLogin != null) {
                HttpSession session = request.getSession();
                if (session == null || session.getAttribute(AppUtils.SESSION_ACCOUNT) == null) {
                    throw new NotLoginException();
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}