/*
 * AUTHOR：YanZhenjie
 *
 * DESCRIPTION：create the File, and add the content.
 *
 * Copyright © jiebao.io. All Rights Reserved
 *
 */
package com.yanzhenjie.kalle.server.controller;

import com.yanzhenjie.kalle.server.annotation.UserLogin;
import com.yanzhenjie.kalle.server.util.AppUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Yan Zhenjie on 2017/5/27.
 */
@RestController
@RequestMapping("/method")
public class MethodController {

    private Logger mLogger = Logger.getLogger(getClass().getName());

    @UserLogin
    @GetMapping(value = "/get", produces = AppUtils.APPLICATION_JSON)
    public String apiGet(@RequestParam(name = "name", required = false) String name,
                         @RequestParam(name = "age", required = false) String age) {
        mLogger.info(String.format("Api get, name:%1$s, age: %2$s.", name, age));
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("name", name);
        stringMap.put("age", age);
        return AppUtils.returnSucceedJson(stringMap);
    }

    @UserLogin
    @PostMapping(value = "/post", produces = AppUtils.APPLICATION_JSON)
    public String apiPost(@RequestParam(name = "name", required = false) String name,
                          @RequestParam(name = "age", required = false) String age) {
        mLogger.info(String.format("Api post, name:%1$s, age: %2$s.", name, age));
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("name", name);
        stringMap.put("age", age);
        return AppUtils.returnSucceedJson(stringMap);
    }

    @UserLogin
    @PutMapping(value = "/put", produces = AppUtils.APPLICATION_JSON)
    public String apiPut(@RequestParam(name = "name", required = false) String name,
                         @RequestParam(name = "age", required = false) String age) {
        mLogger.info(String.format("Api put, name:%1$s, age: %2$s.", name, age));
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("name", name);
        stringMap.put("age", age);
        return AppUtils.returnSucceedJson(stringMap);
    }

    @UserLogin
    @DeleteMapping(value = "/delete", produces = AppUtils.APPLICATION_JSON)
    public String apiDelete(@RequestParam(name = "id", required = false) String id) {
        mLogger.info(String.format("Api delete, id:%1$s.", id));
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("id", id);
        return AppUtils.returnSucceedJson(stringMap);
    }

    @UserLogin
    @PatchMapping(value = "/patch", produces = AppUtils.APPLICATION_JSON)
    public String apiPatch(@RequestParam(name = "name", required = false) String name,
                           @RequestParam(name = "age", required = false) String age) {
        mLogger.info(String.format("Api patch, name:%1$s, age: %2$s.", name, age));
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("name", name);
        stringMap.put("age", age);
        return AppUtils.returnSucceedJson(stringMap);
    }
}
