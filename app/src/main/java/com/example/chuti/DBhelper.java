package com.example.chuti;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBhelper extends SQLiteOpenHelper {

    private  static  final String DB_NAME="xuexi.db";
    private  static  final String SCORE="Score";
    private  static  final String TAB_NAME="timu";
    private SQLiteDatabase db;

    public DBhelper(Context c){
        super(c,DB_NAME,null,2);
    }


    public void insert(ContentValues values) {
        SQLiteDatabase db=getWritableDatabase();
        db.insert(SCORE,null,values);
        db.close();
    }

    public void insert2(ContentValues values) {
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TAB_NAME,null,values);
        db.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        String CREATE_TAB="create table Score(_id integer primary key autoincrement,Score text,Date text)";
        String CREATE_TAB1="create table timu(_id integer primary key autoincrement,timu text,answer int)";
        db.execSQL(CREATE_TAB);
        db.execSQL(CREATE_TAB1);
    }
    public Cursor query(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.query(SCORE,null,null,null,null,null,null);
        return c;
    }

    public Cursor query1(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.query(TAB_NAME,null,null,null,null,null,null);
        return c;
    }
//查询题目
    public Cursor query2(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.query(TAB_NAME,null,null,null,null,null,null);
        return c;
    }


    public void del(int id) {
        if (db==null)
            db=getWritableDatabase();
        db.delete(SCORE,"_id=?",new String[]{String.valueOf(id)});
    }

    public void del1(int id) {
        if (db==null)
            db=getWritableDatabase();
        db.delete(TAB_NAME,"_id=?",new String[]{String.valueOf(id)});
    }


    public void  close(){
        if (db!=null)
            db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
