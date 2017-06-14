package bl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import library.salesforce.common.APIData;
import library.salesforce.common.clsHelper;
import library.salesforce.common.clsStatusMenuStart;
import library.salesforce.common.dataJson;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mMenuData;
import library.salesforce.common.mconfigData;
import library.salesforce.common.tAbsenUserData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.common.trackingLocationData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.enumStatusMenuStart;
import library.salesforce.dal.mMenuDA;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tAbsenUserDA;
import library.salesforce.dal.tUserLoginDA;
import library.salesforce.dal.trackingLocationDA;

//import library.salesforce.common.KoordinasiOutletData;
//import library.salesforce.common.KoordinasiOutletImageData;
//import library.salesforce.common.tActivityData;
//import library.salesforce.common.tLeaveMobileData;
//import library.salesforce.common.tPurchaseOrderDetailData;
//import library.salesforce.common.tPurchaseOrderHeaderData;
//import library.salesforce.common.tSalesProductDetailData;
//import library.salesforce.common.tSalesProductHeaderData;
//import library.salesforce.common.tSalesProductQuantityDetailData;
//import library.salesforce.common.tSalesProductQuantityHeaderData;
//import library.salesforce.common.tSalesProductQuantityImageData;
//import library.salesforce.common.tVisitPlanRealisasiData;
//import library.salesforce.dal.KoordinasiOutletDA;
//import library.salesforce.dal.KoordinasiOutletImageDA;
//import library.salesforce.dal.tActivityDA;
//import library.salesforce.dal.tLeaveMobileDA;
//import library.salesforce.dal.tPurchaseOrderDetailDA;
//import library.salesforce.dal.tPurchaseOrderHeaderDA;
//import library.salesforce.dal.tSalesProductDetailDA;
//import library.salesforce.dal.tSalesProductHeaderDA;
//import library.salesforce.dal.tSalesProductQuantityDetailDA;
//import library.salesforce.dal.tSalesProductQuantityHeaderDA;
//import library.salesforce.dal.tSalesProductQuantityImageDA;
//import library.salesforce.dal.tUserLoginDA;
//import library.salesforce.dal.tVisitPlanRealisasiDA;
//import library.salesforce.dal.trackingLocationDA;

public class clsMainBL {
	SQLiteDatabase db;
	public clsMainBL() {
		super();
		this.db = getDb();
	}
	
