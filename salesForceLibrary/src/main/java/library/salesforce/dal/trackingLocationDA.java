package library.salesforce.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library.salesforce.common.trackingLocationData;

/**
 * Created by Rian Andrivani on 5/15/2017.
 */

public class trackingLocationDA {
    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_trackingLocation;

    // Contacts Table Columns names
    public trackingLocationDA(SQLiteDatabase db){
        trackingLocationData dt = new trackingLocationData();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
                + dt.Property_intId + " TEXT PRIMARY KEY,"
                + dt.Property_txtLongitude + " TEXT NULL,"
                + dt.Property_txtLatitude + " TEXT NULL,"
                + dt.Property_txtAccuracy + " TEXT NULL,"
                + dt.Property_txtTime + " TEXT NULL,"
                + dt.Property_txtUserId + " TEXT NULL,"
                + dt.Property_txtUsername + " TEXT NULL,"
                + dt.Property_txtRoleId + " TEXT NULL,"
                + dt.Property_txtDeviceId + " TEXT NULL,"
                + dt.Property_txtBranchCode + " TEXT NULL,"
                + dt.Property_txtOutletCode + " TEXT NULL,"
                + dt.Property_txtNIK + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL,"
                + dt.Property_intSync + " TEXT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
    }

    public void SaveDataTrackingLocation(SQLiteDatabase db, trackingLocationData data) {
        trackingLocationData dt = new trackingLocationData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_txtLongitude, String.valueOf(data.get_txtLongitude()));
        cv.put(dt.Property_txtLatitude, String.valueOf(data.get_txtLatitude()));
        cv.put(dt.Property_txtAccuracy, String.valueOf(data.get_txtAccuracy()));
        cv.put(dt.Property_txtTime, String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        cv.put(dt.Property_txtUserId, String.valueOf(data.get_txtUserId()));
        cv.put(dt.Property_txtUsername, String.valueOf(data.get_txtUsername()));
        cv.put(dt.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
        cv.put(dt.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
        cv.put(dt.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
        cv.put(dt.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
        cv.put(dt.Property_txtNIK, String.valueOf(data.get_txtNIK()));
        cv.put(dt.Property_intSubmit, String.valueOf(data.get_intSubmit()));
        cv.put(dt.Property_intSync, String.valueOf(data.get_intSync()));
        if (data.get_intId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_intId, String.valueOf(data.get_intId()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }

    public void SaveDataDownloadTrackingLocation(SQLiteDatabase db, trackingLocationData data) {
        trackingLocationData dt = new trackingLocationData();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_txtLongitude, String.valueOf(data.get_txtLongitude()));
        cv.put(dt.Property_txtLatitude, String.valueOf(data.get_txtLatitude()));
        cv.put(dt.Property_txtAccuracy, String.valueOf(data.get_txtAccuracy()));
        cv.put(dt.Property_txtTime, String.valueOf(data.get_txtTime()));
        cv.put(dt.Property_txtUserId, String.valueOf(data.get_txtUserId()));
        cv.put(dt.Property_txtUsername, String.valueOf(data.get_txtUsername()));
        cv.put(dt.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
        cv.put(dt.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
        cv.put(dt.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
        cv.put(dt.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
        cv.put(dt.Property_txtNIK, String.valueOf(data.get_txtNIK()));
        cv.put(dt.Property_intSubmit, String.valueOf(data.get_intSubmit()));
        cv.put(dt.Property_intSync, String.valueOf(data.get_intSync()));
        if (data.get_intId() == null){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            cv.put(dt.Property_intId, String.valueOf(data.get_intId()));
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }

    public List<trackingLocationData> getAllDataToPushData(SQLiteDatabase db){
        List<trackingLocationData> contactList = null;
        trackingLocationData dt = new trackingLocationData();

        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_intSubmit + "=1 AND " + dt.Property_intSync + "=0";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()){
            contactList = new ArrayList<trackingLocationData>();
            do {
                trackingLocationData contact = new trackingLocationData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtLongitude(cursor.getString(1));
                contact.set_txtLatitude(cursor.getString(2));
                contact.set_txtAccuracy(cursor.getString(3));
                contact.set_txtTime(cursor.getString(4));
                contact.set_txtUserId(cursor.getString(5));
                contact.set_txtUsername(cursor.getString(6));
                contact.set_txtRoleId(cursor.getString(7));
                contact.set_txtDeviceId(cursor.getString(8));
                contact.set_txtBranchCode(cursor.getString(9));
                contact.set_txtOutletCode(cursor.getString(10));
                contact.set_txtNIK(cursor.getString(11));
                contact.set_intSubmit(cursor.getString(12));
                contact.set_intSync(cursor.getString(13));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public List<trackingLocationData> getAllData(SQLiteDatabase db){
        List<trackingLocationData> contactList = null;
        // select query
        trackingLocationData dt = new trackingLocationData();
        String selectQuery = "SELECT " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY Time " ;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList = new ArrayList<trackingLocationData>();
            do {
                trackingLocationData contact = new trackingLocationData();
                contact.set_intId(cursor.getString(0));
                contact.set_txtLongitude(cursor.getString(1));
                contact.set_txtLatitude(cursor.getString(2));
                contact.set_txtAccuracy(cursor.getString(3));
                contact.set_txtTime(cursor.getString(4));
                contact.set_txtUserId(cursor.getString(5));
                contact.set_txtUsername(cursor.getString(6));
                contact.set_txtRoleId(cursor.getString(7));
                contact.set_txtDeviceId(cursor.getString(8));
                contact.set_txtBranchCode(cursor.getString(9));
                contact.set_txtOutletCode(cursor.getString(10));
                contact.set_txtNIK(cursor.getString(11));
                contact.set_intSubmit(cursor.getString(12));
                contact.set_intSync(cursor.getString(13));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

}
