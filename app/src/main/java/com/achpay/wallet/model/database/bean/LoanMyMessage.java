package com.achpay.wallet.model.database.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by hy on 2018/4/4.
 */

@Entity(nameInDb = "LoanMyMessage")
public class LoanMyMessage {
    @Id
    @Property(nameInDb = "id")
    @Expose
    @SerializedName("id")
    private long id;

    @Property(nameInDb = "content")
    @Expose
    @SerializedName("content")
    private String content;

    @Property(nameInDb = "date")
    @Expose
    @SerializedName("date")
    private String date;

    @Property(nameInDb = "title")
    @Expose
    @SerializedName("title")
    private String title;

    @Property(nameInDb = "status")
    @Expose
    @SerializedName("status")
    private String status;

    @Generated(hash = 2147121081)
    public LoanMyMessage(long id, String content, String date, String title,
            String status) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.title = title;
        this.status = status;
    }

    @Generated(hash = 261233609)
    public LoanMyMessage() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
