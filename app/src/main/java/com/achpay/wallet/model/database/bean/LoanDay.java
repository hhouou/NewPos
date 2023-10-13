package com.achpay.wallet.model.database.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by 黑明阳 on 2017/12/25.
 */

@Entity(nameInDb = "LoanDay")
public  class LoanDay {

    @Id(autoincrement = true)
    @Property(nameInDb = "localid")
    private Long localid;
    @Expose
    @SerializedName("id")
    @Unique
    @Property(nameInDb = "id")
    private long id;

    @Property(nameInDb = "value")
    @Expose
    @SerializedName("value")
    private String value;

    @Generated(hash = 1457362236)
    public LoanDay(Long localid, long id, String value) {
        this.localid = localid;
        this.id = id;
        this.value = value;
    }

    @Generated(hash = 1400888420)
    public LoanDay() {
    }

    public Long getLocalid() {
        return this.localid;
    }

    public void setLocalid(Long localid) {
        this.localid = localid;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}