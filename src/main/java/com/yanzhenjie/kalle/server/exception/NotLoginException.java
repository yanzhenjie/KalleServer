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
 * Created by YanZhenjie on 2017/11/19.
 */
public class NotLoginException extends ClientException {

    public NotLoginException() {
        super(401, "You are not logged in.");
    }

    public NotLoginException(String message) {
        super(401, message);
    }
}
