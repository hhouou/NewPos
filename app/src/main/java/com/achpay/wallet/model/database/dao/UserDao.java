package com.achpay.wallet.model.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.achpay.wallet.model.database.bean.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "User".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "User";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "id");
        public final static Property BankCardCertify = new Property(1, String.class, "bankCardCertify", false, "bankCardCertify");
        public final static Property Card_zhengmian_src = new Property(2, String.class, "card_zhengmian_src", false, "card_zhengmian_src");
        public final static Property CreditLong = new Property(3, int.class, "creditLong", false, "creditLong");
        public final static Property CreditMoney = new Property(4, int.class, "creditMoney", false, "creditMoney");
        public final static Property GongjijinCertify = new Property(5, String.class, "gongjijinCertify", false, "gongjijinCertify");
        public final static Property IdCardCertify = new Property(6, String.class, "idCardCertify", false, "idCardCertify");
        public final static Property ImgCodeUrl = new Property(7, String.class, "imgCodeUrl", false, "imgCodeUrl");
        public final static Property InviteCode = new Property(8, String.class, "inviteCode", false, "inviteCode");
        public final static Property JingdongCertify = new Property(9, String.class, "jingdongCertify", false, "jingdongCertify");
        public final static Property LeftCreditMoney = new Property(10, int.class, "leftCreditMoney", false, "leftCreditMoney");
        public final static Property NickName = new Property(11, String.class, "nickName", false, "nickName");
        public final static Property Password = new Property(12, String.class, "password", false, "password");
        public final static Property Phone = new Property(13, String.class, "phone", false, "phone");
        public final static Property RegisterTime = new Property(14, String.class, "registerTime", false, "registerTime");
        public final static Property ShebaoCertify = new Property(15, String.class, "shebaoCertify", false, "shebaoCertify");
        public final static Property TaobaoCertify = new Property(16, String.class, "taobaoCertify", false, "taobaoCertify");
        public final static Property TongXunLu = new Property(17, String.class, "tongXunLu", false, "tongXunLu");
        public final static Property XuexinCertify = new Property(18, String.class, "xuexinCertify", false, "xuexinCertify");
        public final static Property YanghangCertify = new Property(19, String.class, "yanghangCertify", false, "yanghangCertify");
        public final static Property YunYingShang = new Property(20, String.class, "yunYingShang", false, "yunYingShang");
        public final static Property ZhiMaCertify = new Property(21, String.class, "zhiMaCertify", false, "zhiMaCertify");
        public final static Property IdCard = new Property(22, String.class, "idCard", false, "idcard");
        public final static Property Name = new Property(23, String.class, "name", false, "name");
        public final static Property BankCard = new Property(24, String.class, "bankCard", false, "bankCard");
        public final static Property Card_fanmian_src = new Property(25, String.class, "card_fanmian_src", false, "card_fanmian_src");
        public final static Property Card_zipai_src = new Property(26, String.class, "card_zipai_src", false, "card_zipai_src");
        public final static Property ShopAddress = new Property(27, String.class, "shopAddress", false, "shopAddress");
        public final static Property LhbOpen = new Property(28, String.class, "lhbOpen", false, "lhbOpen");
        public final static Property JiBenCertify = new Property(29, String.class, "jiBenCertify", false, "jiBenCertify");
        public final static Property JinJiCertify = new Property(30, String.class, "jinJiCertify", false, "jinJiCertify");
        public final static Property Token = new Property(31, String.class, "token", false, "token");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"User\" (" + //
                "\"id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"bankCardCertify\" TEXT," + // 1: bankCardCertify
                "\"card_zhengmian_src\" TEXT," + // 2: card_zhengmian_src
                "\"creditLong\" INTEGER NOT NULL ," + // 3: creditLong
                "\"creditMoney\" INTEGER NOT NULL ," + // 4: creditMoney
                "\"gongjijinCertify\" TEXT," + // 5: gongjijinCertify
                "\"idCardCertify\" TEXT," + // 6: idCardCertify
                "\"imgCodeUrl\" TEXT," + // 7: imgCodeUrl
                "\"inviteCode\" TEXT," + // 8: inviteCode
                "\"jingdongCertify\" TEXT," + // 9: jingdongCertify
                "\"leftCreditMoney\" INTEGER NOT NULL ," + // 10: leftCreditMoney
                "\"nickName\" TEXT," + // 11: nickName
                "\"password\" TEXT," + // 12: password
                "\"phone\" TEXT," + // 13: phone
                "\"registerTime\" TEXT," + // 14: registerTime
                "\"shebaoCertify\" TEXT," + // 15: shebaoCertify
                "\"taobaoCertify\" TEXT," + // 16: taobaoCertify
                "\"tongXunLu\" TEXT," + // 17: tongXunLu
                "\"xuexinCertify\" TEXT," + // 18: xuexinCertify
                "\"yanghangCertify\" TEXT," + // 19: yanghangCertify
                "\"yunYingShang\" TEXT," + // 20: yunYingShang
                "\"zhiMaCertify\" TEXT," + // 21: zhiMaCertify
                "\"idcard\" TEXT," + // 22: idCard
                "\"name\" TEXT," + // 23: name
                "\"bankCard\" TEXT," + // 24: bankCard
                "\"card_fanmian_src\" TEXT," + // 25: card_fanmian_src
                "\"card_zipai_src\" TEXT," + // 26: card_zipai_src
                "\"shopAddress\" TEXT," + // 27: shopAddress
                "\"lhbOpen\" TEXT," + // 28: lhbOpen
                "\"jiBenCertify\" TEXT," + // 29: jiBenCertify
                "\"jinJiCertify\" TEXT," + // 30: jinJiCertify
                "\"token\" TEXT);"); // 31: token
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"User\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String bankCardCertify = entity.getBankCardCertify();
        if (bankCardCertify != null) {
            stmt.bindString(2, bankCardCertify);
        }
 
        String card_zhengmian_src = entity.getCard_zhengmian_src();
        if (card_zhengmian_src != null) {
            stmt.bindString(3, card_zhengmian_src);
        }
        stmt.bindLong(4, entity.getCreditLong());
        stmt.bindLong(5, entity.getCreditMoney());
 
        String gongjijinCertify = entity.getGongjijinCertify();
        if (gongjijinCertify != null) {
            stmt.bindString(6, gongjijinCertify);
        }
 
        String idCardCertify = entity.getIdCardCertify();
        if (idCardCertify != null) {
            stmt.bindString(7, idCardCertify);
        }
 
        String imgCodeUrl = entity.getImgCodeUrl();
        if (imgCodeUrl != null) {
            stmt.bindString(8, imgCodeUrl);
        }
 
        String inviteCode = entity.getInviteCode();
        if (inviteCode != null) {
            stmt.bindString(9, inviteCode);
        }
 
        String jingdongCertify = entity.getJingdongCertify();
        if (jingdongCertify != null) {
            stmt.bindString(10, jingdongCertify);
        }
        stmt.bindLong(11, entity.getLeftCreditMoney());
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(12, nickName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(13, password);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(14, phone);
        }
 
        String registerTime = entity.getRegisterTime();
        if (registerTime != null) {
            stmt.bindString(15, registerTime);
        }
 
        String shebaoCertify = entity.getShebaoCertify();
        if (shebaoCertify != null) {
            stmt.bindString(16, shebaoCertify);
        }
 
        String taobaoCertify = entity.getTaobaoCertify();
        if (taobaoCertify != null) {
            stmt.bindString(17, taobaoCertify);
        }
 
        String tongXunLu = entity.getTongXunLu();
        if (tongXunLu != null) {
            stmt.bindString(18, tongXunLu);
        }
 
        String xuexinCertify = entity.getXuexinCertify();
        if (xuexinCertify != null) {
            stmt.bindString(19, xuexinCertify);
        }
 
        String yanghangCertify = entity.getYanghangCertify();
        if (yanghangCertify != null) {
            stmt.bindString(20, yanghangCertify);
        }
 
        String yunYingShang = entity.getYunYingShang();
        if (yunYingShang != null) {
            stmt.bindString(21, yunYingShang);
        }
 
        String zhiMaCertify = entity.getZhiMaCertify();
        if (zhiMaCertify != null) {
            stmt.bindString(22, zhiMaCertify);
        }
 
        String idCard = entity.getIdCard();
        if (idCard != null) {
            stmt.bindString(23, idCard);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(24, name);
        }
 
        String bankCard = entity.getBankCard();
        if (bankCard != null) {
            stmt.bindString(25, bankCard);
        }
 
        String card_fanmian_src = entity.getCard_fanmian_src();
        if (card_fanmian_src != null) {
            stmt.bindString(26, card_fanmian_src);
        }
 
        String card_zipai_src = entity.getCard_zipai_src();
        if (card_zipai_src != null) {
            stmt.bindString(27, card_zipai_src);
        }
 
        String shopAddress = entity.getShopAddress();
        if (shopAddress != null) {
            stmt.bindString(28, shopAddress);
        }
 
        String lhbOpen = entity.getLhbOpen();
        if (lhbOpen != null) {
            stmt.bindString(29, lhbOpen);
        }
 
        String jiBenCertify = entity.getJiBenCertify();
        if (jiBenCertify != null) {
            stmt.bindString(30, jiBenCertify);
        }
 
        String jinJiCertify = entity.getJinJiCertify();
        if (jinJiCertify != null) {
            stmt.bindString(31, jinJiCertify);
        }
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(32, token);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String bankCardCertify = entity.getBankCardCertify();
        if (bankCardCertify != null) {
            stmt.bindString(2, bankCardCertify);
        }
 
        String card_zhengmian_src = entity.getCard_zhengmian_src();
        if (card_zhengmian_src != null) {
            stmt.bindString(3, card_zhengmian_src);
        }
        stmt.bindLong(4, entity.getCreditLong());
        stmt.bindLong(5, entity.getCreditMoney());
 
        String gongjijinCertify = entity.getGongjijinCertify();
        if (gongjijinCertify != null) {
            stmt.bindString(6, gongjijinCertify);
        }
 
        String idCardCertify = entity.getIdCardCertify();
        if (idCardCertify != null) {
            stmt.bindString(7, idCardCertify);
        }
 
        String imgCodeUrl = entity.getImgCodeUrl();
        if (imgCodeUrl != null) {
            stmt.bindString(8, imgCodeUrl);
        }
 
        String inviteCode = entity.getInviteCode();
        if (inviteCode != null) {
            stmt.bindString(9, inviteCode);
        }
 
        String jingdongCertify = entity.getJingdongCertify();
        if (jingdongCertify != null) {
            stmt.bindString(10, jingdongCertify);
        }
        stmt.bindLong(11, entity.getLeftCreditMoney());
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(12, nickName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(13, password);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(14, phone);
        }
 
        String registerTime = entity.getRegisterTime();
        if (registerTime != null) {
            stmt.bindString(15, registerTime);
        }
 
        String shebaoCertify = entity.getShebaoCertify();
        if (shebaoCertify != null) {
            stmt.bindString(16, shebaoCertify);
        }
 
        String taobaoCertify = entity.getTaobaoCertify();
        if (taobaoCertify != null) {
            stmt.bindString(17, taobaoCertify);
        }
 
        String tongXunLu = entity.getTongXunLu();
        if (tongXunLu != null) {
            stmt.bindString(18, tongXunLu);
        }
 
        String xuexinCertify = entity.getXuexinCertify();
        if (xuexinCertify != null) {
            stmt.bindString(19, xuexinCertify);
        }
 
        String yanghangCertify = entity.getYanghangCertify();
        if (yanghangCertify != null) {
            stmt.bindString(20, yanghangCertify);
        }
 
        String yunYingShang = entity.getYunYingShang();
        if (yunYingShang != null) {
            stmt.bindString(21, yunYingShang);
        }
 
        String zhiMaCertify = entity.getZhiMaCertify();
        if (zhiMaCertify != null) {
            stmt.bindString(22, zhiMaCertify);
        }
 
        String idCard = entity.getIdCard();
        if (idCard != null) {
            stmt.bindString(23, idCard);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(24, name);
        }
 
        String bankCard = entity.getBankCard();
        if (bankCard != null) {
            stmt.bindString(25, bankCard);
        }
 
        String card_fanmian_src = entity.getCard_fanmian_src();
        if (card_fanmian_src != null) {
            stmt.bindString(26, card_fanmian_src);
        }
 
        String card_zipai_src = entity.getCard_zipai_src();
        if (card_zipai_src != null) {
            stmt.bindString(27, card_zipai_src);
        }
 
        String shopAddress = entity.getShopAddress();
        if (shopAddress != null) {
            stmt.bindString(28, shopAddress);
        }
 
        String lhbOpen = entity.getLhbOpen();
        if (lhbOpen != null) {
            stmt.bindString(29, lhbOpen);
        }
 
        String jiBenCertify = entity.getJiBenCertify();
        if (jiBenCertify != null) {
            stmt.bindString(30, jiBenCertify);
        }
 
        String jinJiCertify = entity.getJinJiCertify();
        if (jinJiCertify != null) {
            stmt.bindString(31, jinJiCertify);
        }
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(32, token);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // bankCardCertify
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // card_zhengmian_src
            cursor.getInt(offset + 3), // creditLong
            cursor.getInt(offset + 4), // creditMoney
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // gongjijinCertify
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // idCardCertify
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // imgCodeUrl
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // inviteCode
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // jingdongCertify
            cursor.getInt(offset + 10), // leftCreditMoney
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // nickName
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // password
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // phone
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // registerTime
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // shebaoCertify
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // taobaoCertify
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // tongXunLu
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // xuexinCertify
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // yanghangCertify
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // yunYingShang
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // zhiMaCertify
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // idCard
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // name
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // bankCard
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // card_fanmian_src
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // card_zipai_src
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // shopAddress
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // lhbOpen
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // jiBenCertify
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // jinJiCertify
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31) // token
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setBankCardCertify(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCard_zhengmian_src(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCreditLong(cursor.getInt(offset + 3));
        entity.setCreditMoney(cursor.getInt(offset + 4));
        entity.setGongjijinCertify(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setIdCardCertify(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setImgCodeUrl(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setInviteCode(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setJingdongCertify(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setLeftCreditMoney(cursor.getInt(offset + 10));
        entity.setNickName(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setPassword(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setPhone(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setRegisterTime(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setShebaoCertify(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setTaobaoCertify(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setTongXunLu(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setXuexinCertify(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setYanghangCertify(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setYunYingShang(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setZhiMaCertify(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setIdCard(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setName(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setBankCard(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setCard_fanmian_src(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setCard_zipai_src(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setShopAddress(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setLhbOpen(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setJiBenCertify(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setJinJiCertify(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setToken(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
