package com.app.c2s.application.db.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.c2s.application.adapters.item.ArticleListItem;
import com.app.c2s.application.db.DbContract;
import com.app.c2s.application.db.models.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Article provider present Data Access Object of table article.
 */
public class ArticleProvider extends SQLiteOpenHelper {

    // All Static variables





    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_INTITULE = "intitule";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_PRIX_HT = "prix_ht";
    private static final String KEY_PRIX_TTC = "prix_ttc";
    private static final String KEY_TVA = "tva";
    private static final String KEY_CODE_ARTICLE = "code_article";

    private static final String  selectQuery = "SELECT * FROM " + DbContract.TABLE_ARTICLES;

    public ArticleProvider(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
        SQLiteDatabase db=this.getWritableDatabase();
        this.onCreate(db);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ARTICLES_TABLE = "CREATE TABLE IF NOT EXISTS " + DbContract.TABLE_ARTICLES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_INTITULE + " TEXT,"
                + KEY_PHOTO + " TEXT," + KEY_PRIX_HT + " FLOAT, " + KEY_PRIX_TTC + " FLOAT, "
                + KEY_TVA + " FLOAT," + KEY_CODE_ARTICLE + " TEXT" + ")";
        db.execSQL(CREATE_ARTICLES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.TABLE_ARTICLES);

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
        db.insert(DbContract.TABLE_ARTICLES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Article getArticle(int id) {
        Article article = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DbContract.TABLE_ARTICLES, new String[]{KEY_ID,
                        KEY_INTITULE, KEY_PHOTO, KEY_TVA, KEY_PRIX_HT, KEY_CODE_ARTICLE, KEY_PRIX_TTC}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
            article = new Article();
            cursor.close();
        }

        // return contact
        return article;
    }

    // Getting All Contacts
    public List<Article> getAllArticles() {

        List<Article> articleList = new ArrayList<Article>();
        //open Database connection
        SQLiteDatabase db = this.getWritableDatabase();
        // get Cursor
        Cursor cursor = db.rawQuery(ArticleProvider.selectQuery, null);

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
        cursor.close();
        // return contact list
        return articleList;
    }

    public ArrayList<ArticleListItem> getAllListArticlesItems(){
        ArrayList<ArticleListItem> articleListItems = new ArrayList<ArticleListItem>();
        //open Database connection
        SQLiteDatabase db = this.getWritableDatabase();
        // get Cursor
        Cursor cursor = db.rawQuery(ArticleProvider.selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Article article = new Article();
                article.setId(Integer.parseInt(cursor.getString(0)));
                article.setIntitule(cursor.getString(1));
                article.setCodeArticle(cursor.getString(2));
                // Adding contact to list
                articleListItems.add(new ArticleListItem(article));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return articleListItems;
    }
    // Getting articles Count
    public int getArticlesCount() {
        String countQuery = "SELECT  * FROM " + DbContract.TABLE_ARTICLES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
}
