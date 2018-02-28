/*
 * AUTHOR：YanZhenjie
 *
 * DESCRIPTION：create the File, and add the content.
 *
 * Copyright © jiebao.io. All Rights Reserved
 *
 */
package com.yanzhenjie.kalle.server.exception;

/**
 * Created by YanZhenjie on 2017/11/14.
 */
public class NotFoundException extends BaseException {

    public NotFoundException(String errorMessage) {
        super(404, errorMessage);
    }

    public NotFoundException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
