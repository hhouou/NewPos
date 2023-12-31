package com.achpay.wallet.model.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.achpay.wallet.model.database.bean.Banner;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "banner".
*/
public class BannerDao extends AbstractDao<Banner, Long> {

    public static final String TABLENAME = "banner";

    /**
     * Properties of entity Banner.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Localid = new Property(0, Long.class, "localid", true, "localid");
        public final static Property PhotoId = new Property(1, String.class, "photoId", false, "photoId");
        public final static Property PhotoName = new Property(2, String.class, "photoName", false, "photoName");
        public final static Property PhotoDesc = new Property(3, String.class, "photoDesc", false, "photoDesc");
        public final static Property PhotoUrl = new Property(4, String.class, "photoUrl", false, "photoUrl");
        public final static Property PhotoHref = new Property(5, String.class, "photoHref", false, "photoHref");
    }


    public BannerDao(DaoConfig config) {
        super(config);
    }
    
    public BannerDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"banner\" (" + //
                "\"localid\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: localid
                "\"photoId\" TEXT UNIQUE ," + // 1: photoId
                "\"photoName\" TEXT," + // 2: photoName
                "\"photoDesc\" TEXT," + // 3: photoDesc
                "\"photoUrl\" TEXT," + // 4: photoUrl
                "\"photoHref\" TEXT);"); // 5: photoHref
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"banner\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Banner entity) {
        stmt.clearBindings();
 
        Long localid = entity.getLocalid();
        if (localid != null) {
            stmt.bindLong(1, localid);
        }
 
        String photoId = entity.getPhotoId();
        if (photoId != null) {
            stmt.bindString(2, photoId);
        }
 
        String photoName = entity.getPhotoName();
        if (photoName != null) {
            stmt.bindString(3, photoName);
        }
 
        String photoDesc = entity.getPhotoDesc();
        if (photoDesc != null) {
            stmt.bindString(4, photoDesc);
        }
 
        String photoUrl = entity.getPhotoUrl();
        if (photoUrl != null) {
            stmt.bindString(5, photoUrl);
        }
 
        String photoHref = entity.getPhotoHref();
        if (photoHref != null) {
            stmt.bindString(6, photoHref);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Banner entity) {
        stmt.clearBindings();
 
        Long localid = entity.getLocalid();
        if (localid != null) {
            stmt.bindLong(1, localid);
        }
 
        String photoId = entity.getPhotoId();
        if (photoId != null) {
            stmt.bindString(2, photoId);
        }
 
        String photoName = entity.getPhotoName();
        if (photoName != null) {
            stmt.bindString(3, photoName);
        }
 
        String photoDesc = entity.getPhotoDesc();
        if (photoDesc != null) {
            stmt.bindString(4, photoDesc);
        }
 
        String photoUrl = entity.getPhotoUrl();
        if (photoUrl != null) {
            stmt.bindString(5, photoUrl);
        }
 
        String photoHref = entity.getPhotoHref();
        if (photoHref != null) {
            stmt.bindString(6, photoHref);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Banner readEntity(Cursor cursor, int offset) {
        Banner entity = new Banner( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // localid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // photoId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // photoName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // photoDesc
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // photoUrl
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // photoHref
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Banner entity, int offset) {
        entity.setLocalid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPhotoId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPhotoName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPhotoDesc(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPhotoUrl(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPhotoHref(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Banner entity, long rowId) {
        entity.setLocalid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Banner entity) {
        if(entity != null) {
            return entity.getLocalid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Banner entity) {
        return entity.getLocalid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
