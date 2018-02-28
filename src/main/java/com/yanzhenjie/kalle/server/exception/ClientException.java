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
public class ClientException extends BaseException {

    public ClientException(String errorMessage) {
        super(400, errorMessage);
    }

    public ClientException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
