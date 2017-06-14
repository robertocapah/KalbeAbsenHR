package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.mDownloadMasterData_mobileData;

/**
 * Created by ASUS ZE on 02/05/2017.
 */

public class mDownloadMasterData_mobileDA {
    public mDownloadMasterData_mobileDA(SQLiteDatabase db) {
        mDownloadMasterData_mobileData dt = new mDownloadMasterData_mobileData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "("
                + dt.Property_intId + " TEXT PRIMARY KEY,"
                + dt.Property_intModule + " TEXT NULL,"
                + dt.Property_txtModuleName + " TEXT  NULL,"
                + dt.Property_txtMasterData + " TEXT  NULL,"
                + dt.Property_intVersionApp + " TEXT  NULL,"
                + dt.Property_txtTypeApp + " TEXT  NULL,"
                + dt.Property_txtVersion + " TEXT  NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_mDownloadMasterData_mobile;

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void SaveDataMConfig(SQLiteDatabase db, mDownloadMasterData_mobileData data) {
        mDownloadMasterData_mobileData dt = new mDownloadMasterData_mobileData();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.Property_intId
                + "," + dt.Property_intModule
                + ","+ dt.Property_txtModuleName
                + "," + dt.Property_txtMasterData
                + "," + dt.Property_intVersionApp
                + "," + dt.Property_txtTypeApp
                + "," + dt.Property_txtVersion
                + ") " + "values('"
                + String.valueOf(data.get_intId()) + "','"
                + String.valueOf(data.get_intModule()) + "','"
                + String.valueOf(data.get_txtModuleName()) + "','"
                + String.valueOf(data.get_txtMasterData()) + "','"
                + String.valueOf(data.get_intVersionApp()) + "','"
                + String.valueOf(data.get_txtTypeApp()) + "','"
                + String.valueOf(data.get_txtVersion()) + "')");
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    public void DeleteAllDataMConfig(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
        // db.insert(TABLE_CONTACTS, null, values);
        // db.close(); // Closing database connection
    }
    // Getting single contact
    public mDownloadMasterData_mobileData getData(SQLiteDatabase db, int id) {
        mDownloadMasterData_mobileData dt = new mDownloadMasterData_mobileData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        dt.Property_intId
                        , dt.Property_intModule
                        , dt.Property_txtModuleName
                        , dt.Property_txtMasterData
                        , dt.Property_intVersionApp
                        , dt.Property_txtTypeApp
                        , dt.Property_txtVersion},
                dt.Property_intId + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        mDownloadMasterData_mobileData contact = new mDownloadMasterData_mobileData();
        if (cursor.getCount() > 0) {
            contact.set_intId(cursor.getString(0));
            contact.set_intModule(cursor.getString(1));
            contact.set_txtModuleName(cursor.getString(2));
            contact.set_txtMasterData(cursor.getString(3));
            contact.set_intVersionApp(cursor.getString(4));
            contact.set_txtTypeApp(cursor.getString(5));
            contact.set_txtVersion(cursor.getString(6));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }

    // Getting All Contacts
    public List<mDownloadMasterData_mobileData> getAllData(SQLiteDatabase db) {
        List<mDownloadMasterData_mobileData> contactList = new ArrayList<mDownloadMasterData_mobileData>();
        // Select All Query
        mDownloadMasterData_mobileData dt = new mDownloadMasterData_mobileData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS+" ORDER BY "+ dt.Property_intModule +" ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mDownloadMasterData_mobileData contact = new mDownloadMasterData_mobileData();
                contact.set_intId(cursor.getString(0));
                contact.set_intModule(cursor.getString(1));
                contact.set_txtModuleName(cursor.getString(2));
                contact.set_txtMasterData(cursor.getString(3));
                contact.set_intVersionApp(cursor.getString(4));
                contact.set_txtTypeApp(cursor.getString(5));
                contact.set_txtVersion(cursor.getString(6));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Getting All Contacts based on masterId
    public List<mDownloadMasterData_mobileData> getDataByMasterId(SQLiteDatabase db, String masterId) {
        List<mDownloadMasterData_mobileData> contactList = new ArrayList<mDownloadMasterData_mobileData>();
        // Select All Query
        mDownloadMasterData_mobileData dt = new mDownloadMasterData_mobileData();
        String selectQuery = "SELECT  " + dt.Property_All + " FROM "
                + TABLE_CONTACTS + " WHERE "+ dt.Property_intModule +" = '"+ masterId +"' ORDER BY "+ dt.Property_txtModuleName +" ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mDownloadMasterData_mobileData contact = new mDownloadMasterData_mobileData();
                contact.set_intId(cursor.getString(0));
                contact.set_intModule(cursor.getString(1));
                contact.set_txtModuleName(cursor.getString(2));
                contact.set_txtMasterData(cursor.getString(3));
                contact.set_intVersionApp(cursor.getString(4));
                contact.set_txtTypeApp(cursor.getString(5));
                contact.set_txtVersion(cursor.getString(6));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    // Deleting single contact
    public void deleteContact(SQLiteDatabase db, int id) {
        mDownloadMasterData_mobileData dt = new mDownloadMasterData_mobileData();
        db.delete(TABLE_CONTACTS, dt.Property_intId + " = ?",
                new String[] { String.valueOf(id) });
        // db.close();
    }

    // Getting contacts Count
    public int getContactsCount(SQLiteDatabase db) {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        int countData = cursor.getCount();
        cursor.close();
        // return count
        return countData;
    }
}
