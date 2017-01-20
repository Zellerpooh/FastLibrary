package com.zeller.fastlibrary.searchhistory.bean;

import java.io.Serializable;

/**
 * Created by Zellerpooh on 17/1/18.
 */

public class SearchHistoryModel implements Serializable {
    private String time;
    private String content;

    public SearchHistoryModel() {
    }

    public SearchHistoryModel(String time, String content) {

        this.content = content;
        this.time = time;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
