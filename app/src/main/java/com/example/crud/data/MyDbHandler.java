package com.example.crud.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.crud.model.Contact;
import com.example.crud.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler( Context context) {

        super(context, Params.DB_NAME,null,Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY," + Params.KEY_NAME
                + " TEXT, " + Params.KEY_PHONE + " TEXT" + ")";
        Log.d("db_test","Table created: "+create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(Contact contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Params.KEY_NAME,contact.getName());
        values.put(Params.KEY_PHONE,contact.getPhone_no());
        long rowId=db.insert(Params.TABLE_NAME,null,values);
        if (rowId != -1) {
            Log.d("db_test", "Successfully inserted. Row ID: " + rowId);
        } else {
            Log.e("db_test", "Insertion failed.");
        }        db.close();
    }
    public List<Contact> getAllContacts(){
        List<Contact> contactList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT * FROM "+Params.TABLE_NAME;
        Cursor cursor =db.rawQuery(select,null);
        //Log.d("db_test", String.valueOf(cursor.getPosition()));
        if(cursor.moveToFirst()){
            //Log.d("db_test", String.valueOf(cursor.getPosition()));
            do {
                Contact contact=new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));

                contact.setName(cursor.getString(1));
                contact.setPhone_no(cursor.getString(2));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }Log.d("db_test","getAllContacts() done");
        return contactList;
    }

    public int updateContact(Contact contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Params.KEY_NAME,contact.getName());
        values.put(Params.KEY_PHONE,contact.getPhone_no());
        return db.update(Params.TABLE_NAME,values,Params.KEY_ID+"=?",new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContactbyid(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_ID+"=?",new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public int getCount(){
        String query="SELECT * FROM "+Params.TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();
    }
}
