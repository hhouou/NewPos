package com.achpay.wallet.model.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.achpay.wallet.model.database.bean.LoanDay;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LoanDay".
*/
public class LoanDayDao extends AbstractDao<LoanDay, Long> {

    public static final String TABLENAME = "LoanDay";

    /**
     * Properties of entity LoanDay.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Localid = new Property(0, Long.class, "localid", true, "localid");
        public final static Property Id = new Property(1, long.class, "id", false, "id");
        public final static Property Value = new Property(2, String.class, "value", false, "value");
    }


    public LoanDayDao(DaoConfig config) {
        super(config);
    }
    
    public LoanDayDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LoanDay\" (" + //
                "\"localid\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: localid
                "\"id\" INTEGER NOT NULL UNIQUE ," + // 1: id
                "\"value\" TEXT);"); // 2: value
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LoanDay\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LoanDay entity) {
        stmt.clearBindings();
 
        Long localid = entity.getLocalid();
        if (localid != null) {
            stmt.bindLong(1, localid);
        }
        stmt.bindLong(2, entity.getId());
 
        String value = entity.getValue();
        if (value != null) {
            stmt.bindString(3, value);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LoanDay entity) {
        stmt.clearBindings();
 
        Long localid = entity.getLocalid();
        if (localid != null) {
            stmt.bindLong(1, localid);
        }
        stmt.bindLong(2, entity.getId());
 
        String value = entity.getValue();
        if (value != null) {
            stmt.bindString(3, value);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LoanDay readEntity(Cursor cursor, int offset) {
        LoanDay entity = new LoanDay( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // localid
            cursor.getLong(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // value
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LoanDay entity, int offset) {
        entity.setLocalid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.getLong(offset + 1));
        entity.setValue(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LoanDay entity, long rowId) {
        entity.setLocalid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LoanDay entity) {
        if(entity != null) {
            return entity.getLocalid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LoanDay entity) {
        return entity.getLocalid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
