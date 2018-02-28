/*
 * AUTHOR：YanZhenjie
 *
 * DESCRIPTION：create the File, and add the content.
 *
 * Copyright © jiebao.io. All Rights Reserved
 *
 */
package com.yanzhenjie.kalle.server;

import com.yanzhenjie.kalle.server.exception.BaseException;
import com.yanzhenjie.kalle.server.util.AppUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by YanZhenjie on 2017/11/15.
 */
public class AppExceptionResolver implements HandlerExceptionResolver {

    private Logger mLogger = Logger.getLogger(getClass().getName());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        mLogger.warning(ex.getMessage());
        ModelAndView viewModel = new ModelAndView();
        viewModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        viewModel.addAllObjects(resolveException(request, response, ex));
        return viewModel;
    }

    private Map<String, Object> resolveException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        if (ex instanceof BaseException) {
            BaseException e = (BaseException) ex;
            int code = e.getErrorCode();
            String message = e.getErrorMessage();

            response.setStatus(code);
            return AppUtils.returnFailedMap(code, message);
        } else {
            String message = "Server internal error.";

            response.setStatus(500);
            return AppUtils.returnFailedMap(500, message);
        }
    }
}
