package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.tDeviceInfoUserData;

public class tDeviceInfoUserDA {
	// All Static variables

	// Contacts table name
	private static final String TABLE_CONTACTS = new clsHardCode().txtTable_tDeviceInfoUser;

	// Contacts Table Columns names

	public tDeviceInfoUserDA(SQLiteDatabase db) {
		tDeviceInfoUserData dt=new tDeviceInfoUserData();
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
				+ dt.Property_intId + " INTEGER PRIMARY KEY,"
				+ dt.Property_txtDevice + " INTEGER NULL,"
				+ dt.Property_txtDeviceId + " INTEGER NULL,"
				+ dt.Property_txtModel + " TEXT NULL,"
				+ dt.Property_txtUserId + " TEXT NULL,"
				+ dt.Property_txtImei + " TEXT NULL,"
				+ dt.Property_txtVersion + " TEXT NULL" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	public void DropTable(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		// Create tables again
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void SaveDatatDeviceInfoUserData(SQLiteDatabase db,tDeviceInfoUserData data) {
		tDeviceInfoUserData dt=new tDeviceInfoUserData();
		//ContentValues values = new ContentValues();
		//values.put(dt.Property_intIdMenu, data.get_intIdMenu());
		//values.put(dt.Property_txtRoleId, data.get_txtRoleId());
		// Inserting Row
		//db.insert(TABLE_CONTACTS, null, values);
		db.execSQL("INSERT OR REPLACE into "+TABLE_CONTACTS+" ("+dt.Property_intId+","
				+dt.Property_txtDevice+","
				+dt.Property_txtDeviceId+","
				+dt.Property_txtModel+","
				+dt.Property_txtUserId+","
				+dt.Property_txtImei+","
				+dt.Property_txtVersion+") "+
			"values('"	+String.valueOf(data.get_intId())+"','"
				+String.valueOf(data.get_txtDevice())+"','"
				+String.valueOf(data.get_txtDeviceId())+"','"
				+String.valueOf(data.get_txtModel())+"','"
				+String.valueOf(data.get_txtUserId())+"','"
				+String.valueOf(data.get_txtImei())+"','"
				+String.valueOf(data.get_txtVersion())+"')");

	}

	// Getting single contact
	public tDeviceInfoUserData getData(SQLiteDatabase db, int id) {
		tDeviceInfoUserData dt=new tDeviceInfoUserData();
		tDeviceInfoUserData contact =null;
		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { dt.Property_intId,
				dt.Property_txtVersion,dt.Property_txtDevice, dt.Property_txtModel, dt.Property_txtUserId, dt.Property_txtImei,dt.Property_txtDeviceId}, dt.Property_intId + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null){
			if(cursor.getCount()>0){
				cursor.moveToFirst();
				contact = new tDeviceInfoUserData(Integer.valueOf(cursor.getString(0)),
						cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
			}
		}

		// return contact
		cursor.close();
		return contact;
	}

	// Getting All Contacts
	public List<tDeviceInfoUserData> getAllData(SQLiteDatabase db) {
		List<tDeviceInfoUserData> contactList = new ArrayList<tDeviceInfoUserData>();
		// Select All Query
		tDeviceInfoUserData dt=new tDeviceInfoUserData();
		String selectQuery = "SELECT  "+dt.Property_All+" FROM " + TABLE_CONTACTS;

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				tDeviceInfoUserData contact = new tDeviceInfoUserData();
				contact.set_intId(Integer.valueOf(cursor.getString(0)));
				contact.set_txtVersion(cursor.getString(1));
				contact.set_txtDevice(cursor.getString(2));
				contact.set_txtDeviceId(cursor.getString(3));
				contact.set_txtModel(cursor.getString(4));
				contact.set_txtUserId(cursor.getString(5));
				contact.set_txtImei(cursor.getString(6));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		// return contact list
		return contactList;
	}

	// Deleting single contact
	public void deleteContact(SQLiteDatabase db,int id) {
		tDeviceInfoUserData dt = new tDeviceInfoUserData();
		db.delete(TABLE_CONTACTS, dt.Property_intId + " = ?",
				new String[] { String.valueOf(id) });
	}

	public void DeleteAllDAta(SQLiteDatabase db) {
		// Drop older table if existed
		db.execSQL("DELETE FROM " + TABLE_CONTACTS);
	}
	// Getting contacts Count
	public int getContactsCount(SQLiteDatabase db) {
		String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(countQuery, null);
		int num =cursor.getCount();
		cursor.close();

		// return count
		return num;
	}
}
