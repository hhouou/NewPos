package com.achpay.wallet.model.database;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.internal.DaoConfig;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 95 on 2017/5/3.
 */

public class MigrationHelper {

    private static final String CONVERSION_CLASS_NOT_FOUND_EXCEPTION = "MIGRATION HELPER - CLASS DOESN'T MATCH WITH THE CURRENT PARAMETERS";
    private static volatile MigrationHelper instance;

    public static MigrationHelper getInstance() {
        if (instance == null) {
            synchronized (MigrationHelper.class) {
                if (instance == null) {
                    instance = new MigrationHelper();
                }
            }
        }
        return instance;
    }

    public  void migrate(Database db, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        generateTempTables(db, daoClasses); // 创建临时表
        dropOriginTables(db,daoClasses); // 摧毁需要更新的表
        createNewTables(db,true,daoClasses);
        restoreData(db, daoClasses); // 将临时表的数据复制过去
    }

    /**
     * 生成临时列表
     *
     * @param db
     * @param daoClasses 生成表的对应的dao
     */
    private void generateTempTables(Database db, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        for (int i = 0; i < daoClasses.length; i++) { // 检索每个DAO
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);  // DaoConfig 存储了DAOs的信息

            String divider = "";
            String tableName = daoConfig.tablename; // 原始表名
            String tempTableName = daoConfig.tablename.concat("_TEMP"); // 临时表名
            ArrayList<String> properties = new ArrayList<>();  // 用来存储临时表的所有字段

            StringBuilder createTableStringBuilder = new StringBuilder(); // 用来拼接创建临时表的sql

            createTableStringBuilder.append("CREATE TABLE ").append(tempTableName).append(" (");

            for (int j = 0; j < daoConfig.properties.length; j++) { // 检索每个字段
                String columnName = daoConfig.properties[j].columnName; // DAO 中的字段名

                if (getColumns(db, tableName).contains(columnName)) { // 检索 DAO中的字段是否在数据库表中

                    properties.add(columnName); // 如果 DAO和数据库表中都有该字段则添加进临时表字段
                    String type = null;

                    try {
                        type = getTypeByClass(daoConfig.properties[j].type);  // 得到字段的类型
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    createTableStringBuilder.append(divider).append(columnName).append(" ").append(type); // 拼接

                    if (daoConfig.properties[j].primaryKey) { //检查主键
                        createTableStringBuilder.append(" PRIMARY KEY");
                    }

                    divider = ","; // 字段建 , 分隔
                }
            }
            createTableStringBuilder.append(");");

            db.execSQL(createTableStringBuilder.toString()); // 创建出临时表

            StringBuilder insertTableStringBuilder = new StringBuilder(); //往临时表插入数据的 sql

            // 从原始表中查出数据插入到临时表
            insertTableStringBuilder.append("INSERT INTO ").append(tempTableName).append(" (");
            insertTableStringBuilder.append(TextUtils.join(",", properties));
            insertTableStringBuilder.append(") SELECT ");
            insertTableStringBuilder.append(TextUtils.join(",", properties));
            insertTableStringBuilder.append(" FROM ").append(tableName).append(";");

            db.execSQL(insertTableStringBuilder.toString());
        }
    }

    /**
     * 存储新的数据库表 以及数据
     *
     * @param db
     * @param daoClasses
     */
    private void restoreData(Database db, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        for (int i = 0; i < daoClasses.length; i++) {
            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);
            String tableName = daoConfig.tablename;
            String tempTableName = daoConfig.tablename.concat("_TEMP");
            ArrayList<String> properties = new ArrayList();

            for (int j = 0; j < daoConfig.properties.length; j++) {
                String columnName = daoConfig.properties[j].columnName;

                if (getColumns(db, tempTableName).contains(columnName)) {
                    properties.add(columnName);
                }
            }

            StringBuilder insertTableStringBuilder = new StringBuilder();

            insertTableStringBuilder.append("INSERT INTO ").append(tableName).append(" (");
            insertTableStringBuilder.append(TextUtils.join(",", properties));
            insertTableStringBuilder.append(") SELECT ");
            insertTableStringBuilder.append(TextUtils.join(",", properties));
            insertTableStringBuilder.append(" FROM ").append(tempTableName).append(";");

            StringBuilder dropTableStringBuilder = new StringBuilder();
            dropTableStringBuilder.append("DROP TABLE ").append(tempTableName);
            db.execSQL(insertTableStringBuilder.toString());
            db.execSQL(dropTableStringBuilder.toString());
        }
    }

    private String getTypeByClass(Class<?> type) throws Exception { // 把数据库数据类型与java数据类型对应
        if (type.equals(String.class)) {
            return "TEXT";
        }
        if (type.equals(Long.class) || type.equals(Integer.class) || type.equals(long.class) || type.equals(int.class)) {
            return "INTEGER";
        }
        if (type.equals(Boolean.class)) {
            return "BOOLEAN";
        }
        Exception exception = new Exception(CONVERSION_CLASS_NOT_FOUND_EXCEPTION.concat(" - Class: ").concat(type.toString()));
        exception.printStackTrace();
        throw exception;
    }

    /**
     * 获取数据库中存在的列名
     *
     * @param db        数据库
     * @param tableName 表名
     * @return
     */
    private List<String> getColumns(Database db, String tableName) {
        List<String> columns = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + tableName + " limit 1", null);
            if (cursor != null) {
                columns = new ArrayList<>(Arrays.asList(cursor.getColumnNames()));
            }
        } catch (Exception e) {
            Log.v(tableName, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return columns;
    }

    private void dropOriginTables(Database db,Class<? extends AbstractDao<?,?>>... daoClasses){
        for (int i = 0 ; i < daoClasses.length; i++){
            DaoConfig config = new DaoConfig(db,daoClasses[i]);
            db.execSQL("DROP TABLE IF EXISTS " + config.tablename);
        }
    }

    private void createNewTables(Database db,boolean flag,Class<? extends AbstractDao<?,?>>... daoClasses){
        for (int i = 0 ; i < daoClasses.length; i++){
            try {
                Method createMethod = daoClasses[i].getMethod("createTable", Database.class, boolean.class);
                createMethod.invoke(null,db,flag);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
