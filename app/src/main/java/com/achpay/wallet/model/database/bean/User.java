package com.achpay.wallet.model.database.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by hy on 2017/10/19.
 */
@Entity(nameInDb = "User")
public class User {

//    "bankCard":"623061010110028374",
//            "bankCardCertify":"Y",
//            "card_fanmian_src":"fileXjd/5_fanmian_9b79a0b2b84d4cf488bf4a5cb07fce4a.jpg",
//            "card_zhengmian_src":"fileXjd/5_zhengmian_d7a8f5df774c4727b324e73f7d6d8c31.jpg",
//            "card_zipai_src":"fileXjd/5_zipai_40f3067a5d7c45c2a5afebdc1da3b427.jpg",
//            "creditLong":14,
//            "creditMoney":1000,
//            "gongjijinCertify":"N",
//            "id":5,
//            "idCard":"150122199001212118",
//            "idCardCertify":"Y",
//            "imgCodeUrl":"/qhcode/d4bca00a-eef3-443f-a57f-229a2b247e02-5.png",
//            "inviteCode":"68667457",
//            "jingdongCertify":"N",
//            "leftCreditMoney":995,
//            "name":"侯禹",
//            "nickName":"用户68667457",
//            "password":"hy123456",
//            "phone":"13474836141",
//            "registerTime":"2017-10-28 18:16:27.0",
//            "shebaoCertify":"N",
//            "taobaoCertify":"N",
//            "tongXunLu":"Y",
//            "xuexinCertify":"N",
//            "yanghangCertify":"N",
//            "yunYingShang":"Y",
//            "zhiMaCertify":"N"

    @Id
    @Property(nameInDb = "id")
    @Expose
    @SerializedName("id")
    private long id;

    @Property(nameInDb = "bankCardCertify")
    @Expose
    @SerializedName("bankCardCertify")
    private String bankCardCertify;

    @Property(nameInDb = "card_zhengmian_src")
    @Expose
    @SerializedName("card_zhengmian_src")
    private String card_zhengmian_src;

    @Property(nameInDb = "creditLong")
    @Expose
    @SerializedName("creditLong")
    private int creditLong;

    @Property(nameInDb = "creditMoney")
    @Expose
    @SerializedName("creditMoney")
    private int creditMoney;

    @Property(nameInDb = "gongjijinCertify")
    @Expose
    @SerializedName("gongjijinCertify")
    private String gongjijinCertify;

    @Property(nameInDb = "idCardCertify")
    @Expose
    @SerializedName("idCardCertify")
    private String idCardCertify;

    @Property(nameInDb = "imgCodeUrl")
    @Expose
    @SerializedName("imgCodeUrl")
    private String imgCodeUrl;

    @Property(nameInDb = "inviteCode")
    @Expose
    @SerializedName("inviteCode")
    private String inviteCode;

    @Property(nameInDb = "jingdongCertify")
    @Expose
    @SerializedName("jingdongCertify")
    private String jingdongCertify;

    @Property(nameInDb = "leftCreditMoney")
    @Expose
    @SerializedName("leftCreditMoney")
    private int leftCreditMoney;


    @Property(nameInDb = "nickName")
    @Expose
    @SerializedName("nickName")
    private String nickName;

    @Property(nameInDb = "password")
    @Expose
    @SerializedName("password")
    private String password;

    @Property(nameInDb = "phone")
    @Expose
    @SerializedName("phone")
    private String phone;

    @Property(nameInDb = "registerTime")
    @Expose
    @SerializedName("registerTime")
    private String registerTime;

    @Property(nameInDb = "shebaoCertify")
    @Expose
    @SerializedName("shebaoCertify")
    private String shebaoCertify;

    @Property(nameInDb = "taobaoCertify")
    @Expose
    @SerializedName("taobaoCertify")
    private String taobaoCertify;

    @Property(nameInDb = "tongXunLu")
    @Expose
    @SerializedName("tongXunLu")
    private String tongXunLu;

