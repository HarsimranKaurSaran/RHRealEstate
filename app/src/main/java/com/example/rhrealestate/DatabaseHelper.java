package com.example.rhrealestate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DatabaseHelper extends SQLiteOpenHelper {

    // defining all the variables
    public static final int version = 2;
    public static final String dbName = "Houses.db";

    // table one for favourite houses
    public static final String TABLE_NAME_ONE = "FavouritesHouse";
    public static final String COL1 = "id";
    public static final String COL2 = "address";
    public static final String COL3 = "price";
    public static final String COL4 = "description";
    public static final String COL5 = "image";

    // table two for register users
    public static final String TABLE_NAME_TWO = "RegisterUsers";
    public static final String COLUMN1 = "userid";
    public static final String COLUMN2 = "name";
    public static final String COLUMN3 = "email";
    public static final String COLUMN4 = "contactnumber";
    public static final String COLUMN5 = "password";
    public static final String COLUMN6 = "confirmpassword";

    // table create and drop commands for table one favourite houses
    private static final String CREATE_TABLE_ONE = "create table " + TABLE_NAME_ONE + " ( " + COL1 + " INTEGER, "
            + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " INTEGER ); ";

    private static final String DROP_TABLE_ONE = " DROP TABLE IF EXISTS " + TABLE_NAME_ONE + ";" ;

    // table create and drop commands for table two register users
    private static final String CREATE_TABLE_TWO = "create table " + TABLE_NAME_TWO + " ( " + COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN2 + " TEXT, " + COLUMN3 + " TEXT, " + COLUMN4 + " TEXT, " + COLUMN5 + " TEXT, " + COLUMN6 + " TEXT ); ";

    private static final String DROP_TABLE_TWO = " DROP TABLE IF EXISTS " + TABLE_NAME_TWO + ";" ;

    // constructor for the database helper class
    public DatabaseHelper(Context context){
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_ONE);
        db.execSQL(CREATE_TABLE_TWO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_ONE);
        db.execSQL(DROP_TABLE_TWO);
        onCreate(db);
    }

    // method to insert data into the table RegisterUsers
    public boolean insertUsers(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN2, user.getName());
        cv.put(COLUMN3, user.getEmail());
        cv.put(COLUMN4, user.getContactNumber());
        cv.put(COLUMN5, user.getPassword());
        cv.put(COLUMN6, user.getConfirmPassword());

        long result = db.insert(TABLE_NAME_TWO, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    // method to verify user
    public Boolean verifyUser(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_TWO + " where " + COLUMN2 + " = ? and " + COLUMN5 + " = ? ", new String[]{name, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // method to insert data into the table FavouritesHouse
    public boolean insertFavHouse(Houses houses){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1, houses.getHouseId());
        cv.put(COL2, houses.getAddress());
        cv.put(COL3, houses.getPrice());
        cv.put(COL4, houses.getDescription());
        cv.put(COL5, houses.getImage());

        long result = db.insert(TABLE_NAME_ONE, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    // method to delete favourite houses from table
    public boolean deleteFavHouses(Houses houses){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_ONE, String.valueOf(houses.getHouseId()), null);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    // method to check if favhouse exists in table
    public boolean checkFavHouses(int houseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_ONE + " where " + COL1 + " = ? ", new String[]{String.valueOf(houseId)});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // method to show all the data from the table
    public Cursor viewFavHouseData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select * from " + TABLE_NAME_ONE, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return  cursor;
    }
}