	public SQLiteDatabase getDb() {
		clsHardCode _clsHardCode;
		clsHelper _clsHelper=new clsHelper();
		_clsHardCode =new clsHardCode();
		_clsHelper.createFolderApp();
		String rootDB = _clsHardCode.txtDatabaseName;
		db=SQLiteDatabase.openOrCreateDatabase(rootDB, null);
		return db;
	}
	public String getLinkAPI(){
		this.db = getDb();
		String txtLinkAPI=new mconfigDA(db).getLinkAPIData(db);
		this.db.close();
		return txtLinkAPI;
	}
	public String getTypeMobile(){
		this.db = getDb();
		String txtLinkAPI=new mconfigDA(db).getTypeMobile(db);
		this.db.close();
		return txtLinkAPI;
	}
	public String getLIVE(){
		this.db = getDb();
		String txtLinkAPI=new mconfigDA(db).getLIVE(db);
		this.db.close();
		return txtLinkAPI;
	}
	public String getBackGroundServiceOnline(){
		this.db = getDb();
		String valueBackGroundServiceOnline=new mconfigDA(db).getBackGroundServiceOnlineData(db);
		this.db.close();
		return valueBackGroundServiceOnline;
	}
	public tUserLoginData getUserActive() {
		this.db = getDb();
		List<tUserLoginData> listData;
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		listData=_tUserLoginDA.getUserLoginNow(db);
		if (listData.size()==0){
			listData=_tUserLoginDA.getAllData(db);
		}
		db.close();
		return listData.get(0);
	}
	public String GenerateGuid(){
		 UUID uuid = UUID.randomUUID();
		 String randomUUIDString = uuid.toString();
		 return randomUUIDString;
	 }
	public String GenerateGuid(Context context){
		DeviceUuidFactory _DeviceUuidFactory=new DeviceUuidFactory(context);
		return _DeviceUuidFactory.getDeviceUuid().toString();
	 }
	public clsStatusMenuStart checkUserActive() throws ParseException {
		this.db = getDb();
//		tSalesProductHeaderDA _tSalesProductHeaderDA=new tSalesProductHeaderDA(db);
//		tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(db);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
//    	tActivityDA _tActivityDA=new tActivityDA(db);
    	tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
//    	tLeaveMobileDA _tLeaveMobileDA=new tLeaveMobileDA(db);
//		tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);

    	mMenuDA _mMenuDA=new mMenuDA(db);
    	clsStatusMenuStart _clsStatusMenuStart =new clsStatusMenuStart();
    	if(_tUserLoginDA.CheckLoginNow(db)){
    		List<tUserLoginData> listData=_tUserLoginDA.getUserLoginNow(db);
    		_clsStatusMenuStart.set_intStatus(enumStatusMenuStart.UserActiveLogin);
    	}else{
    		Boolean dvalid=false;
//    		List<tSalesProductHeaderData> listDataPush= _tSalesProductHeaderDA.getAllDataToPushData(db);
//			int listPODataPush = _tPurchaseOrderHeaderDA.getAllCheckToPushData(db);
//    		List<tActivityData> listtActivityDataPush= _tActivityDA.getAllDataToPushData(db);
    		List<tAbsenUserData> listtAbsenUserDataPush= _tAbsenUserDA.getAllDataToPushData(db);
//    		List<tLeaveMobileData> listTLeave= _tLeaveMobileDA.getAllDataPushData(db);
//			List<tVisitPlanRealisasiData> listVisitplan = _tVisitPlanRealisasiDA.getPushData(db);
//    		if(listDataPush != null && dvalid==false){
//    			dvalid=true;
//    		}
//			if(listPODataPush > 0 && dvalid==false){
//				dvalid=true;
//			}
//			if(listVisitplan != null && dvalid==false){
//				dvalid=true;
//			}
//			if (listPODataPush != null && dvalid == false){
//				dvalid = true;
//			}
//    		if(listtActivityDataPush != null && dvalid==false){
//    			dvalid=true;
//    		}
    		if(listtAbsenUserDataPush != null && dvalid==false){
    			dvalid=true;
    		}
//    		if(listTLeave != null && dvalid==false){
//    			dvalid=true;
//    		}
//    		if(listDataPush != null && dvalid==false){
//    			dvalid=true;
//    		}
    		if(dvalid){
    			mMenuData listMenuData= _mMenuDA.getDataByName(db, "mnUploadDataMobile");
    			_clsStatusMenuStart.set_intStatus(enumStatusMenuStart.PushDataSPGMobile);
    			_clsStatusMenuStart.set_mMenuData(listMenuData);
    		}else{
        		new clsHelper().DeleteAllDB(db);
        		_clsStatusMenuStart.set_intStatus(enumStatusMenuStart.FormLogin);
    		}
    	}
    	this.db.close();
    	return _clsStatusMenuStart;
	}
	public dataJson GetAllPushData(String VersionName) throws Exception{
		SQLiteDatabase db=getDb();
		dataJson dtJson=new dataJson();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
//		tLeaveMobileDA _tLeaveMobileDA=new tLeaveMobileDA(db);

		db.close();
		return dtJson;
	}
	public void PushData(String VersionName) throws Exception{
		SQLiteDatabase _db=getDb();
		mconfigDA _mconfigDA =new mconfigDA(_db);
		String _StrLINKAPI="";
		mconfigData dataAPI = _mconfigDA.getData(_db, enumConfigData.ApiKalbe.getidConfigData());
		_StrLINKAPI = dataAPI.get_txtValue();
		if (dataAPI.get_txtValue() == "") {
			_StrLINKAPI = dataAPI.get_txtDefaultValue();
		}
		clsHelper _help =new clsHelper();
		String root = new clsHardCode().txtPathUserData;
		File myDir = new File(root);
		myDir.mkdirs();
		linkAPI dtlinkAPI=new linkAPI();
		String txtMethod="PushDataTabsenUser";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(VersionName);
		String strLinkAPI= dtlinkAPI.QueryString(_StrLINKAPI);
		
		dataAPI = _mconfigDA.getData(_db, enumConfigData.BackGroundServiceOnline.getidConfigData());
		String TimeOut = dataAPI.get_txtValue();
		if (dataAPI.get_txtValue() == "") {
			TimeOut = dataAPI.get_txtDefaultValue();
		}
		
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(_db);
		List<tAbsenUserData> listDataAbsen= _tAbsenUserDA.getAllDataToPushData(_db);
		if(listDataAbsen != null){
			for (tAbsenUserData dataAbsen : listDataAbsen) {
				List<tAbsenUserData> tmplistDataAbsen=new ArrayList<tAbsenUserData>();
				tmplistDataAbsen.add(dataAbsen);
				dataJson Json= new dataJson();
				Json.setIntResult("1");
				Json.setListOftAbsenUserData(tmplistDataAbsen);
				//String Html= new clsHelper().pushtData(strLinkAPI,Json.txtJSON().toString(),Integer.valueOf(TimeOut));
				String Html= new clsHelper().PushDataWithFile(strLinkAPI,Json.txtJSON().toString(),Integer.valueOf(TimeOut),String.valueOf(dataAbsen.get_txtImg1()),String.valueOf(dataAbsen.get_txtImg2()));
				org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()) {
					APIData dtAPIDATA=new APIData();
					org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
					int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
					if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataAbsen.set_intSync("1");
						dataAbsen.set_txtAbsen(String.valueOf(innerObj.get(dtAPIDATA.strArgument)));
						_tAbsenUserDA.SaveDatatAbsenUserData(_db, dataAbsen);
					}
				}
			}
		}

		/*dtlinkAPI = new linkAPI();
		txtMethod = "PushDataTHeaderPO";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtMethod(VersionName);
		strLinkAPI = dtlinkAPI.QueryString(_StrLINKAPI);

		tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(_db);
		List<tPurchaseOrderHeaderData> ListDataTPOHeader = _tPurchaseOrderHeaderDA.getAllDataToPushData(_db);
		if (ListDataTPOHeader != null) {
			for (tPurchaseOrderHeaderData dataHeader : ListDataTPOHeader) {
				dataJson Json = new dataJson();
				List<tPurchaseOrderHeaderData> tmpListDataPOHeaderData = new ArrayList<tPurchaseOrderHeaderData>();
				tmpListDataPOHeaderData.add(dataHeader);
				tAbsenUserData _tmpDataAbsen = _tAbsenUserDA.getData(_db, Integer.valueOf(dataHeader.get_intIdAbsenUser()));
				List<tAbsenUserData> tmpListDataUserAbsen = new ArrayList<tAbsenUserData>();
				tmpListDataUserAbsen.add(_tmpDataAbsen);
				tPurchaseOrderDetailDA _tPurchaseOrderDetailDA = new tPurchaseOrderDetailDA(_db);
				List<tPurchaseOrderDetailData> tmpListPurchaseOrderDetail = _tPurchaseOrderDetailDA.getPurchaseOrderDetailByHeaderId(_db, dataHeader.get_intId());
				if (tmpListPurchaseOrderDetail != null) {
					Json.setListOftPurchaseOrderDetailData(tmpListPurchaseOrderDetail);
				}
				Json.setListOftAbsenUserData(tmpListDataUserAbsen);
				Json.setListOftPurchaseOrderHeaderData(tmpListDataPOHeaderData);
				String Html = new clsHelper().pushtData(strLinkAPI, Json.txtJSON().toString(), Integer.valueOf(TimeOut));
				org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()){
					APIData dtAPIDATA = new APIData();
					JSONObject InnerObj = (JSONObject) i.next();
					int boolValid = Integer.valueOf(String.valueOf(InnerObj.get(dtAPIDATA.boolValid)));
					if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataHeader.set_intSync("1");
						_tPurchaseOrderHeaderDA.SaveDatatPurchaseOrderHeaderData(_db, dataHeader);
					}
				}

			}
		}

		dtlinkAPI = new linkAPI();
		txtMethod = "PushDataTHeaderQuantityStock";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtMethod(VersionName);
		strLinkAPI = dtlinkAPI.QueryString(_StrLINKAPI);
*/
		/*tSalesProductQuantityHeaderDA _tSalesProductQuantityHeaderDA = new tSalesProductQuantityHeaderDA(_db);
		List<tSalesProductQuantityHeaderData> ListDataTQuantityStockHeader = _tSalesProductQuantityHeaderDA.getAllDataToPushData(_db);
		if (ListDataTQuantityStockHeader != null) {
			for (tSalesProductQuantityHeaderData dataHeader : ListDataTQuantityStockHeader) {
				dataJson Json = new dataJson();
				List<tSalesProductQuantityHeaderData> tmpListDataQuantityStockHeaderData = new ArrayList<tSalesProductQuantityHeaderData>();
				tmpListDataQuantityStockHeaderData.add(dataHeader);
				tAbsenUserData _tmpDataAbsen = _tAbsenUserDA.getData(_db, Integer.valueOf(dataHeader.get_intIdAbsenUser()));
				List<tAbsenUserData> tmpListDataUserAbsen = new ArrayList<tAbsenUserData>();
				tmpListDataUserAbsen.add(_tmpDataAbsen);
				tSalesProductQuantityDetailDA _tSalesProductQuantityDetailDA = new tSalesProductQuantityDetailDA(_db);
				List<tSalesProductQuantityDetailData> tmpListQuantityStockDetail = _tSalesProductQuantityDetailDA.getSalesProductQuantityDetailByHeaderId(_db, dataHeader.get_txtQuantityStock());
				if (tmpListQuantityStockDetail != null){
					Json.setListOftSalesProductQuantityDetailData(tmpListQuantityStockDetail);
				}
				tSalesProductQuantityImageDA _tSalesProductQuantityImageDA = new tSalesProductQuantityImageDA(_db);
				List<tSalesProductQuantityImageData> tmpListQuantityStockImage = _tSalesProductQuantityImageDA.getSalesProductQuantityImageByHeaderId(_db, dataHeader.get_txtQuantityStock());
				if (tmpListQuantityStockImage != null){
					Json.setListOftSalesProductQuantityImageData(tmpListQuantityStockImage);
				}
				Json.setListOftAbsenUserData(tmpListDataUserAbsen);
				Json.setListOftSalesProductQuantityData(tmpListDataQuantityStockHeaderData);
				String Html = new clsHelper().pushtData(strLinkAPI, Json.txtJSON().toString(), Integer.valueOf(TimeOut));
				org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()){
					APIData dtAPIDATA = new APIData();
					JSONObject InnerObj = (JSONObject) i.next();
					int boolValid = Integer.valueOf(String.valueOf(InnerObj.get(dtAPIDATA.boolValid)));
					if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataHeader.set_intSync("1");
						_tSalesProductQuantityHeaderDA.SaveDataSalesProductQuantityData(_db, dataHeader);
					}
				}
			}
		}
*/
		dtlinkAPI = new linkAPI();
		txtMethod = "PushDataTrackingLocation";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtMethod(VersionName);
		strLinkAPI = dtlinkAPI.QueryString(_StrLINKAPI);

		trackingLocationDA _trackingLocationDA = new trackingLocationDA(_db);
		List<trackingLocationData> ListDataTrackingLocation = _trackingLocationDA.getAllDataToPushData(_db);
		if (ListDataTrackingLocation != null) {
			for (trackingLocationData dataHeader : ListDataTrackingLocation) {
				dataJson Json = new dataJson();
				List<trackingLocationData> tmpListDataTrackingLocationData = new ArrayList<trackingLocationData>();
				tmpListDataTrackingLocationData.add(dataHeader);
				Json.setListOfTrackingLocationData(tmpListDataTrackingLocationData);
				String Html = new clsHelper().pushtData(strLinkAPI, Json.txtJSON().toString(), Integer.valueOf(TimeOut));
				org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()){
					APIData dtAPIDATA = new APIData();
					JSONObject InnerObj = (JSONObject) i.next();
					int boolValid = Integer.valueOf(String.valueOf(InnerObj.get(dtAPIDATA.boolValid)));
					if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataHeader.set_intSync("1");
						_trackingLocationDA.SaveDataTrackingLocation(_db, dataHeader);
					}
				}
			}
		}

		dtlinkAPI = new linkAPI();
		txtMethod = "PushDataKoordinasiOutlet";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtMethod(VersionName);
		strLinkAPI = dtlinkAPI.QueryString(_StrLINKAPI);

		/*KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(_db);
		List<KoordinasiOutletData> ListDataKoordinasiOutlet = _KoordinasiOutletDA.getAllDataToPushData(_db);
		if (ListDataKoordinasiOutlet != null) {
			for (KoordinasiOutletData dataHeader : ListDataKoordinasiOutlet) {
				dataJson Json = new dataJson();
				List<KoordinasiOutletData> tmpListDataKoordinasiOutletData = new ArrayList<KoordinasiOutletData>();
				tmpListDataKoordinasiOutletData.add(dataHeader);

				KoordinasiOutletImageDA _KoordinasiOutletImageDA = new KoordinasiOutletImageDA(_db);
				List<KoordinasiOutletImageData> tmpListKoordinasiOutletImage = _KoordinasiOutletImageDA.getDataHeaderId(_db, dataHeader.get_intId());
				if (tmpListKoordinasiOutletImage != null) {
					Json.setListOfKoordinasiOutletImageData(tmpListKoordinasiOutletImage);
				}
				Json.setListOfKoordinasiOutletData(tmpListDataKoordinasiOutletData);
				String Html = new clsHelper().pushtData(strLinkAPI, Json.txtJSON().toString(), Integer.valueOf(TimeOut));
				org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()){
					APIData dtAPIDATA = new APIData();
					JSONObject InnerObj = (JSONObject) i.next();
					int boolValid = Integer.valueOf(String.valueOf(InnerObj.get(dtAPIDATA.boolValid)));
					if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataHeader.set_intSync("1");
						_KoordinasiOutletDA.SaveDataKoordinasiOutlet(_db, dataHeader);
					}
				}
			}
		}

			dtlinkAPI=new linkAPI();
			txtMethod="PushDataTHeaderSales";
			dtlinkAPI.set_txtMethod(txtMethod);
			dtlinkAPI.set_txtParam("");
			dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
			dtlinkAPI.set_txtVesion(VersionName);
			strLinkAPI= dtlinkAPI.QueryString(_StrLINKAPI);

			tSalesProductHeaderDA _tSalesProductHeaderDA=new tSalesProductHeaderDA(_db);
			List<tSalesProductHeaderData> ListDataTsalesHeader= _tSalesProductHeaderDA.getAllDataToPushData(_db);
			if(ListDataTsalesHeader!=null){
				for (tSalesProductHeaderData dataheader : ListDataTsalesHeader) {
					dataJson Json= new dataJson();
					List<tSalesProductHeaderData> tmplistDatatSalesProductHeaderData=new ArrayList<tSalesProductHeaderData>();
					tmplistDatatSalesProductHeaderData.add(dataheader);
					tAbsenUserData _tmpdataabsen= _tAbsenUserDA.getData(_db, Integer.valueOf(dataheader.get_intIdAbsenUser()));
					List<tAbsenUserData> tmpListDataUserAbsen=new ArrayList<tAbsenUserData>();
					tmpListDataUserAbsen.add(_tmpdataabsen);
					tSalesProductDetailDA _tSalesProductDetailDA=new tSalesProductDetailDA(_db);

				List<tSalesProductDetailData> tmpListSalesProductDetail= _tSalesProductDetailDA.getSalesProductDetailByHeaderId(_db, dataheader.get_intId());
				if(tmpListSalesProductDetail!=null){
					Json.setListOftSalesProductDetailData(tmpListSalesProductDetail);
				}
				Json.setListOftAbsenUserData(tmpListDataUserAbsen);
				Json.setListOftSalesProductHeaderData(tmplistDatatSalesProductHeaderData);
				String Html= new clsHelper().pushtData(strLinkAPI,Json.txtJSON().toString(),Integer.valueOf(TimeOut));
				org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()) {
					APIData dtAPIDATA=new APIData();
					org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
					int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
					if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataheader.set_intSync("1");
						_tSalesProductHeaderDA.SaveDatatSalesProductHeaderData(_db, dataheader);
					}
				}
			}
		}

		dtlinkAPI=new linkAPI();
		txtMethod="SaveDataTActivityMobile";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(VersionName);
		strLinkAPI= dtlinkAPI.QueryString(_StrLINKAPI);

		tActivityDA _tActivityDA=new tActivityDA(_db);
		List<tActivityData> ListDatatActivityData= _tActivityDA.getAllDataToPushData(_db);
		if(ListDatatActivityData!=null){
			for (tActivityData dataActivity : ListDatatActivityData) {
				List<tActivityData> tmpListDatatActivityData=new ArrayList<tActivityData>();
				tmpListDatatActivityData.add(dataActivity);
				dataJson Json= new dataJson();
				Json.setIntResult("1");
				Json.setListOftActivityData(tmpListDatatActivityData);
				//
				String Html= new clsHelper().PushDataWithFile(strLinkAPI,Json.txtJSON().toString(),Integer.valueOf(TimeOut),String.valueOf(dataActivity.get_txtImg1()),String.valueOf(dataActivity.get_txtImg2()));
				org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()) {
					APIData dtAPIDATA=new APIData();
					org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
					int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
					if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dataActivity.set_intIdSyn(String.valueOf(innerObj.get(dtAPIDATA.strArgument)));
						_tActivityDA.SaveDatatActivityData(_db, dataActivity);
					}
				}
			}
		}

		dtlinkAPI=new linkAPI();
		txtMethod="APISaveDatatCustomerBase";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(VersionName);
		strLinkAPI= dtlinkAPI.QueryString(_StrLINKAPI);

		dtlinkAPI=new linkAPI();
		txtMethod="SaveDatatLeaveMobile";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(VersionName);
		strLinkAPI= dtlinkAPI.QueryString(_StrLINKAPI);

		tLeaveMobileDA _tLeaveMobileDA=new tLeaveMobileDA(_db);
		List<tLeaveMobileData> listoftLeaveMobileData= _tLeaveMobileDA.getAllDataPushData(_db);
		if(listoftLeaveMobileData!=null){
			for (tLeaveMobileData dttLeaveMobileData : listoftLeaveMobileData) {
				//_tLeaveMobileDA.deleteContact(_db, dttLeaveMobileData.get_intLeaveIdSync());
				List<tLeaveMobileData> tmpListDatatLeaveMobileData=new ArrayList<tLeaveMobileData>();
				tmpListDatatLeaveMobileData.add(dttLeaveMobileData);
				dataJson Json= new dataJson();
				Json.setIntResult("1");
				Json.setListOftLeaveMobileData(tmpListDatatLeaveMobileData);
				String Html= new clsHelper().pushtData(strLinkAPI,Json.txtJSON().toString(),Integer.valueOf(TimeOut));
				org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(Html);
				Iterator i = JsonArray.iterator();
				while (i.hasNext()) {
					APIData dtAPIDATA=new APIData();
					org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
					int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
					if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
						dttLeaveMobileData.set_intLeaveIdSync(String.valueOf(innerObj.get(dtAPIDATA.strArgument)));
						_tLeaveMobileDA.SaveDataMConfig(_db, dttLeaveMobileData);
					}
				}
			}
		}*/
		_db.close();
	}
}
