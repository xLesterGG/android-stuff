package com.example.lesgo.a5t1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lesgo on 10/31/2016.
 */
public class DbHandler extends SQLiteOpenHelper{
    public static final int VERSION = 1;
    public static final String DB_NAME = "contact_db";
    public static final String TABLE_NAME = "contacts";


    public DbHandler (Context context)
    {
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME +
                "(id INTEGER PRIMARY KEY, name TEXT, contact TEXT , email TEXT)";

        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put("name",contact.getName());
        content.put("contact",contact.getContact());
        content.put("email",contact.getEmail());

        db.insert(TABLE_NAME,null,content);
    }

    public void updateContact(Contact contact){
        ContentValues content = new ContentValues();

        Log.d("contact details",contact.getName() );
        Log.d("contact details",contact.getContact() );
        Log.d("contact details",contact.getEmail() );

        content.put("name",contact.getName());
        content.put("contact",contact.getContact());
        content.put("email",contact.getEmail());

        //String [] args = {String.valueOf(contact.getId())};

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME,content,"id="+contact.getId(),null);

    }

    public void deleteContact(Contact contact){
        String [] args = {String.valueOf(contact.getId())};
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"id =?",args);


    }

    public Contact getContact(String search_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{"id","name","contact","email"},
                "id=?",
                new String[]{String.valueOf(search_id)},
                null,null,null,null);

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            Contact c = new Contact(Integer.valueOf(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            return c;
        }
        return null;
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from " + TABLE_NAME,null);

        if(cursor.moveToFirst()){
            do{
                Contact c = new Contact();
                c.setId(Integer.valueOf(cursor.getString(0)));
                c.setName(cursor.getString(1));
                c.setContact(cursor.getString(2));
                c.setEmail(cursor.getString(3));
                contactList.add(c);
            }while(cursor.moveToNext());
            return contactList;
        }
        return null;
    }

}
