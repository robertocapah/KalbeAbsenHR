package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.Iterator;

import library.salesforce.common.APIData;
import library.salesforce.common.clsHelper;
import library.salesforce.common.dataJson;
import library.salesforce.common.linkAPI;
import library.salesforce.common.mconfigData;
import library.salesforce.common.tAbsenUserData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.common.trackingLocationData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tAbsenUserDA;
import library.salesforce.dal.tUserLoginDA;
import library.salesforce.dal.trackingLocationDA;

//import org.xml.sax.DTDHandler;
//import com.kalbe.salesforce.TableNotif;
//import com.kalbe.service.MyNotificationService;
//import android.content.Intent;
//import come.example.viewbadger.ShortcutBadger;

public class clsHelperBL extends clsMainBL {
    public void DeleteAllDB() {
        SQLiteDatabase db = getDb();
        new clsHelper().DeleteAllDB(db);
        db.close();
    }

    /*public visitplanAbsenData getDataCheckInActive(){
        SQLiteDatabase db=getDb();
        tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
        tAbsenUserData dataAbsen=new tAbsenUserData();
        dataAbsen=_tAbsenUserDA.getDataCheckInActive(db);
        tVisitPlanRealisasiDA  _tTVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
        tVisitPlanRealisasiData dataVisit = new tVisitPlanRealisasiData();
        dataVisit = _tTVisitPlanRealisasiDA.getDataCheckInActive(db);
        visitplanAbsenData dataReturn = new visitplanAbsenData();
        if (dataAbsen == null && dataVisit.get_txtDataIDRealisasi() != null){
            dataReturn.set_txtId(dataVisit.get_txtDataIDRealisasi().toString());
            dataReturn.set_txtOutletCode(dataVisit.get_txtOutletCode().toString());
            dataReturn.set_txtOutletName(dataVisit.get_txtOutletName().toString());
            dataReturn.set_txtBranchCode(dataVisit.get_txtBranchCode().toString());
            dataReturn.set_txtBranchName(dataVisit.get_txtBranchName().toString());
            dataReturn.set_txtDeviceId(dataVisit.get_deviceId());

            dataReturn.set_dtDateCheckIn(dataVisit.get_dtDate().toString());
            dataReturn.set_dtDateCheckOut(dataVisit.get_dateCheckout().toString());
            dataReturn.set_intSubmit(dataVisit.get_intSubmit());
            dataReturn.set_intSync(dataVisit.get_intPush());
            dataReturn.set_txtAccuracy(dataVisit.get_txtAcc());
            dataReturn.set_txtLatitude(dataVisit.get_txtLat());
            dataReturn.set_txtLongitude(dataVisit.get_txtLong());
            byte[] blob1 = dataVisit.get_dtPhoto1();
//				Bitmap bmp1 = BitmapFactory.decodeByteArray(blob1, 0, blob1.length);
            dataReturn.set_txtImg1(blob1);
            byte[] blob2 = dataVisit.get_dtPhoto2();
            dataReturn.set_txtUserId(dataVisit.get_intUserID());
            dataReturn.set_txtRoleId(dataVisit.get_txtRoleId());
//				Bitmap bmp2 = BitmapFactory.decodeByteArray(blob2, 0, blob2.length);
            dataReturn.set_txtImg2(blob2);


            dataReturn.setType("visitPlan");
        }else if (dataAbsen != null && dataVisit.get_txtDataIDRealisasi() == null){
            dataReturn.set_txtId(dataAbsen.get_intId().toString());
            dataReturn.set_txtOutletCode(dataAbsen.get_txtOutletCode().toString());
            dataReturn.set_txtOutletName(dataAbsen.get_txtOutletName().toString());
            dataReturn.set_txtBranchCode(dataAbsen.get_txtBranchCode().toString());
            dataReturn.set_txtBranchName(dataAbsen.get_txtBranchName().toString());
            dataReturn.set_txtDeviceId(dataAbsen.get_txtDeviceId());

            dataReturn.set_dtDateCheckIn(dataAbsen.get_dtDateCheckIn().toString());
            dataReturn.set_dtDateCheckOut(dataAbsen.get_dtDateCheckOut().toString());
            dataReturn.set_intSubmit(dataAbsen.get_intSubmit());
            dataReturn.set_intSync(dataAbsen.get_intSync());
            dataReturn.set_txtAbsen(dataAbsen.get_txtAbsen());
            dataReturn.set_txtAccuracy(dataAbsen.get_txtAccuracy());
            dataReturn.set_txtLatitude(dataAbsen.get_txtLatitude());
            dataReturn.set_txtLongitude(dataAbsen.get_txtLongitude());
            if (dataAbsen.get_txtImg1() != null){
                byte[] blob1 = dataAbsen.get_txtImg1();
                dataReturn.set_txtImg1(blob1);
            }
//				Bitmap bmp1 = BitmapFactory.decodeByteArray(blob1, 0, blob1.length);
            if (dataAbsen.get_txtImg2() != null){
                byte[] blob2 = dataAbsen.get_txtImg2();
                dataReturn.set_txtImg2(blob2);
            }
            dataReturn.set_txtUserId(dataAbsen.get_txtUserId());
            dataReturn.set_txtRoleId(dataAbsen.get_txtRoleId());
//				Bitmap bmp2 = BitmapFactory.decodeByteArray(blob2, 0, blob2.length);

            dataReturn.setType("absen");
        }else{
            dataReturn = null;
        }
        return dataReturn;
    }*/

