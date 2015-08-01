package com.app.c2s.application.db.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.app.c2s.application.adapters.item.ClientListItem;
import com.app.c2s.application.db.DbContract;
import com.app.c2s.application.db.models.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * ClientProvide provide basic CRUD operations
 */
public class ClientProvider extends SQLiteOpenHelper {

    // All Static variables
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_INTITULE = "intitule";
    private static final String KEY_SOLDE = "solde";
    private static final String KEY_SOLDE_INITIAL = "solde_initial";
    private static final String KEY_CA_TTC = "ca_ttc";
    private static final String KEY_REGLEMENT = "reglement";

    private static final String selectQuery = "SELECT * FROM " + DbContract.TABLE_CLIENTS;

    public ClientProvider(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
        SQLiteDatabase db=this.getWritableDatabase();
        this.onCreate(db);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CLIENTS_TABLE = "CREATE TABLE IF NOT EXISTS " + DbContract.TABLE_CLIENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_INTITULE + " TEXT,"
                + KEY_SOLDE + " FLOAT, " + KEY_REGLEMENT + " FLOAT, "
                + KEY_SOLDE_INITIAL + " FLOAT," + KEY_CA_TTC + " FLOAT" + ")";
        db.execSQL(CREATE_CLIENTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.TABLE_CLIENTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Client
    public void addClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INTITULE, client.getIntitule());
        values.put(KEY_SOLDE, client.getSolde() );
        values.put(KEY_SOLDE_INITIAL, client.getSolde_initial());
        values.put(KEY_REGLEMENT, client.getReglement());
        values.put(KEY_CA_TTC, client.getCa_ttc());

        // Inserting Row
        db.insert(DbContract.TABLE_CLIENTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Client getClient(int id) {
        //Init client
        Client client= null;
        // Open db connection
        SQLiteDatabase db = this.getReadableDatabase();
        // start Cursor to get data.
        Cursor cursor = db.query(DbContract.TABLE_CLIENTS, new String[]{KEY_ID,
                        KEY_INTITULE, KEY_REGLEMENT, KEY_SOLDE, KEY_SOLDE_INITIAL, KEY_REGLEMENT, KEY_CA_TTC}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        //if cursor is not null
        if (cursor != null){
            cursor.moveToFirst();
            client = new Client();
            cursor.close();
        }

        // return contact
        return client;
    }



    public ArrayList<ClientListItem> getAllListClientItems(){
        ArrayList<ClientListItem> articleListItems = new ArrayList<ClientListItem>();
        //open Database connection
        SQLiteDatabase db = this.getWritableDatabase();
        // get Cursor
        Cursor cursor = db.rawQuery(ClientProvider.selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Client client = new Client();
                client.setId(Integer.parseInt(cursor.getString(0)));
                client.setIntitule(cursor.getString(1));
                client.setCa_ttc(cursor.getFloat(2));
                // Adding client to list
                articleListItems.add(new ClientListItem(client));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return articleListItems;
    }

}
