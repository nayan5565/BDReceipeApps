package com.example.nayan.newmybdreceipetest.support;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

public class DBManager extends SQLiteOpenHelper {
    private static Context context;
    private static final int DB_VERSION = 1;
    private static String DB_NAME = "MyDB";
    private static ArrayList<String> tableQueries = new ArrayList();
    private static ArrayList<String> tables = new ArrayList();
    private static SQLiteDatabase db;
    private static DBManager instance;

    public static void init(Context mContext) {
        context = mContext;
        DB_NAME = context.getPackageName().substring(context.getPackageName().lastIndexOf("."));
    }

    private DBManager() {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory)null, 1);
        db = this.getWritableDatabase();
    }

    public static DBManager getInstance() {
        if(instance == null) {
            instance = new DBManager();
        }

        return instance;
    }

    public static void createTable(Class classOfT) {
        String dbField = "";
        String table = classOfT.getSimpleName();
        String sql = "create table if not exists " + table + "(";
        Field[] fields = classOfT.getDeclaredFields();
        Field[] var5 = fields;
        int var6 = fields.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Field field = var5[var7];
            field.setAccessible(true);
            String name = field.getName();
            String type = field.getGenericType().toString();
            if(!name.equalsIgnoreCase("serialVersionUID") && !name.equalsIgnoreCase("$change")) {
                dbField = dbField + name + " " + getType(name, type) + ",";
            }
        }

        if(!tableQueries.contains(sql)) {
            tableQueries.add(sql + " " + dbField.substring(0, dbField.length() - 1) + ")");
        }

        if(!tables.contains(table)) {
            tables.add(table);
        }

    }

    private static String getType(String name, String type) {
        return name.equals("id")?"integer primary key autoincrement":(type.equalsIgnoreCase("int")?"integer":"text");
    }

    public void onCreate(SQLiteDatabase db) {
        Iterator var2 = tableQueries.iterator();

        while(var2.hasNext()) {
            String query = (String)var2.next();
            db.execSQL(query);
        }

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private String getStringFromFile(int fileName) {
        InputStream inputStream = context.getResources().openRawResource(fileName);
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();

        String line;
        try {
            while((line = r.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return total.toString();
    }

    private boolean isExist(String table, String searchField, String value) {
        if(!value.equals("") && Integer.valueOf(value).intValue() > 0) {
            Cursor cursor = null;

            boolean var5;
            try {
                cursor = db.rawQuery("select * from " + table + " where " + searchField + "=\'" + value + "\'", (String[])null);
                if(cursor == null || cursor.getCount() <= 0) {
                    return false;
                }

                var5 = true;
            } catch (Exception var9) {
                return false;
            } finally {
                if(cursor != null) {
                    cursor.close();
                }

            }

            return var5;
        } else {
            return false;
        }
    }

    private String getStringValue(Cursor cursor, String key) {
        return cursor.getColumnIndex(key) == -1?"0":cursor.getString(cursor.getColumnIndex(key));
    }

    public long addData(Object dataModelClass, String primaryKey) {
        long result = -1L;
        String valueOfKey = "";
        String tableName = "";
        tableName = dataModelClass.getClass().getSimpleName();

        try {
            try {
                Class myClass = dataModelClass.getClass();
                Field[] fields = myClass.getDeclaredFields();
                ContentValues contentValues = new ContentValues();
                Field[] var10 = fields;
                int var11 = fields.length;

                for(int var12 = 0; var12 < var11; ++var12) {
                    Field field = var10[var12];
                    String name = field.getName();
                    field.setAccessible(true);
                    Object value = field.get(dataModelClass);
                    if(!name.equalsIgnoreCase("serialVersionUID") && !name.equalsIgnoreCase("$change")) {
                        if(!name.equals(primaryKey) || Integer.parseInt(value + "") != 0) {
                            contentValues.put(name, value + "");
                        }

                        if(name.equalsIgnoreCase(primaryKey)) {
                            valueOfKey = value + "";
                        }
                    }
                }

                if(!this.isExist(tableName, primaryKey, valueOfKey)) {
                    result = db.insert(tableName, (String)null, contentValues);
                } else {
                    result = (long)db.update(tableName, contentValues, primaryKey + "=?", new String[]{valueOfKey + ""});
                }
            } catch (Exception var19) {
                ;
            }

            return result;
        } finally {
            ;
        }
    }

    public <T> ArrayList<T> getData(Class classOfT) {
        String sql = "select * from " + classOfT.getSimpleName();
        Cursor cursor = db.rawQuery(sql, (String[])null);
        new JSONObject();
        ArrayList data = new ArrayList();
        int i;
        if(cursor != null && cursor.moveToFirst()) {
            do {
                JSONObject jsonObject = new JSONObject();

                try {
                    Field[] gson = classOfT.getDeclaredFields();
                    Field[] output = gson;
                    i = gson.length;

                    for(int var9 = 0; var9 < i; ++var9) {
                        Field field = output[var9];
                        field.setAccessible(true);
                        String name = field.getName();
                        jsonObject.put(name, this.getStringValue(cursor, name));
                    }

                    data.add(jsonObject);
                } catch (SecurityException var12) {
                    ;
                } catch (IllegalArgumentException var13) {
                    ;
                } catch (JSONException var14) {
                    var14.printStackTrace();
                }
            } while(cursor.moveToNext());
        }

        Gson var15 = new Gson();
        ArrayList var16 = new ArrayList();

        for(i = 0; i < data.size(); ++i) {
            var16.add(var15.fromJson(((JSONObject)data.get(i)).toString(), classOfT));
        }

        return var16;
    }

    public <T> ArrayList<T> getData(Class classOfT, Search... searches) {
        String searchQ = "";

        for(int sql = 0; sql < searches.length; ++sql) {
            searchQ = searchQ + searches[sql].getField() + searches[sql].getOperator() + "\'" + searches[sql].getValue() + "\'";
            if(searches.length > 1 && sql != searches.length - 1) {
                searchQ = searchQ + " OR ";
            }
        }

        String var17 = "select * from " + classOfT.getSimpleName() + " where " + searchQ;
        Cursor cursor = db.rawQuery(var17, (String[])null);
        new JSONObject();
        ArrayList data = new ArrayList();
        int i;
        if(cursor != null && cursor.moveToFirst()) {
            do {
                JSONObject jsonObject = new JSONObject();

                try {
                    Field[] gson = classOfT.getDeclaredFields();
                    Field[] output = gson;
                    i = gson.length;

                    for(int var11 = 0; var11 < i; ++var11) {
                        Field field = output[var11];
                        field.setAccessible(true);
                        String name = field.getName();
                        jsonObject.put(name, this.getStringValue(cursor, name));
                    }

                    data.add(jsonObject);
                } catch (SecurityException var14) {
                    ;
                } catch (IllegalArgumentException var15) {
                    ;
                } catch (JSONException var16) {
                    var16.printStackTrace();
                }
            } while(cursor.moveToNext());
        }

        Gson var18 = new Gson();
        ArrayList var19 = new ArrayList();

        for(i = 0; i < data.size(); ++i) {
            var19.add(var18.fromJson(((JSONObject)data.get(i)).toString(), classOfT));
        }

        return var19;
    }

    public <T> ArrayList<T> getData(Class classOfT, String sql) {
        Cursor cursor = db.rawQuery(sql, (String[])null);
        new JSONObject();
        ArrayList data = new ArrayList();
        int i;
        if(cursor != null && cursor.moveToFirst()) {
            do {
                JSONObject jsonObject = new JSONObject();

                try {
                    Field[] gson = classOfT.getDeclaredFields();
                    Field[] output = gson;
                    i = gson.length;

                    for(int var9 = 0; var9 < i; ++var9) {
                        Field field = output[var9];
                        field.setAccessible(true);
                        String name = field.getName();
                        jsonObject.put(name, this.getStringValue(cursor, name));
                    }

                    data.add(jsonObject);
                } catch (SecurityException var12) {
                    ;
                } catch (IllegalArgumentException var13) {
                    ;
                } catch (JSONException var14) {
                    var14.printStackTrace();
                }
            } while(cursor.moveToNext());
        }

        Gson var15 = new Gson();
        ArrayList var16 = new ArrayList();

        for(i = 0; i < data.size(); ++i) {
            var16.add(var15.fromJson(((JSONObject)data.get(i)).toString(), classOfT));
        }

        return var16;
    }

    public <T> void addData(ArrayList<T> list, String primaryKey) {
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            Object t = var3.next();
            this.addData(t, primaryKey);
        }

    }

    public int delete(Class modelClass, String searchField, String value) {
        return db.delete(modelClass.getSimpleName(), searchField + "=?", new String[]{value});
    }
}
