package com.example.lingarajsajjan.studentviolation.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lingarajsajjan.studentviolation.model.UserCreation;
import com.example.lingarajsajjan.studentviolation.model.ViolationRegister;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "StudentViolation.db";

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_USERTABLE = "userTable";

    private static final String TABLE_VIOLATION="violation_table";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_GANDER="user_gander";
    private static final String COLUMN_USER_TYPE="user_type";
//violation table
    private static final String COLUMN_SQL_USER_ID="sql_user_id";
    private static final String COLUMN_STD_USER_ID="std_user_id";
    private static final String COLUMN_STD_USER_NAME = "std_user_name";
    private static final String COLUMN_VIOLATION_DATE = "violation_date";
    private static final String COLUMN_VIOLATION_LOCATION = "violation_location";
    private static final String COLUMN_VIOLATION_LIST="violation_list";
    private static final String COLUMN_VIOLATION_DESCRP="violation_description";
    private static final String COLUMN_VIOLATION_STATUS="violation_status";


    // create table sql quer
    private String CREATE_USER_TABLE_NEW = "CREATE TABLE " + TABLE_USERTABLE + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " VARCHAR(25) NOT NULL,"
            + COLUMN_USER_EMAIL + " VARCHAR(25) NOT NULL," + COLUMN_USER_PASSWORD + " VARCHAR(25) NOT NULL,"
            +COLUMN_USER_TYPE+" VARCHAR(25) NOT NULL"+" )";

    //create table violation
    private String CREATE_VIOLATION_TABLE = "CREATE TABLE " + TABLE_VIOLATION + "("
            + COLUMN_SQL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_STD_USER_ID + " VARCHAR(25) NOT NULL,"
            + COLUMN_STD_USER_NAME + " VARCHAR(25) NOT NULL," + COLUMN_VIOLATION_DATE + " VARCHAR(25) NOT NULL,"
            +COLUMN_VIOLATION_LOCATION+" VARCHAR(50),"+COLUMN_VIOLATION_LIST+" VARCHAR(50),"+COLUMN_VIOLATION_DESCRP+" VARCHAR(50),"
            +COLUMN_VIOLATION_STATUS+" VARCHAR(20)"+ ")";


    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USERTABLE;
    private String DROP_VIOLATION_TABLE = "DROP TABLE IF EXISTS " + TABLE_VIOLATION;

    /**
     * Constructor
     * 
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE_NEW);
        db.execSQL(CREATE_VIOLATION_TABLE);
    }

//public void createTable(SQLiteDatabase db){
//    db.execSQL(CREATE_USER_TABLE);
//}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(TABLE_USER);
        db.execSQL(TABLE_VIOLATION);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create violation record
     *
     * @param violation
     */
    public void submitViolation(ViolationRegister violation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STD_USER_ID, violation.getStdId());
        values.put(COLUMN_STD_USER_NAME, violation.getStdName());
        values.put(COLUMN_VIOLATION_DATE,violation.getViolationDate());
        values.put(COLUMN_VIOLATION_LOCATION,violation.getViolationLocation());
        values.put(COLUMN_VIOLATION_LIST,violation.getViolationType());
        values.put(COLUMN_VIOLATION_DESCRP,violation.getViolationDescription());

        // Inserting Row

        db.insert(TABLE_VIOLATION, null, values);
        db.close();
    }
    //method get all violation list
    public List<ViolationRegister> getAllViolations() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_STD_USER_ID,
                COLUMN_STD_USER_NAME,
                COLUMN_VIOLATION_DATE,
                COLUMN_VIOLATION_LOCATION,
                COLUMN_VIOLATION_LIST,
                COLUMN_VIOLATION_DESCRP
        };
        // sorting orders
        String sortOrder =
                COLUMN_STD_USER_NAME + " ASC";
        List<ViolationRegister> violationList = new ArrayList<ViolationRegister>();

        SQLiteDatabase db = this.getReadableDatabase();
        //SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery="select * from violation_table";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //labels.add(cursor.getString(1));
                //violationList.add(cursor.getString(cursor.getColumnIndex()));
                ViolationRegister violation=new ViolationRegister();
                violation.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SQL_USER_ID))));
                violation.setStdId(cursor.getString(cursor.getColumnIndex(COLUMN_STD_USER_ID)));
                violation.setStdName(cursor.getString(cursor.getColumnIndex(COLUMN_STD_USER_NAME)));
                violation.setViolationDate(cursor.getString(cursor.getColumnIndex(COLUMN_VIOLATION_DATE)));
                violation.setViolationLocation(cursor.getString(cursor.getColumnIndex(COLUMN_VIOLATION_LOCATION)));
                violation.setViolationType(cursor.getString(cursor.getColumnIndex(COLUMN_VIOLATION_LIST)));
                violation.setViolationDescription(cursor.getString(cursor.getColumnIndex(COLUMN_VIOLATION_DESCRP)));
                // Adding user record to list
                violationList.add(violation);
            } while (cursor.moveToNext());
        }

//        Cursor cursor = db.query(TABLE_VIOLATION, //Table to query
//                columns,    //columns to return
//                null,        //columns for the WHERE clause
//                null,        //The values for the WHERE clause
//                null,       //group the rows
//                null,       //filter by row groups
//                sortOrder); //The sort order
//        if (cursor.moveToFirst()) {
//            do {
//
//                ViolationRegister violation=new ViolationRegister();
//                violation.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SQL_USER_ID))));
//                violation.setStdId(cursor.getString(cursor.getColumnIndex(COLUMN_STD_USER_ID)));
//                violation.setStdName(cursor.getString(cursor.getColumnIndex(COLUMN_STD_USER_NAME)));
//                violation.setViolationDate(cursor.getString(cursor.getColumnIndex(COLUMN_VIOLATION_DATE)));
//                violation.setViolationLocation(cursor.getString(cursor.getColumnIndex(COLUMN_VIOLATION_LOCATION)));
//                violation.setViolationType(cursor.getString(cursor.getColumnIndex(COLUMN_VIOLATION_LIST)));
//                violation.setViolationDescription(cursor.getString(cursor.getColumnIndex(COLUMN_VIOLATION_DESCRP)));
//                // Adding user record to list
//                violationList.add(violation);
//            } while (cursor.moveToNext());
       // }
        cursor.close();
        db.close();

        // return user list
        return violationList;
    }
    /**
     * This method to check user exist or not
     *
     * @param stdId
     * @return true/false
     */
    public boolean checkViolation(String stdId) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_SQL_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_STD_USER_ID + " = ?";

        // selection argument
        String[] selectionArgs = {stdId};

        Cursor cursor = db.query(TABLE_VIOLATION, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method is to create user record
     *
     * @param user
     */

    public void addUser(UserCreation user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        //values.put(COLUMN_USER_GANDER,user.getGendar());
        values.put(COLUMN_USER_TYPE,user.getUserType());

        // Inserting Row

        db.insert(TABLE_USERTABLE, null, values);
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<UserCreation> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD,
                //COLUMN_USER_GANDER,
                COLUMN_USER_TYPE
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<UserCreation> userList = new ArrayList<UserCreation>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USERTABLE, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserCreation user = new UserCreation();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                //user.setGendar(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GANDER)));
                user.setUserType(cursor.getString(cursor.getColumnIndex(COLUMN_USER_TYPE)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(UserCreation user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        //values.put(COLUMN_USER_GANDER,user.getGendar());
        values.put(COLUMN_USER_TYPE,user.getUserType());


        // updating row
        db.update(TABLE_USERTABLE, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(UserCreation user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USERTABLE, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USERTABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USERTABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
