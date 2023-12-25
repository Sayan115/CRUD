package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.crud.data.MyDbHandler;
import com.example.crud.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView contactsList;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDbHandler db=new MyDbHandler(MainActivity.this);
        /*Contact sayan=new Contact();
        sayan.setPhone_no("7867564523");
        sayan.setName("Sayan");
        db.addContact(sayan);

        Contact dev=new Contact();
        dev.setPhone_no("9472030219");
        dev.setName("Devarjya");
        db.addContact(dev);

        dev.setId(6);
        dev.setName("Devarjya Adhikari");
        dev.setPhone_no("0000000000");
        int affected_rows=db.updateContact(dev);
        Log.d("db_test","No of affected rows: "+affected_rows);
        db.deleteContactbyid(1);*/

        ArrayList<String >contacts=new ArrayList<>();
        List<Contact> allContacts=db.getAllContacts();

        //Log.d("db_test","getAllContacts() done");
        for(Contact contact:allContacts) {
            Log.d("db_test", "Name: " + contact.getName() + " id: " + contact.getId() + " Phone no: " + contact.getPhone_no());
            contacts.add(contact.getName()+"("+contact.getPhone_no()+")");
        }
        contactsList=findViewById(R.id.contactsList);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacts);
        contactsList.setAdapter(arrayAdapter);
        Log.d("db_test","You have "+db.getCount()+" contacts");
    }

}