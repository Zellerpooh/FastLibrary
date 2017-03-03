package com.zeller.fastlibrary.searchhistory.storage.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Zellerpooh on 17/3/3.
 */
@Entity(indexes = {
        @Index(value = "text, date DESC", unique = true)
})
public class SearchRecord {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String text;
    private java.util.Date date;

    @Generated(hash = 981367054)
    public SearchRecord(Long id, @NotNull String text, java.util.Date date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    @Generated(hash = 839789598)
    public SearchRecord() {
    }

    public SearchRecord(String text, Date date) {
        this.text = text;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