    @Property(nameInDb = "xuexinCertify")
    @Expose
    @SerializedName("xuexinCertify")
    private String xuexinCertify;

    @Property(nameInDb = "yanghangCertify")
    @Expose
    @SerializedName("yanghangCertify")
    private String yanghangCertify;

    @Property(nameInDb = "yunYingShang")
    @Expose
    @SerializedName("yunYingShang")
    private String yunYingShang;

    @Property(nameInDb = "zhiMaCertify")
    @Expose
    @SerializedName("zhiMaCertify")
    private String zhiMaCertify;

    @Property(nameInDb = "idcard")
    @Expose
    @SerializedName("idCard")
    private String idCard;

    @Property(nameInDb = "name")
    @Expose
    @SerializedName("name")
    private String name;

    @Property(nameInDb = "bankCard")
    @Expose
    @SerializedName("bankCard")
    private String bankCard;

    @Property(nameInDb = "card_fanmian_src")
    @Expose
    @SerializedName("card_fanmian_src")
    private String card_fanmian_src;

    @Property(nameInDb = "card_zipai_src")
    @Expose
    @SerializedName("card_zipai_src")
    private String card_zipai_src;

    @Property(nameInDb = "shopAddress")
    @Expose
    @SerializedName("shopAddress")
    private String shopAddress;

    @Property(nameInDb = "lhbOpen")
    @Expose
    @SerializedName("lhbOpen")
    private String lhbOpen;

    @Property(nameInDb = "jiBenCertify")
    @Expose
    @SerializedName("jiBenCertify")
    private String jiBenCertify;

    @Property(nameInDb = "jinJiCertify")
    @Expose
    @SerializedName("jinJiCertify")
    private String jinJiCertify;

    @Property(nameInDb = "token")
    @Expose
    @SerializedName("token")
    private String token;

