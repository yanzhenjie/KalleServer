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
import com.yanzhenjie.kalle.server.exception.ParamExeception;
import com.yanzhenjie.kalle.server.util.AppUtils;
import com.yanzhenjie.kalle.server.util.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by YanZhenjie on 2018/2/28.
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    private Logger mLogger = Logger.getLogger(getClass().getName());

    @UserLogin
    @RequestMapping(
            value = "/form",
            method = {RequestMethod.POST, RequestMethod.PUT},
            produces = AppUtils.APPLICATION_JSON
    )
    public String formUpload(HttpServletRequest request,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "age", required = false) String age,
                             @RequestParam(value = "file1", required = false) MultipartFile file1,
                             @RequestParam(value = "file2", required = false) MultipartFile file2,
                             @RequestParam(value = "file3", required = false) MultipartFile file3) throws IOException {
        mLogger.info(String.format("Api get, name:%1$s, age: %2$s.", name, age));

        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("name", name);
        stringMap.put("age", age);

        String dirPath = request.getServletContext().getRealPath("/upload");
        if (file1 != null) {
            String extension = AppUtils.getFileExtension(file1.getOriginalFilename());
            String fileName = saveFile(dirPath, extension, file1.getInputStream());
            stringMap.put("file1", AppUtils.createFileLink(request.getRequestURL().toString(), fileName));
        }
        if (file2 != null) {
            String extension = AppUtils.getFileExtension(file2.getOriginalFilename());
            String fileName = saveFile(dirPath, extension, file2.getInputStream());
            stringMap.put("file2", AppUtils.createFileLink(request.getRequestURL().toString(), fileName));
        }
        if (file3 != null) {
            String extension = AppUtils.getFileExtension(file3.getOriginalFilename());
            String fileName = saveFile(dirPath, extension, file3.getInputStream());
            stringMap.put("file3", AppUtils.createFileLink(request.getRequestURL().toString(), fileName));
        }
        return AppUtils.returnSucceedJson(stringMap);
    }

    @UserLogin
    @RequestMapping(
            value = "/body/file",
            method = {RequestMethod.POST, RequestMethod.PUT},
            produces = AppUtils.APPLICATION_JSON
    )
    public String bodyFile(HttpServletRequest request, @QueryParam("filename") String filename) throws IOException {
        mLogger.info(String.format("Api get, filename:%1$s.", filename));

        if (StringUtils.isEmpty(filename)) throw new ParamExeception("Missing filename parameter.");

        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("filename", filename);

        InputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            String dirPath = request.getServletContext().getRealPath("/upload");
            String extension = AppUtils.getFileExtension(filename);
            String fileName = saveFile(dirPath, extension, inputStream);
            stringMap.put("file", AppUtils.createFileLink(request.getRequestURL().toString(), fileName));
        }
        return AppUtils.returnSucceedJson(stringMap);
    }

    @UserLogin
    @RequestMapping(
            value = "/body/json",
            method = {RequestMethod.POST, RequestMethod.PUT},
            produces = AppUtils.APPLICATION_JSON
    )
    public String bodyJson(HttpServletRequest request, @RequestBody String json) throws IOException {
        Map<String, String> stringMap = new HashMap<>();

        InputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            String dirPath = request.getServletContext().getRealPath("/upload");
            String fileName = saveFile(dirPath, ".json", inputStream);
            stringMap.put("file", AppUtils.createFileLink(request.getRequestURL().toString(), fileName));
        }
        return AppUtils.returnSucceedJson(stringMap);
    }

    private String saveFile(String dirPath, String extension, InputStream inputStream) throws IOException {
        long timeStamp = System.currentTimeMillis();
        String fileName = timeStamp + UUID.randomUUID().toString();
        if (!StringUtils.isEmpty(extension)) {
            fileName += ("." + extension);
        }
        File tempFile = new File(dirPath, fileName);
        IOUtils.createNewFile(tempFile);
        OutputStream outputStream = new FileOutputStream(tempFile);
        IOUtils.write(inputStream, outputStream);
        IOUtils.closeQuietly(outputStream);

        return tempFile.getName();
    }

}