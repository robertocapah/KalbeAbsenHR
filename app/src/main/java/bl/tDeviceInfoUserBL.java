package bl;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

import library.salesforce.common.tDeviceInfoUserData;
import library.salesforce.dal.tDeviceInfoUserDA;

public class tDeviceInfoUserBL extends clsMainBL {
	public void SaveInfoDevice(String txtUserId,String txtDeviceId, String imeiNumber){
		SQLiteDatabase db=getDb();
		tDeviceInfoUserDA _tDeviceInfoUserDA=new tDeviceInfoUserDA(db);
		tDeviceInfoUserData data= new tDeviceInfoUserData();
		int num=1;
		data.set_intId(num);
		data.set_txtUserId(txtUserId);
		data.set_txtDeviceId(txtDeviceId);
		data.set_txtDevice(Build.DEVICE);
		data.set_txtModel(Build.MANUFACTURER+" "+ Build.MODEL);
		data.set_txtImei(imeiNumber);
		data.set_txtVersion(System.getProperty("os.version"));
        _tDeviceInfoUserDA.SaveDatatDeviceInfoUserData(db, data);
	}
	public List<tDeviceInfoUserData> getData(int id){
		List<tDeviceInfoUserData> listData=new ArrayList<tDeviceInfoUserData>();
		tDeviceInfoUserDA _tDeviceInfoUserDA=new tDeviceInfoUserDA(db);
		if(id == 0){
			listData=_tDeviceInfoUserDA.getAllData(db);
		}else{
			tDeviceInfoUserData data=new tDeviceInfoUserData();
			data=_tDeviceInfoUserDA.getData(db, id);
			listData.add(data);
		}
		return listData;
	}
}
