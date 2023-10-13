package com.achpay.wallet.model.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.achpay.wallet.model.database.bean.LhbOpen;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LhbOpen".
*/
public class LhbOpenDao extends AbstractDao<LhbOpen, Long> {

    public static final String TABLENAME = "LhbOpen";

    /**
     * Properties of entity LhbOpen.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Localid = new Property(0, Long.class, "localid", true, "localid");
        public final static Property Cs_shoplhb = new Property(1, String.class, "cs_shoplhb", false, "cs_shoplhb");
        public final static Property Dj_shoplhb = new Property(2, String.class, "dj_shoplhb", false, "dj_shoplhb");
        public final static Property Huawei_shoplhb = new Property(3, String.class, "huawei_shoplhb", false, "huawei_shoplhb");
        public final static Property Lhb_shoplhb = new Property(4, String.class, "lhb_shoplhb", false, "lhb_shoplhb");
        public final static Property Oppo_shoplhb = new Property(5, String.class, "oppo_shoplhb", false, "oppo_shoplhb");
        public final static Property Vivo_shoplhb = new Property(6, String.class, "vivo_shoplhb", false, "vivo_shoplhb");
        public final static Property Wandoujia_shoplhb = new Property(7, String.class, "wandoujia_shoplhb", false, "wandoujia_shoplhb");
        public final static Property Xiaomi_shoplhb = new Property(8, String.class, "xiaomi_shoplhb", false, "xiaomi_shoplhb");
        public final static Property Yingyongbao_shoplhb = new Property(9, String.class, "yingyongbao_shoplhb", false, "yingyongbao_shoplhb");
    }


    public LhbOpenDao(DaoConfig config) {
        super(config);
    }
    
    public LhbOpenDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LhbOpen\" (" + //
                "\"localid\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: localid
                "\"cs_shoplhb\" TEXT," + // 1: cs_shoplhb
                "\"dj_shoplhb\" TEXT," + // 2: dj_shoplhb
                "\"huawei_shoplhb\" TEXT," + // 3: huawei_shoplhb
                "\"lhb_shoplhb\" TEXT," + // 4: lhb_shoplhb
                "\"oppo_shoplhb\" TEXT," + // 5: oppo_shoplhb
                "\"vivo_shoplhb\" TEXT," + // 6: vivo_shoplhb
                "\"wandoujia_shoplhb\" TEXT," + // 7: wandoujia_shoplhb
                "\"xiaomi_shoplhb\" TEXT," + // 8: xiaomi_shoplhb
                "\"yingyongbao_shoplhb\" TEXT);"); // 9: yingyongbao_shoplhb
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LhbOpen\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LhbOpen entity) {
        stmt.clearBindings();
 
        Long localid = entity.getLocalid();
        if (localid != null) {
            stmt.bindLong(1, localid);
        }
 
        String cs_shoplhb = entity.getCs_shoplhb();
        if (cs_shoplhb != null) {
            stmt.bindString(2, cs_shoplhb);
        }
 
        String dj_shoplhb = entity.getDj_shoplhb();
        if (dj_shoplhb != null) {
            stmt.bindString(3, dj_shoplhb);
        }
 
        String huawei_shoplhb = entity.getHuawei_shoplhb();
        if (huawei_shoplhb != null) {
            stmt.bindString(4, huawei_shoplhb);
        }
 
        String lhb_shoplhb = entity.getLhb_shoplhb();
        if (lhb_shoplhb != null) {
            stmt.bindString(5, lhb_shoplhb);
        }
 
        String oppo_shoplhb = entity.getOppo_shoplhb();
        if (oppo_shoplhb != null) {
            stmt.bindString(6, oppo_shoplhb);
        }
 
        String vivo_shoplhb = entity.getVivo_shoplhb();
        if (vivo_shoplhb != null) {
            stmt.bindString(7, vivo_shoplhb);
        }
 
        String wandoujia_shoplhb = entity.getWandoujia_shoplhb();
        if (wandoujia_shoplhb != null) {
            stmt.bindString(8, wandoujia_shoplhb);
        }
 
        String xiaomi_shoplhb = entity.getXiaomi_shoplhb();
        if (xiaomi_shoplhb != null) {
            stmt.bindString(9, xiaomi_shoplhb);
        }
 
        String yingyongbao_shoplhb = entity.getYingyongbao_shoplhb();
        if (yingyongbao_shoplhb != null) {
            stmt.bindString(10, yingyongbao_shoplhb);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LhbOpen entity) {
        stmt.clearBindings();
 
        Long localid = entity.getLocalid();
        if (localid != null) {
            stmt.bindLong(1, localid);
        }
 
        String cs_shoplhb = entity.getCs_shoplhb();
        if (cs_shoplhb != null) {
            stmt.bindString(2, cs_shoplhb);
        }
 
        String dj_shoplhb = entity.getDj_shoplhb();
        if (dj_shoplhb != null) {
            stmt.bindString(3, dj_shoplhb);
        }
 
        String huawei_shoplhb = entity.getHuawei_shoplhb();
        if (huawei_shoplhb != null) {
            stmt.bindString(4, huawei_shoplhb);
        }
 
        String lhb_shoplhb = entity.getLhb_shoplhb();
        if (lhb_shoplhb != null) {
            stmt.bindString(5, lhb_shoplhb);
        }
 
        String oppo_shoplhb = entity.getOppo_shoplhb();
        if (oppo_shoplhb != null) {
            stmt.bindString(6, oppo_shoplhb);
        }
 
        String vivo_shoplhb = entity.getVivo_shoplhb();
        if (vivo_shoplhb != null) {
            stmt.bindString(7, vivo_shoplhb);
        }
 
        String wandoujia_shoplhb = entity.getWandoujia_shoplhb();
        if (wandoujia_shoplhb != null) {
            stmt.bindString(8, wandoujia_shoplhb);
        }
 
        String xiaomi_shoplhb = entity.getXiaomi_shoplhb();
        if (xiaomi_shoplhb != null) {
            stmt.bindString(9, xiaomi_shoplhb);
        }
 
        String yingyongbao_shoplhb = entity.getYingyongbao_shoplhb();
        if (yingyongbao_shoplhb != null) {
            stmt.bindString(10, yingyongbao_shoplhb);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LhbOpen readEntity(Cursor cursor, int offset) {
        LhbOpen entity = new LhbOpen( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // localid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // cs_shoplhb
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // dj_shoplhb
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // huawei_shoplhb
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // lhb_shoplhb
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // oppo_shoplhb
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // vivo_shoplhb
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // wandoujia_shoplhb
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // xiaomi_shoplhb
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // yingyongbao_shoplhb
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LhbOpen entity, int offset) {
        entity.setLocalid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCs_shoplhb(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDj_shoplhb(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setHuawei_shoplhb(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLhb_shoplhb(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setOppo_shoplhb(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setVivo_shoplhb(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setWandoujia_shoplhb(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setXiaomi_shoplhb(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setYingyongbao_shoplhb(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LhbOpen entity, long rowId) {
        entity.setLocalid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LhbOpen entity) {
        if(entity != null) {
            return entity.getLocalid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LhbOpen entity) {
        return entity.getLocalid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}