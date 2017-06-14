package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mconfigData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.common.trackingLocationData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tUserLoginDA;
import library.salesforce.dal.trackingLocationDA;

/**
 * Created by Rian Andrivani on 5/15/2017.
 */

public class trackingLocationBL extends clsMainBL {
    SQLiteDatabase db = getDb();

    public void SaveDataLocation(List<trackingLocationData> ListData) {
        SQLiteDatabase _db = getDb();
        trackingLocationDA _trackingLocationDA = new trackingLocationDA(_db);
        for (trackingLocationData data:ListData){
            _trackingLocationDA.SaveDataTrackingLocation(_db, data);
        }
        db.close();
    }

    public void SaveDataTrackingLocation(trackingLocationData dt) {
        SQLiteDatabase _db = getDb();
        trackingLocationDA _trackingLocationDA = new trackingLocationDA(_db);
        _trackingLocationDA.SaveDataDownloadTrackingLocation(_db, dt);
        db.close();
    }

    public List<trackingLocationData> getAllDataTrackingLocation() {
        SQLiteDatabase _db = getDb();
        trackingLocationDA _trackingLocationDA = new trackingLocationDA(_db);
        List<trackingLocationData> dt = _trackingLocationDA.getAllData(_db);
        db.close();
        return dt;
    }

    public org.json.simple.JSONArray DownloadTrackingLocation(String versionName) throws Exception{
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        mconfigDA _mconfigDA = new mconfigDA(_db);
        String strVAl2 = "";
        mconfigData dataApi = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVAl2 = dataApi.get_txtValue();
        if (dataApi.get_txtValue() == ""){
            strVAl2 = dataApi.get_txtDefaultValue();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateNow = dateFormat.format(date);

        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
        clsHelper _help = new clsHelper();
        linkAPI dtLinkAPI = new linkAPI();
        String txtMethod = "GetDataTrackingLocation";
        JSONObject resJson = new JSONObject();
        dtLinkAPI.set_txtMethod(txtMethod);
        dtLinkAPI.set_txtParam("|" + _dataUserLogin.get_TxtEmpId() + "|" + dateNow);
        dtLinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtLinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtLinkAPI.QueryString(strVAl2);
        String JsonData = _help.pushtData(strLinkAPI, dtLinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));
        org.json.simple.JSONArray jsonArray = _help.ResultJsonArray(JsonData);
        _db.close();
        return jsonArray;

    }
}
