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

import com.yanzhenjie.kalle.server.annotation.UserLogin;
import com.yanzhenjie.kalle.server.entity.News;
import com.yanzhenjie.kalle.server.entity.PageData;
import com.yanzhenjie.kalle.server.util.AppUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Yan Zhenjie on 2017/5/27.
 */
@RestController
@RequestMapping("/method")
public class MethodController {

    private static final int ITEM_TOTAL_COUNT = 500;

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
    @GetMapping(value = "/get/list", produces = AppUtils.APPLICATION_JSON)
    public String apiGetList(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "50") int pageSize) {
        mLogger.info("Api get List.");

        pageSize = Math.max(1, pageSize);
        pageNum = Math.max(1, pageNum);
        int itemTotalCount = ITEM_TOTAL_COUNT;
        int pageCount = itemTotalCount / pageSize + (itemTotalCount % pageSize > 0 ? 1 : 0);

        System.out.println("pageNum:" + pageNum);
        System.out.println("pageCount:" + pageCount);

        List<News> newsList = new ArrayList<>();
        if (pageNum <= pageCount) {
            int start = Math.max(0, (pageNum - 1) * pageSize);
            int loadCount = pageNum <= pageCount ? pageSize : itemTotalCount % pageSize;
            System.out.println("start:" + start);
            System.out.println("loadCount:" + loadCount);
            for (int i = start; i < start + loadCount; i++) {
                News news = new News();
                news.setTitle(String.format("I am title %1$d.", i));
                news.setContent(String.format("I am content %1$d.", i));
                newsList.add(news);
            }
        }
        PageData<News> pageData = AppUtils.transformPageData(pageNum, pageCount, itemTotalCount, newsList);
        return AppUtils.returnSucceedJson(pageData);
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
