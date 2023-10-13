package com.achpay.wallet.model.database.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by 黑明阳 on 2017/12/25.
 */

@Entity(nameInDb = "UserAddress")
public  class UserAddress {
    /**
     * address : 北京 北京 朝阳区圣诞快乐
     * id : 1
     * isdefault : 1
     * phone : 18311468960
     * user_id : 4
     * username : 黑明阳
     */


    @Id(autoincrement = true)
    @Property(nameInDb = "localid")
    private Long localid;
    @Expose
    @SerializedName("id")
    @Unique
    @Property(nameInDb = "id")
    private long id;

    @Property(nameInDb = "address")
    @Expose
    @SerializedName("address")
    private String address;

    @Property(nameInDb = "isdefault")
    @Expose
    @SerializedName("isdefault")
    private int isdefault;

    @Property(nameInDb = "phone")
    @Expose
    @SerializedName("phone")
    private String phone;

    @Property(nameInDb = "user_id")
    @Expose
    @SerializedName("user_id")
    private int user_id;

    @Property(nameInDb = "username")
    @Expose
    @SerializedName("username")
    private String username;

    @Generated(hash = 1814691120)
    public UserAddress(Long localid, long id, String address, int isdefault,
            String phone, int user_id, String username) {
        this.localid = localid;
        this.id = id;
        this.address = address;
        this.isdefault = isdefault;
        this.phone = phone;
        this.user_id = user_id;
        this.username = username;
    }

    @Generated(hash = 1066331545)
    public UserAddress() {
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsdefault() {
        return this.isdefault;
    }

    public void setIsdefault(int isdefault) {
        this.isdefault = isdefault;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    



}