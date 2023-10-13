package com.achpay.wallet.model.database.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by hy on 2018/3/10.
 */

@Entity(nameInDb = "LhbOpen")
public class LhbOpen {

    @Id(autoincrement = true)
    @Property(nameInDb = "localid")
    private Long localid;

    @Property(nameInDb = "cs_shoplhb")
    @Expose
    @SerializedName("cs_shoplhb")
    private String cs_shoplhb;

    @Property(nameInDb = "dj_shoplhb")
    @Expose
    @SerializedName("dj_shoplhb")
    private String dj_shoplhb;

    @Property(nameInDb = "huawei_shoplhb")
    @Expose
    @SerializedName("huawei_shoplhb")
    private String huawei_shoplhb;

    @Property(nameInDb = "lhb_shoplhb")
    @Expose
    @SerializedName("lhb_shoplhb")
    private String lhb_shoplhb;

    @Property(nameInDb = "oppo_shoplhb")
    @Expose
    @SerializedName("oppo_shoplhb")
    private String oppo_shoplhb;

    @Property(nameInDb = "vivo_shoplhb")
    @Expose
    @SerializedName("vivo_shoplhb")
    private String vivo_shoplhb;

    @Property(nameInDb = "wandoujia_shoplhb")
    @Expose
    @SerializedName("wandoujia_shoplhb")
    private String wandoujia_shoplhb;

    @Property(nameInDb = "xiaomi_shoplhb")
    @Expose
    @SerializedName("xiaomi_shoplhb")
    private String xiaomi_shoplhb;

    @Property(nameInDb = "yingyongbao_shoplhb")
    @Expose
    @SerializedName("yingyongbao_shoplhb")
    private String yingyongbao_shoplhb;

    @Generated(hash = 696532095)
    public LhbOpen(Long localid, String cs_shoplhb, String dj_shoplhb,
            String huawei_shoplhb, String lhb_shoplhb, String oppo_shoplhb,
            String vivo_shoplhb, String wandoujia_shoplhb, String xiaomi_shoplhb,
            String yingyongbao_shoplhb) {
        this.localid = localid;
        this.cs_shoplhb = cs_shoplhb;
        this.dj_shoplhb = dj_shoplhb;
        this.huawei_shoplhb = huawei_shoplhb;
        this.lhb_shoplhb = lhb_shoplhb;
        this.oppo_shoplhb = oppo_shoplhb;
        this.vivo_shoplhb = vivo_shoplhb;
        this.wandoujia_shoplhb = wandoujia_shoplhb;
        this.xiaomi_shoplhb = xiaomi_shoplhb;
        this.yingyongbao_shoplhb = yingyongbao_shoplhb;
    }

    @Generated(hash = 446431438)
    public LhbOpen() {
    }

    public Long getLocalid() {
        return this.localid;
    }

    public void setLocalid(Long localid) {
        this.localid = localid;
    }

    public String getCs_shoplhb() {
        return this.cs_shoplhb;
    }

    public void setCs_shoplhb(String cs_shoplhb) {
        this.cs_shoplhb = cs_shoplhb;
    }

    public String getDj_shoplhb() {
        return this.dj_shoplhb;
    }

    public void setDj_shoplhb(String dj_shoplhb) {
        this.dj_shoplhb = dj_shoplhb;
    }

    public String getHuawei_shoplhb() {
        return this.huawei_shoplhb;
    }

    public void setHuawei_shoplhb(String huawei_shoplhb) {
        this.huawei_shoplhb = huawei_shoplhb;
    }

    public String getLhb_shoplhb() {
        return this.lhb_shoplhb;
    }

    public void setLhb_shoplhb(String lhb_shoplhb) {
        this.lhb_shoplhb = lhb_shoplhb;
    }

    public String getOppo_shoplhb() {
        return this.oppo_shoplhb;
    }

    public void setOppo_shoplhb(String oppo_shoplhb) {
        this.oppo_shoplhb = oppo_shoplhb;
    }

    public String getVivo_shoplhb() {
        return this.vivo_shoplhb;
    }

    public void setVivo_shoplhb(String vivo_shoplhb) {
        this.vivo_shoplhb = vivo_shoplhb;
    }

    public String getWandoujia_shoplhb() {
        return this.wandoujia_shoplhb;
    }

    public void setWandoujia_shoplhb(String wandoujia_shoplhb) {
        this.wandoujia_shoplhb = wandoujia_shoplhb;
    }

    public String getXiaomi_shoplhb() {
        return this.xiaomi_shoplhb;
    }

    public void setXiaomi_shoplhb(String xiaomi_shoplhb) {
        this.xiaomi_shoplhb = xiaomi_shoplhb;
    }

    public String getYingyongbao_shoplhb() {
        return this.yingyongbao_shoplhb;
    }

    public void setYingyongbao_shoplhb(String yingyongbao_shoplhb) {
        this.yingyongbao_shoplhb = yingyongbao_shoplhb;
    }


}