    @Generated(hash = 564027954)
    public User(long id, String bankCardCertify, String card_zhengmian_src, int creditLong,
            int creditMoney, String gongjijinCertify, String idCardCertify, String imgCodeUrl,
            String inviteCode, String jingdongCertify, int leftCreditMoney, String nickName,
            String password, String phone, String registerTime, String shebaoCertify,
            String taobaoCertify, String tongXunLu, String xuexinCertify,
            String yanghangCertify, String yunYingShang, String zhiMaCertify, String idCard,
            String name, String bankCard, String card_fanmian_src, String card_zipai_src,
            String shopAddress, String lhbOpen, String jiBenCertify, String jinJiCertify,
            String token) {
        this.id = id;
        this.bankCardCertify = bankCardCertify;
        this.card_zhengmian_src = card_zhengmian_src;
        this.creditLong = creditLong;
        this.creditMoney = creditMoney;
        this.gongjijinCertify = gongjijinCertify;
        this.idCardCertify = idCardCertify;
        this.imgCodeUrl = imgCodeUrl;
        this.inviteCode = inviteCode;
        this.jingdongCertify = jingdongCertify;
        this.leftCreditMoney = leftCreditMoney;
        this.nickName = nickName;
        this.password = password;
        this.phone = phone;
        this.registerTime = registerTime;
        this.shebaoCertify = shebaoCertify;
        this.taobaoCertify = taobaoCertify;
        this.tongXunLu = tongXunLu;
        this.xuexinCertify = xuexinCertify;
        this.yanghangCertify = yanghangCertify;
        this.yunYingShang = yunYingShang;
        this.zhiMaCertify = zhiMaCertify;
        this.idCard = idCard;
        this.name = name;
        this.bankCard = bankCard;
        this.card_fanmian_src = card_fanmian_src;
        this.card_zipai_src = card_zipai_src;
        this.shopAddress = shopAddress;
        this.lhbOpen = lhbOpen;
        this.jiBenCertify = jiBenCertify;
        this.jinJiCertify = jinJiCertify;
        this.token = token;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBankCardCertify() {
        return this.bankCardCertify;
    }

    public void setBankCardCertify(String bankCardCertify) {
        this.bankCardCertify = bankCardCertify;
    }

    public String getCard_zhengmian_src() {
        return this.card_zhengmian_src;
    }

    public void setCard_zhengmian_src(String card_zhengmian_src) {
        this.card_zhengmian_src = card_zhengmian_src;
    }

    public int getCreditLong() {
        return this.creditLong;
    }

    public void setCreditLong(int creditLong) {
        this.creditLong = creditLong;
    }

    public int getCreditMoney() {
        return this.creditMoney;
    }

    public void setCreditMoney(int creditMoney) {
        this.creditMoney = creditMoney;
    }

    public String getGongjijinCertify() {
        return this.gongjijinCertify;
    }

    public void setGongjijinCertify(String gongjijinCertify) {
        this.gongjijinCertify = gongjijinCertify;
    }

    public String getIdCardCertify() {
        return this.idCardCertify;
    }

    public void setIdCardCertify(String idCardCertify) {
        this.idCardCertify = idCardCertify;
    }

    public String getImgCodeUrl() {
        return this.imgCodeUrl;
    }

    public void setImgCodeUrl(String imgCodeUrl) {
        this.imgCodeUrl = imgCodeUrl;
    }

    public String getInviteCode() {
        return this.inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getJingdongCertify() {
        return this.jingdongCertify;
    }

    public void setJingdongCertify(String jingdongCertify) {
        this.jingdongCertify = jingdongCertify;
    }

    public int getLeftCreditMoney() {
        return this.leftCreditMoney;
    }

    public void setLeftCreditMoney(int leftCreditMoney) {
        this.leftCreditMoney = leftCreditMoney;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegisterTime() {
        return this.registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getShebaoCertify() {
        return this.shebaoCertify;
    }

    public void setShebaoCertify(String shebaoCertify) {
        this.shebaoCertify = shebaoCertify;
    }

    public String getTaobaoCertify() {
        return this.taobaoCertify;
    }

    public void setTaobaoCertify(String taobaoCertify) {
        this.taobaoCertify = taobaoCertify;
    }

    public String getTongXunLu() {
        return this.tongXunLu;
    }

    public void setTongXunLu(String tongXunLu) {
        this.tongXunLu = tongXunLu;
    }

    public String getXuexinCertify() {
        return this.xuexinCertify;
    }

    public void setXuexinCertify(String xuexinCertify) {
        this.xuexinCertify = xuexinCertify;
    }

    public String getYanghangCertify() {
        return this.yanghangCertify;
    }

    public void setYanghangCertify(String yanghangCertify) {
        this.yanghangCertify = yanghangCertify;
    }

    public String getYunYingShang() {
        return this.yunYingShang;
    }

    public void setYunYingShang(String yunYingShang) {
        this.yunYingShang = yunYingShang;
    }

    public String getZhiMaCertify() {
        return this.zhiMaCertify;
    }

    public void setZhiMaCertify(String zhiMaCertify) {
        this.zhiMaCertify = zhiMaCertify;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankCard() {
        return this.bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getCard_fanmian_src() {
        return this.card_fanmian_src;
    }

    public void setCard_fanmian_src(String card_fanmian_src) {
        this.card_fanmian_src = card_fanmian_src;
    }

    public String getCard_zipai_src() {
        return this.card_zipai_src;
    }

    public void setCard_zipai_src(String card_zipai_src) {
        this.card_zipai_src = card_zipai_src;
    }

    public String getShopAddress() {
        return this.shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getLhbOpen() {
        return this.lhbOpen;
    }

    public void setLhbOpen(String lhbOpen) {
        this.lhbOpen = lhbOpen;
    }

    public String getJiBenCertify() {
        return this.jiBenCertify;
    }

    public void setJiBenCertify(String jiBenCertify) {
        this.jiBenCertify = jiBenCertify;
    }

    public String getJinJiCertify() {
        return this.jinJiCertify;
    }

    public void setJinJiCertify(String jinJiCertify) {
        this.jinJiCertify = jinJiCertify;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }



}
