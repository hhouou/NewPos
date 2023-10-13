package com.achpay.wallet.model.database.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by 95 on 2017/5/10.
 */

@Entity(nameInDb = "banner")
public class Banner {

    @Id(autoincrement = true)
    @Property(nameInDb = "localid")
    private Long localid;
    @Expose
    @SerializedName("photoId")
    @Unique
    @Property(nameInDb = "photoId")
    private String photoId;
    @Expose
    @SerializedName("photoName")
    @Property(nameInDb = "photoName")
    private String photoName;
    @Expose
    @SerializedName("photoDesc")
    @Property(nameInDb = "photoDesc")
    private String photoDesc;
    @Expose
    @SerializedName("photoUrl")
    @Property(nameInDb = "photoUrl")
    private String photoUrl;
    @Expose
    @SerializedName("photoHref")
    @Property(nameInDb = "photoHref")
    private String photoHref;
    @Generated(hash = 898198291)
    public Banner(Long localid, String photoId, String photoName, String photoDesc,
            String photoUrl, String photoHref) {
        this.localid = localid;
        this.photoId = photoId;
        this.photoName = photoName;
        this.photoDesc = photoDesc;
        this.photoUrl = photoUrl;
        this.photoHref = photoHref;
    }
    @Generated(hash = 2026719322)
    public Banner() {
    }
    public Long getLocalid() {
        return this.localid;
    }
    public void setLocalid(Long localid) {
        this.localid = localid;
    }
    public String getPhotoId() {
        return this.photoId;
    }
    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }
    public String getPhotoName() {
        return this.photoName;
    }
    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
    public String getPhotoDesc() {
        return this.photoDesc;
    }
    public void setPhotoDesc(String photoDesc) {
        this.photoDesc = photoDesc;
    }
    public String getPhotoUrl() {
        return this.photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public String getPhotoHref() {
        return this.photoHref;
    }
    public void setPhotoHref(String photoHref) {
        this.photoHref = photoHref;
    }

   

}
