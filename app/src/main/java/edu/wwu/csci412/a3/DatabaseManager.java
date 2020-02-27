package edu.wwu.csci412.a3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "friendDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FRIEND = "friends";
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " + TABLE_FRIEND + "( " + ID + " integer primary key autoincrement, " + FIRST_NAME + " text, " + LAST_NAME + " text, " + EMAIL + " text )";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if exists
        db.execSQL("drop table if exists " + TABLE_FRIEND);
        // Create Friend Table
        onCreate(db);
    }

    public void insert (Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_FRIEND + " values(null, '" + friend.getFirstName() + "',  '" + friend.getLastName() + "', '" + friend.getEmailAddress() + "' )";
        db.execSQL(sqlInsert);
        db.close();
    }

    public Friend selectByEmail(String email) {
        String sqlQuery = "select * from " + TABLE_FRIEND + " where " + EMAIL + " = '" + email + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        Friend friend = null;
        if (cursor.moveToFirst()) {
            friend = new Friend(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        return friend;
    }

    public ArrayList<Friend> selectAll() {
        String sqlQuery = "select * from " + TABLE_FRIEND;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Friend> friends = new ArrayList<>();
        while (cursor.moveToNext()) {
            Friend friend = new Friend(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            friends.add(friend);
        }
        db.close();
        return friends;
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_FRIEND;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();
    }


}
