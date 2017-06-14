package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.clsHelper;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mconfigData;
import library.salesforce.common.tAbsenUserData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tAbsenUserDA;
import library.salesforce.dal.tUserLoginDA;

public class tAbsenUserBL extends clsMainBL {
	public void saveData(List<tAbsenUserData> Listdata){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);		
		for(tAbsenUserData data:Listdata){
			_tAbsenUserDA.SaveDatatAbsenUserData(db, data);	
		}
	}

    public void saveDataDownload(List<tAbsenUserData> Listdata){
        SQLiteDatabase db=getDb();
        tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
        for(tAbsenUserData data:Listdata){
            _tAbsenUserDA.saveAbsenDownload(db, data);
        }
    }

	public List<tAbsenUserData> GetData(String IdAbsen){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		List<tAbsenUserData> ListData=new ArrayList<tAbsenUserData>();
		if(IdAbsen.equals("")){
			ListData=_tAbsenUserDA.getAllData(db);
		}else{
			tAbsenUserData dt=new tAbsenUserData();
			dt=_tAbsenUserDA.getData(db, Integer.valueOf(IdAbsen));
			ListData.add(dt);
		}
		return ListData;
	}
	public tAbsenUserData getDataCheckInActive(){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		tAbsenUserData dt=new tAbsenUserData();
		dt=_tAbsenUserDA.getDataCheckInActive(db);
		return dt;
	}
	public List<tAbsenUserData>  getAllDataToPushData(){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		List<tAbsenUserData> ListData=new ArrayList<tAbsenUserData>();
		ListData=_tAbsenUserDA.getAllDataToPushData(db);
		return ListData;
	}

	public List<tAbsenUserData>  getAllDataActive(){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		List<tAbsenUserData> ListData=new ArrayList<tAbsenUserData>();
		ListData=_tAbsenUserDA.getAllDataActive(db);
		return ListData;
	}

	public List<tAbsenUserData>  getAllDataActiveOrderByDate(){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		List<tAbsenUserData> ListData=new ArrayList<tAbsenUserData>();
		ListData=_tAbsenUserDA.getAllDataActiveOrderByDate(db);
		return ListData;
	}

	public int  getContactsCount(){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		return _tAbsenUserDA.getContactsCount(db);
	}
	public void  getContactsCount(String IdData){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		_tAbsenUserDA.SaveDataSubmit(db,IdData);
	}

	public JSONArray DownloadAbsen(String versionName) throws Exception {
		SQLiteDatabase _db = getDb();
		tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
		mconfigDA _mconfigDA = new mconfigDA(_db);

		String strVal2 = "";
		mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
		strVal2 = dataAPI.get_txtValue();
		if (dataAPI.get_txtValue() == "") {
			strVal2 = dataAPI.get_txtDefaultValue();
		}
		//ambil version dari webservices
		tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
		clsHelper _help = new clsHelper();
		linkAPI dtlinkAPI = new linkAPI();
		String txtMethod = "GetDatatAbsenUser_mobile";
		JSONObject resJson = new JSONObject();
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId());
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionName);

//		String strLinkAPI = dtlinkAPI.QueryString(strVal2);
//		String JsonData = _help.pushtData(strLinkAPI, dtlinkAPI.get_txtParam(), Integer.valueOf(getBackGroundServiceOnline()));

		String strLinkAPI= dtlinkAPI.QueryString(strVal2);
		String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));

		org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);

		_db.close();
		return JsonArray;
	}
}
