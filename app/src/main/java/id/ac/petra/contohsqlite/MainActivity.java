package id.ac.petra.contohsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHandler dbHandler = new DBHandler(this);

//        dbHandler.addContact(new Contact("Budi","081222222222"));
//        dbHandler.addContact(new Contact("Andi","081233333333"));
//        dbHandler.addContact(new Contact("David","081244444444"));
        Log.d("SQLITE","Membaca isi database");
        List<Contact> contacts = dbHandler.getAllContacts();
        for (Contact c: contacts) {
            String m = "id: "+c.getId()+ " Nama: "+c.getNama()+" Phone: "+c.getPhone();
            Log.d("SQLITE",m);
        }
    }
}