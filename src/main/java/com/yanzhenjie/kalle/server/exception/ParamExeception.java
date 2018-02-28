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
 * Created by YanZhenjie on 2017/12/6.
 */
public class ParamExeception extends BaseException {

    public ParamExeception(String errorMessage) {
        super(400, errorMessage);
    }
}
