package com.app.c2s.application.db.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.c2s.application.db.models.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nawel on 7/24/2015.
 */
public class ArticleHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION =2;

    // Database Name
    private static final String DATABASE_NAME = "app_db";

    // Contacts table name
    private static final String TABLE_ARTICLES = "articles";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_INTITULE = "intitule";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_PRIX_HT = "prix_ht";
    private static final String KEY_PRIX_TTC = "prix_ttc";
    private static final String KEY_TVA = "tva";
    private static final String KEY_CODE_ARTICLE = "code_article";

    public ArticleHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ARTICLES_TABLE = "CREATE TABLE " + TABLE_ARTICLES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_INTITULE + " TEXT,"
                + KEY_PHOTO + " TEXT," + KEY_PRIX_HT + " FLOAT, " + KEY_PRIX_TTC + " FLOAT, "
                + KEY_TVA + " FLOAT," + KEY_CODE_ARTICLE + " TEXT" + ")";
        db.execSQL(CREATE_ARTICLES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLES);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void addArticle(Article article) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INTITULE, article.getIntitule());
        values.put(KEY_PHOTO, article.getPhoto() );
        values.put(KEY_TVA, article.getTva());
        values.put(KEY_PRIX_HT, article.getPrixHt());
        values.put(KEY_CODE_ARTICLE, article.getCodeArticle());
        values.put(KEY_PRIX_TTC, article.getPrixTtc());

        // Inserting Row
        db.insert(TABLE_ARTICLES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Article getArticle(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ARTICLES, new String[] { KEY_ID,
                        KEY_INTITULE, KEY_PHOTO, KEY_TVA, KEY_PRIX_HT, KEY_CODE_ARTICLE, KEY_PRIX_TTC  }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Article article = new Article();
        // return contact
        return article;
    }

    // Getting All Contacts
    public List<Article> getAllArticles() {

        List<Article> articleList = new ArrayList<Article>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ARTICLES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Article article = new Article();
                article.setId(Integer.parseInt(cursor.getString(0)));
                article.setIntitule(cursor.getString(1));
                article.setCodeArticle(cursor.getString(2));
                // Adding contact to list
                articleList.add(article);
            } while (cursor.moveToNext());
        }

        // return contact list
        return articleList;
    }

    // Updating single contact
    public int updateArticle(Article article) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INTITULE, article.getIntitule());
        values.put(KEY_CODE_ARTICLE, article.getCodeArticle());

        // updating row
        return db.update(TABLE_ARTICLES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(article.getId()) });
    }

    // Deleting single article
    public void deleteArticle(Article article) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ARTICLES, KEY_ID + " = ?",
                new String[] { String.valueOf(article.getId()) });
        db.close();
    }


    // Getting articles Count
    public int getArticlesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ARTICLES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
