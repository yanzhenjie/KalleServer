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
package com.yanzhenjie.kalle.server.util;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by YanZhenjie on 2017/11/18.
 */
public final class AppUtils {

    /**
     * Account info.
     */
    public static final String SESSION_ACCOUNT = "SESSION_ACCOUNT";
    /**
     * JSON.
     */
    public static final String APPLICATION_JSON = "application/json;charset=utf-8";
    /**
     * Stream.
     */
    public static final String APPLICATION_STREAM = "application/octet-stream";

    /**
     * Succeed json.
     */
    public static String returnSucceedJson(Object data) {
        Map<String, Object> returned = new HashMap<>();
        returned.put("succeed", true);
        returned.put("code", 200);
        returned.put("data", data);
        return JSON.toJSONString(returned);
    }

    /**
     * Failed json.
     */
    public static String returnFailedJson(int status, String message) {
        return JSON.toJSONString(returnFailedMap(status, message));
    }

    /**
     * Failed map.
     */
    public static Map<String, Object> returnFailedMap(int status, String message) {
        Map<String, Object> returned = new HashMap<>();
        returned.put("succeed", false);
        returned.put("code", status);
        returned.put("message", message);
        return returned;
    }

    /**
     * Obtain file extension.
     */
    public static String getFileExtension(String url) {
        if (!StringUtils.isEmpty(url)) {
            int fragment = url.lastIndexOf('#');
            if (fragment > 0) {
                url = url.substring(0, fragment);
            }

            int query = url.lastIndexOf('?');
            if (query > 0) {
                url = url.substring(0, query);
            }

            int filenamePos = url.lastIndexOf('/');
            String filename = 0 <= filenamePos ? url.substring(filenamePos + 1) : url;

            if (!filename.isEmpty() &&
                    Pattern.matches("[a-zA-Z_0-9\\.\\-\\(\\)\\%]+", filename)) {
                int dotPos = filename.lastIndexOf('.');
                if (0 <= dotPos) {
                    return filename.substring(dotPos + 1);
                }
            }
        }
        return "";
    }

    /**
     * Create download link.
     */
    public static String createFileLink(String sourceUrl, String fileName) {
        URI uri = URI.create(sourceUrl);
        return uri.getScheme() + "://" + uri.getHost() + port(uri.getPort()) + "/upload/" + fileName;
    }

    private static String port(int port) {
        return port < 0 ? "" : String.format(Locale.getDefault(), ":%d", port);
    }
}