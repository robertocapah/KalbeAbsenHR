package library.salesforce.dal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.clsFileAttach_mobile;
//import library.salesforce.common.tNotificationData;

/**
 * Created by aan.junianto on 8/06/2017.
 */

public class clsFileAttach_mobileDA {
    public clsFileAttach_mobileDA(SQLiteDatabase db) {
        clsFileAttach_mobile dt = new clsFileAttach_mobile();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACTS + "(" + dt.Property_txtIDFile + " TEXT PRIMARY KEY,"
                + dt.Property_txtIdHeaderNotif + " TEXT NULL,"
                + dt.Property_txtDesc + " TEXT NULL,"
                + dt.Property_byteInitialVector + " TEXT NULL,"
                + dt.Property_txtLinkFileAttach + " TEXT NULL,"
                + dt.Property_txtNameFileEncrypt + " TEXT NULL,"
                + dt.Property_txtTypeFile + " TEXT NULL,"
                + dt.Property_intActive + " TEXT NULL,"
                + dt.Property_dtInserted + " TEXT NULL,"
                + dt.Property_dtUpdated + " TEXT NULL,"
                + dt.Property_intSubmit + " TEXT NULL,"
                + dt.Property_intSync + " TEXT NULL,"
                + dt.Property_intStatus + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // All Static variables

    // Contacts table name
    private static final String TABLE_CONTACTS = new clsHardCode().txtTable_clsFileAttach_mobile;

    // Upgrading database
    public void DropTable(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

    }
    // Adding new contact
    public void SaveDataMConfig(SQLiteDatabase db, clsFileAttach_mobile data) {
        clsFileAttach_mobile dt = new clsFileAttach_mobile();
        db.execSQL("INSERT OR REPLACE into " + TABLE_CONTACTS + " ("
                + dt.PropertyAll
                + ") " + "values('"
                + String.valueOf(data.get_txtIDFile()) + "','"
                + String.valueOf(data.get_txtIdHeaderNotif()) + "','"
                + String.valueOf(data.get_txtDesc()) + "','"
                + String.valueOf(data.get_byteInitialVector()) + "','"
                + String.valueOf(data.get_txtLinkFileAttach()) + "','"
                + String.valueOf(data.get_txtNameFileEncrypt()) + "','"
                + String.valueOf(data.get_txtTypeFile()) + "','"
                + String.valueOf(data.get_intActive()) + "','"
                + String.valueOf(data.get_dtInserted()) + "','"
                + String.valueOf(data.get_dtUpdated()) + "','"
                + String.valueOf(data.get_intSubmit()) + "','"
                + String.valueOf(data.get_intSync()) + "','"
                + String.valueOf(data.get_intStatus()) + "')");
			db.close();
    }
    public void DeleteAllData(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_CONTACTS );
    }
    // Getting single contact
    public clsFileAttach_mobile getData(SQLiteDatabase db, String id) {
        clsFileAttach_mobile dt = new clsFileAttach_mobile();
        String selectQuery = "SELECT  " + dt.PropertyAll + " FROM "
                + TABLE_CONTACTS+" WHERE "+dt.Property_txtIDFile+"='"+id+"'";;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        clsFileAttach_mobile contact = new clsFileAttach_mobile();
        if (cursor.getCount() > 0) {
            contact.set_txtIDFile(cursor.getString(0));
            contact.set_txtIdHeaderNotif(cursor.getString(1));
            contact.set_txtDesc(cursor.getString(2));
            contact.set_byteInitialVector(cursor.getString(3));
            contact.set_txtLinkFileAttach(cursor.getString(4));
            contact.set_txtNameFileEncrypt(cursor.getString(5));
            contact.set_txtTypeFile(cursor.getString(6));
            contact.set_intActive(cursor.getString(7));
            contact.set_dtInserted(cursor.getString(8));
            contact.set_dtUpdated(cursor.getString(9));
            contact.set_intSubmit(cursor.getString(10));
            contact.set_intSync(cursor.getString(11));
            contact.set_intStatus(cursor.getString(12));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }

    public List<clsFileAttach_mobile> getAllDataWillAlert(SQLiteDatabase db, String Status) {
        List<clsFileAttach_mobile> contactList = null;
        // Select All Query
        clsFileAttach_mobile dt = new clsFileAttach_mobile();
        String selectQuery = "SELECT  " + dt.PropertyAll + " FROM "
                + TABLE_CONTACTS +" WHERE "+dt.Property_intStatus+"='"+Status+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<clsFileAttach_mobile>();
            do {
                clsFileAttach_mobile contact = new clsFileAttach_mobile();
                contact.set_txtIDFile(cursor.getString(0));
                contact.set_txtIdHeaderNotif(cursor.getString(1));
                contact.set_txtDesc(cursor.getString(2));
                contact.set_byteInitialVector(cursor.getString(3));
                contact.set_txtLinkFileAttach(cursor.getString(4));
                contact.set_txtNameFileEncrypt(cursor.getString(5));
                contact.set_txtTypeFile(cursor.getString(6));
                contact.set_intActive(cursor.getString(7));
                contact.set_dtInserted(cursor.getString(8));
                contact.set_dtUpdated(cursor.getString(9));
                contact.set_intSubmit(cursor.getString(10));
                contact.set_intSync(cursor.getString(11));
                contact.set_intStatus(cursor.getString(12));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    public List<clsFileAttach_mobile> getAllData(SQLiteDatabase db) {
        List<clsFileAttach_mobile> contactList = null;
        // Select All Query
        clsFileAttach_mobile dt = new clsFileAttach_mobile();
        String selectQuery = "SELECT  " + dt.PropertyAll + " FROM "
                + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            contactList=new ArrayList<clsFileAttach_mobile>();
            do {
                clsFileAttach_mobile contact = new clsFileAttach_mobile();
                contact.set_txtIDFile(cursor.getString(0));
                contact.set_txtIdHeaderNotif(cursor.getString(1));
                contact.set_txtDesc(cursor.getString(2));
                contact.set_byteInitialVector(cursor.getString(3));
                contact.set_txtLinkFileAttach(cursor.getString(4));
                contact.set_txtNameFileEncrypt(cursor.getString(5));
                contact.set_txtTypeFile(cursor.getString(6));
                contact.set_intActive(cursor.getString(7));
                contact.set_dtInserted(cursor.getString(8));
                contact.set_dtUpdated(cursor.getString(9));
                contact.set_intSubmit(cursor.getString(10));
                contact.set_intSync(cursor.getString(11));
                contact.set_intStatus(cursor.getString(12));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return contactList;
    }

    /*// Getting All Contacts
    public tNotificationData getDataStatusN(SQLiteDatabase db,String status) {
        tNotificationData dt = new tNotificationData();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        dt.Property_intIndex,dt.Property_guiID, dt.Property_tPublishStart, dt.Property_dtPublishEnd,
                        dt.Property_bitActive,dt.Property_txtTitle,dt.Property_txtDescription,dt.Property_txtImage,dt.Property_txtLink,dt.Property_txtOutlet,dt.Property_txtOutletName,dt.Property_txtBranchCode,dt.Property_txtStatus,dt.Property_dtUpdate,
                        dt.Property_intSubmit,dt.Property_intSync},
                dt.Property_txtStatus + "=?", new String[] { String.valueOf(status) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        tNotificationData contact = new tNotificationData();
        if (cursor.getCount() > 0) {
            contact.set_intIndex(cursor.getString(0));
            contact.set_guiID(cursor.getString(1));
            contact.set_tPublishStart(cursor.getString(2));
            contact.set_dtPublishEnd(cursor.getString(3));
            contact.set_bitActive(cursor.getString(4));
            contact.set_txtTitle(cursor.getString(5));
            contact.set_txtDescription(cursor.getString(6));
            contact.set_txtImage(cursor.getString(7));
            contact.set_txtLink(cursor.getString(8));
            contact.set_txtOutlet(cursor.getString(9));
            contact.set_txtOutletName(cursor.getString(10));
            contact.set_txtBranchCode(cursor.getString(11));
            contact.set_txtStatus(cursor.getString(12));
            contact.set_dtUpdate(cursor.getString(13));
            contact.set_intSubmit(cursor.getString(14));
            contact.set_intSync(cursor.getString(15));
            contact.set_intPriority(cursor.getString(16));
            // return contact
        } else {
            contact = null;
        }
        cursor.close();
        return contact;
    }*/





    // Deleting single contact
    public void deleteContactByNotifId(SQLiteDatabase db,String id) {
        clsFileAttach_mobile dt = new clsFileAttach_mobile();
        db.delete(TABLE_CONTACTS, dt.Property_txtIDFile + " = ?",
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

    public void updateStatus(SQLiteDatabase db,String dataid) {
        clsFileAttach_mobile dt=new clsFileAttach_mobile();
        db.execSQL("Update "+TABLE_CONTACTS+" set  "
                +dt.Property_intStatus+"=0"
                +" Where " + dt.Property_txtIDFile +"='"+ dataid+"'");
    }
    /*public void getDataGUi(SQLiteDatabase db,String dataid) {
        tNotificationData dt=new tNotificationData();
        db.execSQL("SELECT * FROM "+TABLE_CONTACTS+" Where " + dt.Property_guiID +"='"+ dataid+"'");
    }
    //count status
    public int getContactsCountStatus(SQLiteDatabase db) {
        tNotificationData dt=new tNotificationData();
        String countQuery = "SELECT 1 FROM " + TABLE_CONTACTS +" WHERE "+dt.Property_txtStatus+"=1";
        Cursor cursor = db.rawQuery(countQuery, null);
        int num =cursor.getCount();
        cursor.close();

        // return count
        return num;
    }*/
}
