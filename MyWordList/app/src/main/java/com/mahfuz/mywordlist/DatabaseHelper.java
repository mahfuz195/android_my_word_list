package com.mahfuz.mywordlist;

/**
 * Created by IslamMha on 9/10/2014.
 */
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.*;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "my_word_list";
    private static int VERSION = 2;

    private static String TABLE = "word";
    private static String KEY_ID = "id";
    private static String KEY_TAG = "tag";
    private static String KEY_WORD = "value";
    private static String KEY_MEAN = "meaning";
    private static String KEY_COMMENT = "comment";
    private static String KEY_RATE = "rate";

    private static String CREATE_TABLE = "CREATE TABLE " + TABLE + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " +
                                                                           KEY_TAG + " TEXT , " +
                                                                           KEY_WORD+ " TEXT , " +
                                                                           KEY_MEAN+ " TEXT , " +
                                                                           KEY_COMMENT + " TEXT ," +
                                                                           KEY_RATE + " INTEGER )";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public boolean insertWord(Word word){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TAG, word.getTag());
        values.put(KEY_WORD, word.getValue());
        values.put(KEY_MEAN,word.getMeaning());
        values.put(KEY_COMMENT,word.getMeaning());
        values.put(KEY_RATE,word.getRate());

        long id = db.insert(TABLE,null,values);
        db.close();

        if(id>0)return true;
        return false;
    }
    public List<Word> getAllWords(){
        List<Word> words = new ArrayList<Word>();
        String selectQuery = "SELECT  * FROM " + TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Word w = new Word();
                w.setId(Integer.parseInt(cursor.getString(0)));
                w.setTag(cursor.getString(1));
                w.setValue(cursor.getString(2));
                w.setMeaning(cursor.getString(3));
                w.setComment(cursor.getString(4));
                w.setRate(Integer.parseInt(cursor.getString(5)));

                words.add(w);
            } while (cursor.moveToNext());
        }

        return words;
    }
    public boolean updateWord(Word word){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_WORD,word.getValue());
        values.put(KEY_MEAN,word.getMeaning());
        values.put(KEY_COMMENT,word.getMeaning());
        values.put(KEY_TAG,word.getTag());
        values.put(KEY_RATE,word.getRate());

        int i = word.getId();
        long id = db.update(TABLE,values,KEY_ID + "= ?",new String[]{ String.valueOf(i)});
        if(id>0)return true;
        return  false;
    }
    public boolean delete_word(Word word){
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(word.getId())});
        if(id>0)return true ;
        return false;
    }
}
