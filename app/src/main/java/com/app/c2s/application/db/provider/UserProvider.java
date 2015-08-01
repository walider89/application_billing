package com.app.c2s.application.db.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.c2s.application.db.DbContract;
import com.app.c2s.application.db.models.Article;
import com.app.c2s.application.db.models.User;

/**
 * This class provide access to user table and some CRUD functionality
 */
public class UserProvider extends SQLiteOpenHelper {

    // All Static variables
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NOM = "nom";
    private static final String KEY_PRENOM = "prenom";
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REGLEMENT = "reglement";

    private static final String selectQuery = "SELECT * FROM " + DbContract.TABLE_USERS;
    private static UserProvider INSTANCE = null;

    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized UserProvider getInstance(Context context)
    {
        if (INSTANCE == null)
        { 	INSTANCE = new UserProvider(context);
        }
        return INSTANCE;
    }

    public UserProvider(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
        SQLiteDatabase db=this.getWritableDatabase();
        this.onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CLIENTS_TABLE = "CREATE TABLE IF NOT EXISTS " + DbContract.TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOM + " TEXT,"
                + KEY_PRENOM + " TEXT, " + KEY_USER_NAME + " TEXT, "
                + KEY_PASSWORD + " TEXT," + KEY_REGLEMENT + " TEXT" + ")";
        db.execSQL(CREATE_CLIENTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.TABLE_USERS);
        // Create tables again
        onCreate(db);
    }

    // query for a user with corresponding username and password.
    public synchronized  User getUser(String username, String password){
        // return User object
        User user = null;
        // Open connection
        SQLiteDatabase db = this.getReadableDatabase();
        //create the select query
        String selectLoginQuery = "SELECT * FROM " + DbContract.TABLE_USERS + " WHERE "
               +KEY_USER_NAME+ "='"+ username +"' AND "+KEY_PASSWORD+" = '"+ password +"'";
        // execute query
        Cursor cursor = db.rawQuery(selectLoginQuery, null);
        // if success and user exist
        if (cursor != null)
        {
            if(cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID));
                String nom = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NOM));
                String prenom = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PRENOM));
                String user_name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_USER_NAME));
                String pwd = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PASSWORD));
                // fill user object within returned data from query
                user = new User(id, nom, prenom, user_name, pwd);
            }
            // close cursor
            cursor.close();
        }
        return user;
    }

    public boolean login(String username, String password){
        if (username != "" && password != ""){
            return this.getUser(username, password) != null;
        }
        return false;
    }

    // Adding new Client
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOM, user.getNom());
        values.put(KEY_PRENOM, user.getPrenom() );
        values.put(KEY_USER_NAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());


        // Inserting Row
        db.insert(DbContract.TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }



}
