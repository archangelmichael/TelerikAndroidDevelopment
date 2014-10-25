package com.addressbook;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "AddressBookDb.db";
	public static final String CONTACTS_TABLE_NAME = "contacts";
	public static final String CONTACTS_COLUMN_ID = "id";
	public static final String CONTACTS_COLUMN_NAME = "name";
	public static final String CONTACTS_COLUMN_EMAIL = "email";
	public static final String CONTACTS_COLUMN_STREET = "street";
	public static final String CONTACTS_COLUMN_CITY = "place";
	public static final String CONTACTS_COLUMN_PHONE = "phone";

	private HashMap hp;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table contacts "
				+ "(id integer primary key, name text, phone text, email text, street text, place text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS contacts");
		onCreate(db);
	}

	public boolean insertContact(Contact contact) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues contentValues = getContentValues(contact);
		database.insert("contacts", null, contentValues);
		return true;
	}

	public Cursor getData(int id) {
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor contactInfo = database.rawQuery("select * from contacts where id=" + id + "", null);
		return contactInfo;
	}
	
	public Contact getContactData(int id) {
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor contactInfo = database.rawQuery("select * from contacts where id=" + id + "", null);
		contactInfo.moveToFirst();
		String name = contactInfo.getString(contactInfo
				.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
		String phone = contactInfo.getString(contactInfo
				.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
		String email = contactInfo.getString(contactInfo
				.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL));
		String street = contactInfo.getString(contactInfo
				.getColumnIndex(DBHelper.CONTACTS_COLUMN_STREET));
		String place = contactInfo.getString(contactInfo
				.getColumnIndex(DBHelper.CONTACTS_COLUMN_CITY));
		if (!contactInfo.isClosed()) {
			contactInfo.close();
		}
		
		return new Contact(name, phone, email, street, place);
	}

	public int numberOfRows() {
		SQLiteDatabase database = this.getReadableDatabase();
		int numRows = (int) DatabaseUtils.queryNumEntries(database,
				CONTACTS_TABLE_NAME);
		return numRows;
	}

	public boolean updateContact(Integer id, Contact contact) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues contentValues = getContentValues(contact);
		database.update("contacts", contentValues, "id = ? ",
				new String[] { Integer.toString(id) });
		return true;
	}

	public Integer deleteContact(Integer id) {
		SQLiteDatabase database = this.getWritableDatabase();
		return database.delete("contacts", "id = ? ",
				new String[] { Integer.toString(id) });
	}

	public ArrayList<String> getAllContacts() {
		ArrayList<String> array_list = new ArrayList<String>();
		// hp = new HashMap();
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor allContacts = database.rawQuery("select * from contacts", null);
		allContacts.moveToFirst();
		while (allContacts.isAfterLast() == false) {
			array_list.add(allContacts.getString(allContacts.getColumnIndex(CONTACTS_COLUMN_NAME)));
			allContacts.moveToNext();
		}
		
		return array_list;
	}
	
	private ContentValues getContentValues (Contact contact) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", contact.getName());
		contentValues.put("phone", contact.getPhone());
		contentValues.put("email", contact.getEmail());
		contentValues.put("street", contact.getStreet());
		contentValues.put("place", contact.getPlace());
		return contentValues;
	}
}
