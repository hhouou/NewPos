package com.achpay.wallet.model.database.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by hy on 2017/12/25.
 */

@Entity(nameInDb = "LoanMoney")
public  class LoanMoney {

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

    @Generated(hash = 866805939)
    public LoanMoney(Long localid, long id, String value) {
        this.localid = localid;
        this.id = id;
        this.value = value;
    }

    @Generated(hash = 478888369)
    public LoanMoney() {
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