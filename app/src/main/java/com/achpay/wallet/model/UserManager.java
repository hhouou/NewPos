package com.achpay.wallet.model;

import com.achpay.wallet.model.database.DBHelper;
import com.achpay.wallet.model.database.bean.User;

/**
 * Created by kongcraft on 2017/5/7.
 */

public class UserManager {
    private long id;
    private String bankCardCertify;
    private String card_zhengmian_src;
    private int creditLong;
    private int creditMoney;
    private String gongjijinCertify;
    private String idCardCertify;
    private String imgCodeUrl;
    private String inviteCode;
    private String jingdongCertify;
    private int leftCreditMoney;
    private String nickName;
    private String password;
    private String phone;
    private String registerTime;
    private String shebaoCertify;
    private String taobaoCertify;
    private String tongXunLu;
    private String xuexinCertify;
    private String yanghangCertify;
    private String yunYingShang;
    private String zhiMaCertify;
    private String idCard;
    private String name;
    private String bankCard;
    private String shopAddress;
    private String lhbOpen;
    private String jiBenCertify;
    private String jinJiCertify;
    private String token;



    public DBHelper getmDBHelper() {
        return mDBHelper;
    }

    public void setmDBHelper(DBHelper mDBHelper) {
        this.mDBHelper = mDBHelper;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    private DBHelper mDBHelper;
    private User mUser;

    public UserManager(DBHelper helper) {
        mDBHelper = helper;
        User user = mDBHelper.getDefaultUser();
        if (user != null) {
            mUser = user;
            id = user.getId();
            bankCardCertify = user.getBankCardCertify();
            card_zhengmian_src= user.getBankCardCertify();
            creditLong= user.getCreditLong();
            creditMoney= user.getCreditMoney();
            gongjijinCertify= user.getGongjijinCertify();
            idCardCertify= user.getIdCardCertify();
            imgCodeUrl= user.getImgCodeUrl();
            inviteCode= user.getInviteCode();
            jingdongCertify= user.getJingdongCertify();
            leftCreditMoney= user.getLeftCreditMoney();
            nickName= user.getNickName();
            password= user.getPassword();
            phone= user.getPhone();
            registerTime= user.getRegisterTime();
            shebaoCertify= user.getShebaoCertify();
            taobaoCertify= user.getTaobaoCertify();
            tongXunLu= user.getTongXunLu();
            xuexinCertify= user.getXuexinCertify();
            yanghangCertify= user.getYanghangCertify();
            yunYingShang= user.getYunYingShang();
            zhiMaCertify= user.getZhiMaCertify();
            idCard= user.getIdCard();
            name= user.getName();
            bankCard=user.getBankCard();
            shopAddress=user.getShopAddress();
            lhbOpen=user.getLhbOpen();
            jiBenCertify=user.getJiBenCertify();
            jinJiCertify=user.getJinJiCertify();
            token=user.getToken();
        }
    }

    public boolean isUserLogin() {
        User user = mDBHelper.getDefaultUser();
        if (null == user) return false;
        else return true;
    }


    public void update() {
        mUser = mDBHelper.getDefaultUser();
        if (mUser != null) {
            id = mUser.getId();
            bankCardCertify = mUser.getBankCardCertify();
            card_zhengmian_src= mUser.getBankCardCertify();
            creditLong= mUser.getCreditLong();
            creditMoney= mUser.getCreditMoney();
            gongjijinCertify= mUser.getGongjijinCertify();
            idCardCertify= mUser.getIdCardCertify();
            imgCodeUrl= mUser.getImgCodeUrl();
            inviteCode= mUser.getInviteCode();
            jingdongCertify= mUser.getJingdongCertify();
            leftCreditMoney= mUser.getLeftCreditMoney();
            nickName= mUser.getNickName();
            password= mUser.getPassword();
            phone= mUser.getPhone();
            registerTime= mUser.getRegisterTime();
            shebaoCertify= mUser.getShebaoCertify();
            taobaoCertify= mUser.getTaobaoCertify();
            tongXunLu= mUser.getTongXunLu();
            xuexinCertify= mUser.getXuexinCertify();
            yanghangCertify= mUser.getYanghangCertify();
            yunYingShang= mUser.getYunYingShang();
            zhiMaCertify= mUser.getZhiMaCertify();
            idCard= mUser.getIdCard();
            name= mUser.getName();
            bankCard=mUser.getBankCard();
            shopAddress=mUser.getShopAddress();
            lhbOpen=mUser.getLhbOpen();
            jiBenCertify=mUser.getJiBenCertify();
            jinJiCertify=mUser.getJinJiCertify();
            token=mUser.getToken();

        }else {
            lhbOpen="false";
            password="";
            phone="";
            bankCard="";
            name="";
            token="";
        }
    }

    public void update(User user) {
        mDBHelper.updateUser(user);
        mUser = user;
    }


    public void doLogout() {
        if (mUser != null) {
            mDBHelper.deleteUser();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBankCardCertify() {
        return bankCardCertify;
    }

    public void setBankCardCertify(String bankCardCertify) {
        this.bankCardCertify = bankCardCertify;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getCard_zhengmian_src() {
        return card_zhengmian_src;
    }

    public void setCard_zhengmian_src(String card_zhengmian_src) {
        this.card_zhengmian_src = card_zhengmian_src;
    }

    public int getCreditLong() {
        return creditLong;
    }

    public void setCreditLong(int creditLong) {
        this.creditLong = creditLong;
    }

    public int getCreditMoney() {
        return creditMoney;
    }

    public void setCreditMoney(int creditMoney) {
        this.creditMoney = creditMoney;
    }

    public String getGongjijinCertify() {
        return gongjijinCertify;
    }

    public void setGongjijinCertify(String gongjijinCertify) {
        this.gongjijinCertify = gongjijinCertify;
    }

    public String getIdCardCertify() {
        return idCardCertify;
    }

    public void setIdCardCertify(String idCardCertify) {
        this.idCardCertify = idCardCertify;
    }

    public String getImgCodeUrl() {
        return imgCodeUrl;
    }

    public void setImgCodeUrl(String imgCodeUrl) {
        this.imgCodeUrl = imgCodeUrl;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getJingdongCertify() {
        return jingdongCertify;
    }

    public void setJingdongCertify(String jingdongCertify) {
        this.jingdongCertify = jingdongCertify;
    }

    public int getLeftCreditMoney() {
        return leftCreditMoney;
    }

    public void setLeftCreditMoney(int leftCreditMoney) {
        this.leftCreditMoney = leftCreditMoney;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getShebaoCertify() {
        return shebaoCertify;
    }

    public void setShebaoCertify(String shebaoCertify) {
        this.shebaoCertify = shebaoCertify;
    }

    public String getTaobaoCertify() {
        return taobaoCertify;
    }

    public void setTaobaoCertify(String taobaoCertify) {
        this.taobaoCertify = taobaoCertify;
    }

    public String getTongXunLu() {
        return tongXunLu;
    }

    public void setTongXunLu(String tongXunLu) {
        this.tongXunLu = tongXunLu;
    }

    public String getXuexinCertify() {
        return xuexinCertify;
    }

    public void setXuexinCertify(String xuexinCertify) {
        this.xuexinCertify = xuexinCertify;
    }

    public String getYanghangCertify() {
        return yanghangCertify;
    }

    public void setYanghangCertify(String yanghangCertify) {
        this.yanghangCertify = yanghangCertify;
    }

    public String getYunYingShang() {
        return yunYingShang;
    }

    public void setYunYingShang(String yunYingShang) {
        this.yunYingShang = yunYingShang;
    }

    public String getZhiMaCertify() {
        return zhiMaCertify;
    }

    public void setZhiMaCertify(String zhiMaCertify) {
        this.zhiMaCertify = zhiMaCertify;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getLhbOpen() {
        return lhbOpen;
    }

    public void setLhbOpen(String lhbOpen) {
        this.lhbOpen = lhbOpen;
    }

    public String getJiBenCertify() {
        return jiBenCertify;
    }

    public void setJiBenCertify(String jiBenCertify) {
        this.jiBenCertify = jiBenCertify;
    }

    public String getJinJiCertify() {
        return jinJiCertify;
    }

    public void setJinJiCertify(String jinJiCertify) {
        this.jinJiCertify = jinJiCertify;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
