package com.example.a4329619.sqlitedatabase2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 4329619 on 27/10/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "contact_db";
    private static final String TABLE_NAME = "contact";

    public DatabaseHandler (Context context)
    {
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME +
                "(id INTEGER PRIMARY KEY, name TEXT, contact TEXT)";

        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put("name", contact.getName());
        content.put("contact",contact.getName());

        db.insert(TABLE_NAME, null,content);
        db.close();
    }

    public Contact getContact (String search_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{"id","name","contact"},"name=?",
                new String[]{search_name},
                null,null,null,null);

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            Contact contact = new Contact(Integer.valueOf(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
            return contact;
        }
        return null;
    }

    public List<Contact> getAllContacts(){
        List<Contact>contactList = new ArrayList<Contact>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from " + TABLE_NAME,null);

        if (cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.valueOf(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setContact(cursor.getString(2));
                contactList.add(contact);
            }while (cursor.moveToNext());

            return contactList;
        }

        return null;
    }

    public String deleteAllContacts()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("DELETE from " + TABLE_NAME,null).moveToFirst();
        db.close();
        return "Records Deleted";

    }
}
