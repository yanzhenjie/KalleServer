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
public class ServerException extends BaseException {

    public ServerException(String errorMessage) {
        super(500, errorMessage);
    }

    public ServerException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
