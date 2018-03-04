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
package com.yanzhenjie.kalle.server.controller;

import com.yanzhenjie.kalle.server.entity.Account;
import com.yanzhenjie.kalle.server.exception.NotLoginException;
import com.yanzhenjie.kalle.server.util.AppUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

/**
 * Created by Yan Zhenjie on 2017/11/15.
 */
@RestController
public class LoginController {

    private Logger mLogger = Logger.getLogger(getClass().getName());

    @PostMapping(path = "/login", produces = AppUtils.APPLICATION_JSON)
    public String loginGithub(@RequestParam(name = "name", required = false) String name,
                              @RequestParam(name = "password", required = false) String password,
                              HttpSession session) {
        mLogger.info(String.format("Login api, name: %1$s, password: %2$s", name, password));
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            throw new NotLoginException("Missing name or password parameters.");
        }

        Account account = new Account();
        account.setName(name);
        account.setPassword(password);
        session.setAttribute(AppUtils.SESSION_ACCOUNT, account);
        return AppUtils.returnSucceedJson(null);
    }
}