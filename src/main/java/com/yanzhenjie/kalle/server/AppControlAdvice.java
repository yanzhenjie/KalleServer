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
import com.yanzhenjie.kalle.server.exception.ClientException;
import com.yanzhenjie.kalle.server.util.AppUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by YanZhenjie on 2017/11/14.
 */
@ControllerAdvice
@RestController
public class AppControlAdvice {

    private Logger mLogger = Logger.getLogger(getClass().getName());

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String missingParamHandle(HttpServletRequest request, HttpServletResponse response, MissingServletRequestParameterException e) {
        return handleException(request, response, new ClientException("Lack of necessary parameters."));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String methodNotSupportHandle(HttpServletRequest request, HttpServletResponse response, HttpRequestMethodNotSupportedException e) {
        Set<MediaType> mediaTypeSet = new HashSet<>();
        MediaType mediaType = new MediaType("application", "json", Charset.forName("utf-8"));
        mediaTypeSet.add(mediaType);
        request.setAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, mediaTypeSet);
        return handleException(request, response, new ClientException("Unsupported request method."));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String notReadBodyHandle(HttpServletRequest request, HttpServletResponse response, HttpMessageNotReadableException e) {
        return handleException(request, response, new ClientException("Missing request body."));
    }

    @ExceptionHandler(BaseException.class)
    public String baseExceptionHandle(HttpServletRequest request, HttpServletResponse response, BaseException e) {
        return handleException(request, response, e);
    }

    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception e) {
        return handleException(request, response, e);
    }

    @ExceptionHandler(Error.class)
    public String defaultErrorHandle(HttpServletRequest request, HttpServletResponse response, Error e) {
        return handleException(request, response, e);
    }

    @ExceptionHandler(Throwable.class)
    public String handleException(HttpServletRequest request, HttpServletResponse response, Throwable ex) {
        mLogger.warning(ex.getMessage());
        if (ex instanceof BaseException) {
            BaseException e = (BaseException) ex;
            int code = e.getErrorCode();
            String message = e.getErrorMessage();
            response.setStatus(code);
            return AppUtils.returnFailedJson(code, message);
        } else {
            String message = "Server internal error.";

            response.setStatus(500);
            return AppUtils.returnFailedJson(500, message);
        }
    }

}