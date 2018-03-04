/*
 * Copyright © 2018 Yan Zhenjie.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
