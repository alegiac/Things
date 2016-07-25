package com.alegiac.things.store;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.alegiac.things.models.Category;
import com.alegiac.things.models.Thing;
import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alessandro.giacomell on 25/07/16.
 */
public class DbManager extends SQLiteOpenHelper
{
    private static final String CREATE_CATEGORY_TABLE =
            "CREATE TABLE category (" +
                    "c_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "c_name TEXT, " +
                    "c_color NUMERIC, " +
                    "c_image NUMERIC " +
            ");";

    private static final String CREATE_THINGS_TABLE =
            "CREATE TABLE thing (" +
                    "t_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "t_title TEXT, " +
                    "t_body TEXT, " +
                    "t_created_ts NUMERIC, " +
                    "t_lastedit_ts NUMERIC, " +
                    "t_category_id NUMERIC, " +
                    "FOREIGN KEY (t_category_id) REFERENCES category (c_id)" +
            ");";

    private SQLiteDatabase mDatabase;
    private Context mContext;


    public DbManager(Context context)
    {
        super(context,"things.db",null,1);
        this.mContext = context;
        this.mDatabase = this.getWritableDatabase();
    }

    private void createCategoriesTable(SQLiteDatabase db)
    {
        db.execSQL(CREATE_CATEGORY_TABLE);

    }

    private void createThingsTable(SQLiteDatabase db)
    {

        db.execSQL(CREATE_THINGS_TABLE);
    }

    private int getThingsCountByCategoryId(long identifier)
    {
        Cursor cursor = mDatabase.rawQuery("SELECT COUNT(t_id) FROM thing where t_category_id = ?", new String[]{identifier + ""});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    private Category getCategoryByCursor(Cursor cursor)
    {
        Category category = new Category();
        category.setIdentifier(cursor.getLong(0));
        category.setName(cursor.getString(1));
        category.setColorReference(cursor.getInt(2));
        category.setImageReference(cursor.getInt(3));
        category.setThingCount(getThingsCountByCategoryId(category.getIdentifier()));
    }

    public void insertThing(Thing thing)
    {
        ContentValues record = new ContentValues();
        record.put("t_title",thing.getTitle());
        record.put("t_body",thing.getBody());
        record.put("t_created_ts",thing.getInsertTimestamp());
        record.put("t_category_id",thing.getCategory().getIdentifier());

        try {
            mDatabase.insert("thing",null,record);
        } catch (Exception e) {
            Crashlytics.logException(e);

            /* TODO handle graphically the exception */
        }
    }

    public void insertCategory(Category category)
    {
        ContentValues record = new ContentValues();
        record.put("c_name",category.getName());
        record.put("c_color",category.getColorReference());
        record.put("c_image",category.getImageReference());

        try {
            mDatabase.insert("category",null,record);
        } catch (Exception e) {
            Crashlytics.logException(e);

            /* TODO handle graphically the exception */
        }
    }

    public List<Category> getCategories()
    {
        List<Category> categories = new ArrayList<>();

        try {
            Cursor cursor = mDatabase.rawQuery("SELECT * FROM category",null);
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                do {
                    categories.add(getCategoryByCursor(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Crashlytics.logException(e);

            /* TODO handle graphically the exception */
        }

        return categories;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        createCategoriesTable(db);
        createThingsTable(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {}
}