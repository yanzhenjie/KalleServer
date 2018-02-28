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
public class BaseException extends RuntimeException {

    private int mErrorCode;
    private String mErrorMessage;

    public BaseException(int errorCode, String errorMessage) {
        super(errorMessage);

        this.mErrorCode = errorCode;
        this.mErrorMessage = errorMessage;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }
}
