package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

import library.salesforce.common.APIData;
import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mDownloadMasterData_mobileData;
import library.salesforce.common.tDeviceInfoUserData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.mDownloadMasterData_mobileDA;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tDeviceInfoUserDA;

/**
 * Created by ASUS ZE on 02/05/2017.
 */

public class mDownloadMasterData_mobileBL extends clsMainBL {
    public void deleteAllData() {
        SQLiteDatabase db = getDb();
        mDownloadMasterData_mobileDA _mDownloadMasterData_mobileDA=new mDownloadMasterData_mobileDA(db);
        _mDownloadMasterData_mobileDA.DeleteAllDataMConfig(db);
    }
    public List<mDownloadMasterData_mobileData> GetAllData(){
        SQLiteDatabase db=getDb();
        mDownloadMasterData_mobileDA _mDownloadMasterData_mobileDA=new mDownloadMasterData_mobileDA(db);
        List<mDownloadMasterData_mobileData>ListData=_mDownloadMasterData_mobileDA.getAllData(db);
        db.close();
        return ListData;
    }
    public int getContactCount(){
        int count = 0;
        mDownloadMasterData_mobileDA _mDownloadMasterData_mobileDA=new mDownloadMasterData_mobileDA(db);
        count = _mDownloadMasterData_mobileDA.getContactsCount(db);
        return count;
    }

    public void SaveData(List<mDownloadMasterData_mobileData> Listdata) {
        SQLiteDatabase db = getDb();
        mDownloadMasterData_mobileDA _mDownloadMasterData_mobileDA = new mDownloadMasterData_mobileDA(db);
        _mDownloadMasterData_mobileDA.DeleteAllDataMConfig(db);
        Long index = Long.valueOf(_mDownloadMasterData_mobileDA.getContactsCount(db) + 1);
        for (mDownloadMasterData_mobileData data : Listdata) {
            data.set_intId(String.valueOf(index));
            _mDownloadMasterData_mobileDA.SaveDataMConfig(db, data);
            index += 1;
        }
        db.close();
    }

    public JSONArray DownloadmDownloadMasterData_mobile(String versionApp, String roleId) throws ParseException {
        SQLiteDatabase db=getDb();
        JSONArray res=new JSONArray();
        mconfigDA _mconfigDA=new mconfigDA(db);
        tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
        String txtDomain= _mconfigDA.getDomainKalbeData(db);
        //String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
        JSONObject resJson = new JSONObject();
        resJson.put("IntRoleId", "100");
        resJson.put("TxtVersionApp", versionApp);
        linkAPI dtlinkAPI=new linkAPI();
        dtlinkAPI.set_txtMethod("GetDatatDownloadMasterData_mobile");
        dtlinkAPI.set_txtParam("");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionApp);
        String strLinkAPI= dtlinkAPI.QueryString(getLinkAPI());
        APIData dtAPIDATA=new APIData();
        clsHelper _clsHelper=new clsHelper();
        String JsonData= _clsHelper.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
        res= _clsHelper.ResultJsonArray(JsonData);
        //String txtParam=
        return res;
    }
}
