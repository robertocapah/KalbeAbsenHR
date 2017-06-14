package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

import library.salesforce.common.APIData;
import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.tDeviceInfoUserData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tDeviceInfoUserDA;
import library.salesforce.dal.tUserLoginDA;

public class tUserLoginBL extends clsMainBL {
	public JSONArray Logout(String versionApp) throws ParseException {
		SQLiteDatabase db=getDb();
		JSONArray res=new JSONArray();
		mconfigDA _mconfigDA=new mconfigDA(db);
		tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		List<tUserLoginData> dttAbsenUserData= _tUserLoginDA.getUserLoginNow(db);

		String txtDomain= _mconfigDA.getDomainKalbeData(db);
		//String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;

		linkAPI dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod("tUserLogin_mobileLogOut");
//		dtlinkAPI.set_txtParam(dttAbsenUserData.get(0).get_txtDataId());
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionApp);
		String strLinkAPI= dtlinkAPI.QueryString(getLinkAPI());
		APIData dtAPIDATA=new APIData();
		clsHelper _clsHelper=new clsHelper();
		String JsonData= _clsHelper.pushtData(strLinkAPI, "", Integer.valueOf(getBackGroundServiceOnline()));
		res= _clsHelper.ResultJsonArray(JsonData);
		//String txtParam=
		return res;
	}

	public JSONArray LogoutFromPushData(String versionApp) throws ParseException {
		SQLiteDatabase db=getDb();
		JSONArray res=new JSONArray();
		mconfigDA _mconfigDA=new mconfigDA(db);
		tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		List<tUserLoginData> dttAbsenUserData= _tUserLoginDA.getAllData(db);

		String txtDomain= _mconfigDA.getDomainKalbeData(db);
		//String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;

		linkAPI dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod("tUserLogin_mobileLogOut");
		dtlinkAPI.set_txtParam(dttAbsenUserData.get(0).get_txtDataId());
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionApp);
		String strLinkAPI= dtlinkAPI.QueryString(getLinkAPI());
		APIData dtAPIDATA=new APIData();
		clsHelper _clsHelper=new clsHelper();
		String JsonData= _clsHelper.pushtData(strLinkAPI, "", Integer.valueOf(getBackGroundServiceOnline()));
		res= _clsHelper.ResultJsonArray(JsonData);
		//String txtParam=
		return res;
	}

	public JSONArray Login(String txtUserName, String txtPass, String intRoleId, String versionApp) throws ParseException {
		SQLiteDatabase db=getDb();
		JSONArray res=new JSONArray();
		mconfigDA _mconfigDA=new mconfigDA(db);
		tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
		String txtDomain= _mconfigDA.getDomainKalbeData(db);
		//String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
		JSONObject resJson = new JSONObject();
		resJson.put("domain", txtDomain);
		resJson.put("username", txtUserName);
		resJson.put("pass", txtPass);
		resJson.put("deviceid", "");
		resJson.put("imeiNumber", dt.get_txtImei());
		resJson.put("version", dt.get_txtVersion());
		resJson.put("device", dt.get_txtDevice());
		resJson.put("model", dt.get_txtModel());
		resJson.put("introle", intRoleId);
		linkAPI dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod("GetDataMWebUserWithActiveDirectoryAndDatabaseNewInsentiveAndMenuAccess");
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
	public JSONArray LoginNew(String txtUserName, String txtPass, String intRoleId, String txtOutletCode, String txtOutletName, String txtBranchCode, String versionApp) throws ParseException {
		SQLiteDatabase db=getDb();
		JSONArray res=new JSONArray();
		mconfigDA _mconfigDA=new mconfigDA(db);
		tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
		String txtDomain= _mconfigDA.getDomainKalbeData(db);
		//String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
		JSONObject resJson = new JSONObject();
		resJson.put("domain", txtDomain);
		resJson.put("username", txtUserName);
		resJson.put("pass", txtPass);
		resJson.put("deviceid", "");
		resJson.put("imeiNumber", dt.get_txtImei());
		resJson.put("version", dt.get_txtVersion());
		resJson.put("device", dt.get_txtDevice());
		resJson.put("model", dt.get_txtModel());
		resJson.put("introle", intRoleId);
		resJson.put("txtOutletCode", txtOutletCode);
		resJson.put("txtOutletName", txtOutletName);
		resJson.put("txtBranchCode", txtBranchCode);
		linkAPI dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod("GetDataMWebUserWithActiveDirectoryAndDatabaseNewInsentiveAndMenuAccess");
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


	public void saveData(tUserLoginData data){
		SQLiteDatabase db=getDb();
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		_tUserLoginDA.SaveDatatUserLoginData(db, data);
	}

    public tUserLoginData getUserLogin(){
        SQLiteDatabase db=getDb();
        return new tUserLoginDA(db).getData(db, 1);
    }

	public tUserLoginData getUserLoginByUserId(String id){
		SQLiteDatabase db=getDb();
		return new tUserLoginDA(db).getDataByUserId(db, id);
	}
}
