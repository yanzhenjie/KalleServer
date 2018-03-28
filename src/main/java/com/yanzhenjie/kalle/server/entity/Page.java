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
package com.yanzhenjie.kalle.server.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by YanZhenjie on 2017/11/19.
 */
public class Page implements Serializable {

    /**
     * Current page.
     */
    @SerializedName("pageNum")
    private long mPageNum;

    /**
     * Item count.
     */
    @SerializedName("pageSize")
    private long mPageSize;

    /**
     * Item total count.
     */
    @SerializedName("totalSize")
    private long mTotalSize;

    /**
     * Page count.
     */
    @SerializedName("pageCount")
    private long mPageCount;

    public Page() {
    }

    public long getPageNum() {
        return mPageNum;
    }

    public void setPageNum(long pageNum) {
        mPageNum = pageNum;
    }

    public long getPageSize() {
        return mPageSize;
    }

    public void setPageSize(long pageSize) {
        mPageSize = pageSize;
    }

    public long getTotalSize() {
        return mTotalSize;
    }

    public void setTotalSize(long totalSize) {
        mTotalSize = totalSize;
    }

    public long getPageCount() {
        return mPageCount;
    }

    public void setPageCount(long pageCount) {
        mPageCount = pageCount;
    }
}