    public void DownloadData(String versionName) throws ParseException {
        SQLiteDatabase _db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
        JSONObject resJson = new JSONObject();
        resJson.put("User", _dataUserLogin.get_txtUserId());
        resJson.put("txtBranchCode", "");
        resJson.put("txtOutletCode", "");
        resJson.put("txtDeviceId", _dataUserLogin.get_txtDeviceId());
        mconfigDA _mconfigDA = new mconfigDA(_db);
        String strVal2 = "";
        mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        clsHelper _help = new clsHelper();
        linkAPI dtlinkAPI = new linkAPI();
        String txtMethod = "DownloadAllDataTransaction";
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_TxtEmpId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
        String JsonData = _help.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);

        APIData dtAPIDATA = new APIData();
        //String aa=new clsHelper().linkAPI(db);
        Iterator i = JsonArray.iterator();
        Boolean flag = true;
        String ErrorMess = "";
        clsHelper _clsHelper = new clsHelper();
    }

    /*public org.json.simple.JSONArray callPushDataReturnJson(String versionName, String strJson, HashMap<String, byte[]> ListOfDataFile) throws Exception {
        SQLiteDatabase _db = getDb();
        Boolean flag = true;
        String ErrorMess = "";
        String txtMethod = "PushDataSPGMobile";
        linkAPI dtlinkAPI = new linkAPI();
        clsHelper _help = new clsHelper();
        dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod(txtMethod);
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strVal2 = "";
        mconfigDA _mconfigDA = new mconfigDA(_db);
        mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        dataAPI = _mconfigDA.getData(_db, enumConfigData.BackGroundServiceOnline.getidConfigData());
        String TimeOut = dataAPI.get_txtValue();
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = _help.PushDataWithFile(strLinkAPI, strJson, Integer.valueOf(TimeOut), ListOfDataFile);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        APIData dtAPIDATA = new APIData();
        Iterator i = JsonArray.iterator();
        mCounterNumberDA _mCounterNumberDA = new mCounterNumberDA(_db);
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mCounterNumberData _data = new mCounterNumberData();
                _data.set_intId(enumCounterData.dtPushKBN.getidCounterData());
                _data.set_txtDeskripsi((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtName((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtValue((String) innerObj.get("_pstrArgument"));
                _mCounterNumberDA.SaveDataMConfig(_db, _data);
            } else {
                flag = false;
                ErrorMess = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        _db.close();
        return JsonArray;
    }*/

    public org.json.simple.JSONArray GetDatamversionAppPostData(String versionName) throws Exception {
        SQLiteDatabase _db = getDb();
        String txtMethod = "GetDatamversionAppPostData";
        linkAPI dtlinkAPI = new linkAPI();
        clsHelper _help = new clsHelper();
        dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod(txtMethod);
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        dtlinkAPI.set_txtParam("|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strVal2 = "";
        mconfigDA _mconfigDA = new mconfigDA(_db);
        mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }

        String TimeOut = new clsMainBL().getBackGroundServiceOnline();
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        JSONObject resJson = new JSONObject();
        resJson.put("intVersionApp", "");
        resJson.put("txtTypeApp", new clsMainBL().getTypeMobile());
        resJson.put("txtVersion", "");
        resJson.put("status", "1");
        String JsonData = _help.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(TimeOut));
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        APIData dtAPIDATA = new APIData();
        Iterator i = JsonArray.iterator();
        _db.close();
        return JsonArray;
    }

    /*public void callPushData(String versionName, String strJson, HashMap<String, Byte[]> ListOfDataFile) throws Exception {
        SQLiteDatabase _db = getDb();
        Boolean flag = true;
        String ErrorMess = "";
        String txtMethod = "PushDataKBN";
        linkAPI dtlinkAPI = new linkAPI();
        clsHelper _help = new clsHelper();
        dtlinkAPI = new linkAPI();
        dtlinkAPI.set_txtMethod(txtMethod);
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(_db);
        tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
        dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId() + "|||");
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        dtlinkAPI.set_txtVesion(versionName);
        String strVal2 = "";
        mconfigDA _mconfigDA = new mconfigDA(_db);
        mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue() == "") {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        dataAPI = _mconfigDA.getData(_db, enumConfigData.BackGroundServiceOnline.getidConfigData());
        String TimeOut = dataAPI.get_txtValue();
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);
        String JsonData = null;
//				=_help.PushDataWithFile(strLinkAPI, strJson, Integer.valueOf(TimeOut), ListOfDataFile);
        //String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
        org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);
        APIData dtAPIDATA = new APIData();
        Iterator i = JsonArray.iterator();
        mCounterNumberDA _mCounterNumberDA = new mCounterNumberDA(_db);
        while (i.hasNext()) {
            org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
            int boolValid = Integer.valueOf(String.valueOf(innerObj.get(dtAPIDATA.boolValid)));
            if (boolValid == Integer.valueOf(new clsHardCode().intSuccess)) {
                mCounterNumberData _data = new mCounterNumberData();
                _data.set_intId(enumCounterData.dtPushKBN.getidCounterData());
                _data.set_txtDeskripsi((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtName((String) innerObj.get("_pstrMethodRequest"));
                _data.set_txtValue((String) innerObj.get("_pstrArgument"));
                _mCounterNumberDA.SaveDataMConfig(_db, _data);
            } else {
                flag = false;
                ErrorMess = (String) innerObj.get(dtAPIDATA.strMessage);
                break;
            }
        }
        _db.close();
    }*/

    /*public clsPushData pushData(String versionName) {
        clsPushData dtclsPushData = new clsPushData();
        dataJson dtPush = new dataJson();
        SQLiteDatabase db = getDb();
        tUserLoginDA _tUserLoginDA = new tUserLoginDA(db);
        HashMap<String, byte[]> FileUpload = null;
        if (_tUserLoginDA.getContactsCount(db) > 0) {
            tUserLoginData _tUserLoginData = _tUserLoginDA.getData(db, 1);
            dtPush.set_txtVersionApp(versionName);
            dtPush.set_txtUserId(_tUserLoginData.get_txtUserId());
            dtPush.set_txtSessionLoginId(_tUserLoginData.get_txtDataId());
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                mCounterNumberDA _mCounterNumberDA = new mCounterNumberDA(db);
                mCounterNumberData _data = new mCounterNumberData();
                _data.set_intId(enumCounterData.MonitorSchedule.getidCounterData());
                _data.set_txtDeskripsi("value menunjukan waktu terakhir menjalankan services");
                _data.set_txtName("Monitor Service");
                _data.set_txtValue(dateFormat.format(cal.getTime()));
                _mCounterNumberDA.SaveDataMConfig(db, _data);

                //new clsInit().PushData(db,versionName);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
            tVisitPlanHeader_MobileDA _tVisitPlanHeader_MobileDA = new tVisitPlanHeader_MobileDA(db);
            tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
            tActivityDA _tActivityDA = new tActivityDA(db);
            tLeaveMobileDA _tLeaveMobileDA =new tLeaveMobileDA(db);
            tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(db);
            tSalesProductDetailDA _tSalesProductDetailDA = new tSalesProductDetailDA(db);
            tPurchaseOrderDetailDA _tPurchaseOrderDetailDA = new tPurchaseOrderDetailDA(db);
            tJawabanUserDA _tJawabanUserDA = new tJawabanUserDA(db);
            tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(db);
            tSalesProductQuantityHeaderDA _tSalesProductQuantityDA = new tSalesProductQuantityHeaderDA(db);
            tSalesProductQuantityDetailDA _tSalesProductQuantityDetailDA = new tSalesProductQuantityDetailDA(db);
            tSalesProductQuantityImageDA _tSalesProductQuantityImageDA = new tSalesProductQuantityImageDA(db);
            tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(db);
            tCustomerBasedMobileDetailDA _tCustomerBasedMobileDetailDA = new tCustomerBasedMobileDetailDA(db);
            tCustomerBasedMobileDetailProductDA _tCustomerBasedMobileDetailProductDA = new tCustomerBasedMobileDetailProductDA(db);
            trackingLocationDA _trackingLocationDA = new trackingLocationDA(db);
            KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(db);
            KoordinasiOutletImageDA _KoordinasiOutletImageDA = new KoordinasiOutletImageDA(db);

            List<tVisitPlanHeader_MobileData> ListOftVisitPlanHeader_MobileData = _tVisitPlanHeader_MobileDA.getPushData(db);
            List<tVisitPlanRealisasiData> ListOftVisitPlanRealisasiDataDetail = _tVisitPlanRealisasiDA.getPushData(db);
            List<tCustomerBasedMobileHeaderData> ListOftCustomerBasedMobileHeader = _tCustomerBasedMobileHeaderDA.getPushData(db);
            List<tCustomerBasedMobileDetailData> ListOftCustomerBasedMobileDetail = _tCustomerBasedMobileDetailDA.getPushData(db, ListOftCustomerBasedMobileHeader);
            List<tCustomerBasedMobileDetailProductData> ListOftCustomerBasedMobileDetailProduct = _tCustomerBasedMobileDetailProductDA.getPushData(db, ListOftCustomerBasedMobileDetail);

            List<tSalesProductHeaderData> ListOfSalesProductHeader = _tSalesProductHeaderDA.getAllDataToPushData(db);
            List<tSalesProductDetailData> ListOfSalesProductDetail = _tSalesProductDetailDA.getAllDataToPushData(db, ListOfSalesProductHeader);
            List<tPurchaseOrderHeaderData> ListOfPurchaseOrderHeader = _tPurchaseOrderHeaderDA.getAllDataToPushData(db);
            List<tPurchaseOrderDetailData> ListOfPurchaseOrderDetail = _tPurchaseOrderDetailDA.getAllDataToPushDataPO(db, ListOfPurchaseOrderHeader);
            List<tSalesProductQuantityHeaderData> ListOfSalesProductQuantityHeader = _tSalesProductQuantityDA.getAllDataToPushData(db);
            List<tJawabanUserData> ListOfJawabanUser = _tJawabanUserDA.GetAllData(db);
            List<tSalesProductQuantityDetailData> ListOfSalesProductQuantityDetail = _tSalesProductQuantityDetailDA.getAllDataToPushData(db, ListOfSalesProductQuantityHeader);
            List<tSalesProductQuantityImageData> ListOfSalesProductQuantityImage = _tSalesProductQuantityImageDA.getAllDataToPushData(db, ListOfSalesProductQuantityHeader);
            List<trackingLocationData> ListOfTrackingLocation = _trackingLocationDA.getAllDataToPushData(db);
            List<KoordinasiOutletData> ListOfKoordinasiOutlet = _KoordinasiOutletDA.getAllDataToPushData(db);
            List<KoordinasiOutletImageData> ListOfKoordinasiOutletImage = _KoordinasiOutletImageDA.getAllDataToPushData(db, ListOfKoordinasiOutlet);
            List<tLeaveMobileData> ListOftLeaveData=_tLeaveMobileDA.getAllDataPushData(db);
            List<tAbsenUserData> ListOftAbsenUserData = _tAbsenUserDA.getAllDataToPushData(db);
            List<tActivityData> ListOftActivityData = _tActivityDA.getAllDataToPushData(db);

            FileUpload = new HashMap<String, byte[]>();
            if (ListOftAbsenUserData != null) {
                dtPush.setListOftAbsenUserData(ListOftAbsenUserData);
                for (tAbsenUserData dttAbsenUserData : ListOftAbsenUserData) {
                    if (dttAbsenUserData.get_txtImg1() != null) {
                        FileUpload.put("FUAbsen" + dttAbsenUserData.get_intId() + "-1", dttAbsenUserData.get_txtImg1());
                    }
                    if (dttAbsenUserData.get_txtImg2() != null) {
                        FileUpload.put("FUAbsen" + dttAbsenUserData.get_intId() + "-2", dttAbsenUserData.get_txtImg2());
                    }
                }
            }
            if (ListOftActivityData != null) {
                dtPush.setListOftActivityData(ListOftActivityData);
                for (tActivityData dttActivityData : ListOftActivityData) {
                    if (dttActivityData.get_txtImg1() != null) {
                        FileUpload.put("FUActivity" + dttActivityData.get_intId() + "-1", dttActivityData.get_txtImg1());
                    }
                    if (dttActivityData.get_txtImg2() != null) {
                        FileUpload.put("FUActivity" + dttActivityData.get_intId() + "-2", dttActivityData.get_txtImg2());
                    }
                }
            }
            if (ListOfSalesProductQuantityImage != null){
                dtPush.setListOftSalesProductQuantityImageData(ListOfSalesProductQuantityImage);
                for (tSalesProductQuantityImageData dttQuantityImageData : ListOfSalesProductQuantityImage) {
                    if (dttQuantityImageData.get_txtImage() != null) {
                        if (dttQuantityImageData.get_txtType().equals("After") &&  dttQuantityImageData.get_intPosition().equals("1")) {
                            FileUpload.put("FUQTS" + dttQuantityImageData.get_txtId(), dttQuantityImageData.get_txtImage());
                        }
                        if (dttQuantityImageData.get_txtType().equals("After") &&  dttQuantityImageData.get_intPosition().equals("2")) {
                            FileUpload.put("FUQTS" + dttQuantityImageData.get_txtId(), dttQuantityImageData.get_txtImage());
                        }
                        if (dttQuantityImageData.get_txtType().equals("Before") &&  dttQuantityImageData.get_intPosition().equals("1")) {
                            FileUpload.put("FUQTS" + dttQuantityImageData.get_txtId(), dttQuantityImageData.get_txtImage());
                        }
                        if (dttQuantityImageData.get_txtType().equals("Before") &&  dttQuantityImageData.get_intPosition().equals("2")) {
                            FileUpload.put("FUQTS" + dttQuantityImageData.get_txtId(), dttQuantityImageData.get_txtImage());
                        }
                    }
                }
            }
            if (ListOfKoordinasiOutletImage != null){
                dtPush.setListOfKoordinasiOutletImageData(ListOfKoordinasiOutletImage);
                for (KoordinasiOutletImageData dttKoordinasiOutletImageData : ListOfKoordinasiOutletImage) {
                    if (dttKoordinasiOutletImageData.get_txtImage() != null) {
                        if (dttKoordinasiOutletImageData.get_intPosition().equals("1")) {
                            FileUpload.put("FUKDO" + dttKoordinasiOutletImageData.get_txtId(), dttKoordinasiOutletImageData.get_txtImage());
                        }
                        if (dttKoordinasiOutletImageData.get_intPosition().equals("2")) {
                            FileUpload.put("FUKDO" + dttKoordinasiOutletImageData.get_txtId(), dttKoordinasiOutletImageData.get_txtImage());
                        }
                    }
                }
            }
            if (ListOftVisitPlanRealisasiDataDetail != null){
                for (tVisitPlanRealisasiData dttVisitPlanRealisasiData : ListOftVisitPlanRealisasiDataDetail) {
                    if (dttVisitPlanRealisasiData.get_dtPhoto1() != null) {
                        FileUpload.put("VisitPlan" + dttVisitPlanRealisasiData.get_txtDataIDRealisasi() + "-1", dttVisitPlanRealisasiData.get_dtPhoto1());
                    }
                    if (dttVisitPlanRealisasiData.get_dtPhoto2() != null) {
                        FileUpload.put("VisitPlan" + dttVisitPlanRealisasiData.get_txtDataIDRealisasi() + "-2", dttVisitPlanRealisasiData.get_dtPhoto2());
                    }
                }
                dtPush.setListOftVisitPlanRealisasiData(ListOftVisitPlanRealisasiDataDetail);
            }
            if (ListOftVisitPlanHeader_MobileData != null){
                dtPush.setListOftVisitPlanHeader_MobileData(ListOftVisitPlanHeader_MobileData);
            }
            if(ListOftLeaveData!=null){
                dtPush.setListOftLeaveMobileData(ListOftLeaveData);
            }
//
            if (ListOfSalesProductHeader != null) {
                dtPush.setListOftSalesProductHeaderData(ListOfSalesProductHeader);
            }

            if (ListOfSalesProductDetail != null) {
                dtPush.setListOftSalesProductDetailData(ListOfSalesProductDetail);
            }

            if (ListOfJawabanUser != null){
                dtPush.setListOftJawabanUserData(ListOfJawabanUser);
            }
            if (ListOfPurchaseOrderDetail != null){
                dtPush.setListOftPurchaseOrderDetailData(ListOfPurchaseOrderDetail);
            }
            if (ListOfPurchaseOrderHeader != null){
                dtPush.setListOftPurchaseOrderHeaderData(ListOfPurchaseOrderHeader);
            }

            if (ListOfSalesProductQuantityDetail != null){
                dtPush.setListOftSalesProductQuantityDetailData(ListOfSalesProductQuantityDetail);
            }
//            if (ListOfSalesProductQuantityImage != null){
//                dtPush.setListOftSalesProductQuantityImageData(ListOfSalesProductQuantityImage);
//            }
            if (ListOfSalesProductQuantityHeader != null){
                dtPush.setListOftSalesProductQuantityData(ListOfSalesProductQuantityHeader);
            }

            if (ListOfTrackingLocation != null){
                dtPush.setListOfTrackingLocationData(ListOfTrackingLocation);
            }

            if (ListOfKoordinasiOutlet != null) {
                dtPush.setListOfKoordinasiOutletData(ListOfKoordinasiOutlet);
            }

            if (ListOftCustomerBasedMobileHeader != null) {
                dtPush.set_ListOftCustomerBasedMobileHeaderData(ListOftCustomerBasedMobileHeader);
            }

            if (ListOftCustomerBasedMobileDetail != null) {
                dtPush.setListOftCustomerBasedMobileDetailData(ListOftCustomerBasedMobileDetail);
            }

            if (ListOftCustomerBasedMobileDetailProduct != null) {
                dtPush.setListOftCustomerBasedMobileDetailProductData(ListOftCustomerBasedMobileDetailProduct);
            }*//*
            if (ListOftVisitPlanHeader_MobileData != null){
                dtPush.setListOftVisitPlanHeader_MobileData(ListOftVisitPlanHeader_MobileData);
            }
*//*
        } else {
            dtPush = null;
        }
        db.close();
        dtclsPushData.setDtdataJson(dtPush);
        dtclsPushData.setFileUpload(FileUpload);
        return dtclsPushData;
    }*/

    /*public void deleteDataPush(dataJson dtJson, JSONArray JsonResult) {
        SQLiteDatabase db = getDb();
        if (dtJson.getListOftJawabanUserData() != null) {
            for (tJawabanUserData dt : dtJson.getListOftJawabanUserData()) {
                tJawabanUserDA _tJawabanUserDA = new tJawabanUserDA(db);
                _tJawabanUserDA.DeleteAllDatatJawabanUser(db);
            }
        }
        db.close();
    }*/

    public void saveDataPush(dataJson dtJson, org.json.simple.JSONArray JsonResult) {
        SQLiteDatabase db = getDb();

        if (dtJson.getListOftAbsenUserData() != null) {
            Iterator j = null;
            j = JsonResult.iterator();
            while (j.hasNext()) {
                org.json.simple.JSONObject innerObj_Header = (org.json.simple.JSONObject) j.next();
                org.json.simple.JSONArray JsonArray_Detail = (JSONArray) innerObj_Header.get("ListOftAbsenUser_mobile");
                Iterator jDetail = JsonArray_Detail.iterator();
                while (jDetail.hasNext()) {
                    org.json.simple.JSONObject innerObj_Detail = (org.json.simple.JSONObject) jDetail.next();
                    for (tAbsenUserData dt : dtJson.getListOftAbsenUserData()) {
                        tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
                        if (String.valueOf(innerObj_Detail.get("TxtDataIdFromSource")).equals(dt.get_intId())) {
                            dt.set_intSync("1");
                            dt.set_txtAbsen(String.valueOf(innerObj_Detail.get("TxtDataId")));
                            _tAbsenUserDA.SaveDatatAbsenUserData(db, dt);
                        }
                    }
                }
            }
        }

        /*if (dtJson.getListOftVisitPlanRealisasiData() != null) {
            for (tVisitPlanRealisasiData dt : dtJson.getListOftVisitPlanRealisasiData()) {
                tVisitPlanRealisasiDA _tVisitPlanRealisasiDA = new tVisitPlanRealisasiDA(db);
                dt.set_intPush("1");
                _tVisitPlanRealisasiDA.UpdatePushVisitPlan_MobileData(db, dt);
            }
        }
        if (dtJson.getListOftActivityData() != null) {
            for (tActivityData dt : dtJson.getListOftActivityData()) {
                tActivityDA _tActivityDA = new tActivityDA(db);
                dt.set_intIdSyn("1");
                _tActivityDA.SaveDatatActivityData(db, dt);
            }
        }

        if (dtJson.getListOftLeaveMobileData() != null) {
            for (tLeaveMobileData dt : dtJson.getListOftLeaveMobileData()) {
                tLeaveMobileDA _tLeaveMobileDA = new tLeaveMobileDA(db);
                dt.set_intLeaveIdSync("1");
                _tLeaveMobileDA.SaveDataMConfig(db, dt);
            }
        }

        if (dtJson.getListOftSalesProductHeaderData() != null) {
            for (tSalesProductHeaderData dt : dtJson.getListOftSalesProductHeaderData()) {
                tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(db);
                dt.set_intSync("1");
                _tSalesProductHeaderDA.SaveDatatSalesProductHeaderData(db, dt);
            }
        }

        if (dtJson.getListOftPurchaseOrderHeaderData() != null){
            for (tPurchaseOrderHeaderData dt : dtJson.getListOftPurchaseOrderHeaderData()){
                tPurchaseOrderHeaderDA _tPurchaseOrderHeaderDA = new tPurchaseOrderHeaderDA(db);
                dt.set_intSync("1");
                _tPurchaseOrderHeaderDA.SaveDatatPurchaseOrderHeaderData(db,dt);
            }
        }

        if (dtJson.getListOftSalesProductQuantityHeaderData() != null){
            for (tSalesProductQuantityHeaderData dt : dtJson.getListOftSalesProductQuantityHeaderData()){
                tSalesProductQuantityHeaderDA _tSalesProductQuantityDA = new tSalesProductQuantityHeaderDA(db);
                dt.set_intSync("1");
                _tSalesProductQuantityDA.SaveDataSalesProductQuantityData(db, dt);
            }
        }*/

        if (dtJson.getListOfTrackingLocationData() != null){
            for (trackingLocationData dt : dtJson.getListOfTrackingLocationData()){
                trackingLocationDA _trackingLocationDA = new trackingLocationDA(db);
                dt.set_intSync("1");
                _trackingLocationDA.SaveDataTrackingLocation(db, dt);
            }
        }

        /*if (dtJson.getListOfKoordinasiOutletData() != null){
            for (KoordinasiOutletData dt : dtJson.getListOfKoordinasiOutletData()){
                KoordinasiOutletDA _KoordinasiOutletDA = new KoordinasiOutletDA(db);
                dt.set_intSync("1");
                _KoordinasiOutletDA.SaveDataKoordinasiOutlet(db, dt);
            }
        }

        if (dtJson.get_ListOftCustomerBasedMobileHeaderData() != null) {
            for (tCustomerBasedMobileHeaderData dt : dtJson.get_ListOftCustomerBasedMobileHeaderData()) {
                tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(db);
                dt.set_intSync("1");
                _tCustomerBasedMobileHeaderDA.SaveDatatCustomerBasedMobileHeaderData(db, dt);
            }
        }*/

        /*Iterator j2 = null;
        j2 = JsonResult.iterator();
        while (j2.hasNext()) {
            org.json.simple.JSONObject innerObj_Header = (org.json.simple.JSONObject) j2.next();
            org.json.simple.JSONArray JsonArray_Detail = (JSONArray) innerObj_Header.get("ListOfTNotificationHeaderSPG_mobile");
            if (JsonArray_Detail != null) {
                tNotificationBL _tNotificationBL = new tNotificationBL();
                List<tNotificationData> listDatatNotificationData = new ArrayList<tNotificationData>();
                Iterator jDetail = JsonArray_Detail.iterator();
                int index = _tNotificationBL.getContactsCount() + 1;
                while (jDetail.hasNext()) {
                    org.json.simple.JSONObject innerObj_Detail = (org.json.simple.JSONObject) jDetail.next();
                    tNotificationData dttNotificationData = new tNotificationData();
                    dttNotificationData.set_bitActive(String.valueOf(innerObj_Detail.get("_intActive")));
                    dttNotificationData.set_dtPublishEnd(String.valueOf(innerObj_Detail.get("_dtEnd")));
                    dttNotificationData.set_guiID(String.valueOf(innerObj_Detail.get("_txtIdHeaderNotif")));
                    dttNotificationData.set_intIndex(String.valueOf(index));
//                    dttNotificationData.set_intPriority(String.valueOf(innerObj_Detail.get("IntPriority")));
                    dttNotificationData.set_intSubmit("1");
                    dttNotificationData.set_intSync("1");
                    dttNotificationData.set_tPublishStart(String.valueOf(innerObj_Detail.get("_dtStart")));
                    dttNotificationData.set_txtBranchCode("");
                    dttNotificationData.set_txtDescription(String.valueOf(innerObj_Detail.get("_txtDesc")));
                    dttNotificationData.set_txtImage("");
                    dttNotificationData.set_txtLink(String.valueOf(innerObj_Detail.get("_txtLinkFileAttach")));
                    dttNotificationData.set_txtOutlet("");
                    dttNotificationData.set_txtOutletName("");
                    dttNotificationData.set_txtStatus("2");
                    dttNotificationData.set_txtTitle(String.valueOf(innerObj_Detail.get("_txtTitle")));
                    listDatatNotificationData.add(dttNotificationData);
                    index += 1;
                }
                _tNotificationBL.saveData(listDatatNotificationData);
            }
        }*/

//        Iterator j3 = null;
//        j3 = JsonResult.iterator();
//        while (j3.hasNext()) {
//            org.json.simple.JSONObject innerObj_Header = (org.json.simple.JSONObject) j3.next();
//            org.json.simple.JSONArray JsonArray_Detail = (JSONArray) innerObj_Header.get("ListOfTNotificationHeaderSPG_mobile");
//            if (JsonArray_Detail != null) {
//                tNotificationBL _tNotificationBL = new tNotificationBL();
//                List<tNotificationData> listDatatNotificationData = new ArrayList<tNotificationData>();
//                Iterator jDetail = JsonArray_Detail.iterator();
//                int index = _tNotificationBL.getContactsCount() + 1;
//                while (jDetail.hasNext()) {
//                    org.json.simple.JSONObject innerObj_Detail = (org.json.simple.JSONObject) jDetail.next();
//                    tNotificationData dttNotificationData = new tNotificationData();
//                    dttNotificationData.set_bitActive(String.valueOf(innerObj_Detail.get("_intActive")));
//                    dttNotificationData.set_dtPublishEnd(String.valueOf(innerObj_Detail.get("_dtEnd")));
//                    dttNotificationData.set_guiID(String.valueOf(innerObj_Detail.get("_txtIdHeaderNotif")));
//                    dttNotificationData.set_intIndex(String.valueOf(index));
////                    dttNotificationData.set_intPriority(String.valueOf(innerObj_Detail.get("IntPriority")));
//                    dttNotificationData.set_intSubmit("1");
//                    dttNotificationData.set_intSync("1");
//                    dttNotificationData.set_tPublishStart(String.valueOf(innerObj_Detail.get("_dtStart")));
//                    dttNotificationData.set_txtBranchCode("");
//                    dttNotificationData.set_txtDescription(String.valueOf(innerObj_Detail.get("_txtDesc")));
//                    dttNotificationData.set_txtImage("");
//                    dttNotificationData.set_txtLink(String.valueOf(innerObj_Detail.get("_txtLinkFileAttach")));
//                    dttNotificationData.set_txtOutlet("");
//                    dttNotificationData.set_txtOutletName("");
//                    dttNotificationData.set_txtStatus("2");
//                    dttNotificationData.set_txtTitle(String.valueOf(innerObj_Detail.get("_txtTitle")));
//                    listDatatNotificationData.add(dttNotificationData);
//                    index += 1;
//                }
//                _tNotificationBL.saveData(listDatatNotificationData);
//            }
//        }

        db.close();
    }
}
