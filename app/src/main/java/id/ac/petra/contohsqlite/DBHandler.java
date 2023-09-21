package id.ac.petra.contohsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "ContactsManager";
    private static final String TB_NAME = "Contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";


    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Query ="CREATE TABLE "+TB_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY,"+
                KEY_NAME+" TEXT,"+KEY_PHONE+" TEXT"+")";
        sqLiteDatabase.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TB_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addContact(Contact c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,c.getNama());
        cv.put(KEY_PHONE,c.getPhone());
        db.insert(TB_NAME,null,cv);
        db.close();
    }

    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TB_NAME,new String[] {KEY_ID,KEY_NAME,KEY_PHONE},
                KEY_ID+"=?",new String[] {String.valueOf(id)},null,null,null);
        if (c!=null)
            c.moveToFirst();
        Contact contact = new Contact(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2));
        return contact;
    }

    public List<Contact> getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Contact> contactList = new ArrayList<Contact>();

        String query = "SELECT * FROM "+TB_NAME;
        Cursor c = db.rawQuery(query,null);

        if (c.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(c.getString(0)));
                contact.setNama(c.getString(1));
                contact.setPhone(c.getString(2));
                contactList.add(contact);
            } while (c.moveToNext());
        }
        return contactList;
    }

}
