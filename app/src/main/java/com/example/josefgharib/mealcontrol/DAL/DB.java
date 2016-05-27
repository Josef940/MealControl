package com.example.josefgharib.mealcontrol.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.josefgharib.mealcontrol.BE.BEMeal;

import java.util.ArrayList;

/**
 * Created by josefgharib on 30/04/16.
 */
public class DB {
    private static final String DATABASE_NAME = "mealcontrol.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME_1 = "Meal";

    private Context context;

    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private static final String INSERT = "INSERT INTO " + TABLE_NAME_1
            + "(title,description,done) VALUES (?,?,?)";

    public DB(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
    }

    public long insert(BEMeal meal) {
        this.insertStmt.bindString(1, meal.getmTitle());
        this.insertStmt.bindString(2,meal.getmDescription());
        this.insertStmt.bindString(3, (meal.ismDone() ? "1" : "0"));
        return this.insertStmt.executeInsert();
    }

    public void deleteAll() {
        this.db.delete(TABLE_NAME_1, null, null);
    }

    public void deleteMeal(int Id)
    {
        this.db.delete(TABLE_NAME_1,"Id = "+Id,null);
    }
    public void setMealDone(int id)
    {
        db.execSQL("UPDATE " + TABLE_NAME_1 + " SET done = 1 WHERE id = " + id);
    }

    public void resetMeals()
    {
        db.execSQL("UPDATE "+ TABLE_NAME_1+" SET done = 0");
    }

    public ArrayList<BEMeal> selectAll() {
        ArrayList<BEMeal> list = new ArrayList<BEMeal>();
        Cursor cursor = this.db.query(TABLE_NAME_1, new String[] { "id", "title","description, done" },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new BEMeal(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getInt(3)==1 ? true : false));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }



    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME_1
                    + "(id INTEGER PRIMARY KEY, title TEXT, description TEXT, done INTEGER)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_1);
            onCreate(db);
        }
    }
}
