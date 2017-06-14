package library.salesforce.dal;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import library.salesforce.common.tUserLoginData;

public class clsHardCode  {
	public String txtPathApp= Environment.getExternalStorageDirectory()+File.separator+"dataAbsen"+File.separator+"dataAbsen"+File.separator+"KalbeAbsen"+File.separator+"app_database"+File.separator;
	//public String txtPathApp= "data"+File.separator+"data"+File.separator+"com.example.kalbespgmobile"+File.separator+"databases"+File.separator;
	//public String txtPathApp= "data"+File.separator+"data"+File.separator+"com.example.kalbespgmobile"+File.separator+"databases"+File.separator;
	public String txtPathUserData= Environment.getExternalStorageDirectory()+File.separator+"data"+File.separator+"data"+File.separator+"Kalbespgmobile"+File.separator+"user_data"+File.separator;
	public String txtFolderNotification=txtPathUserData+"Notification"+File.separator;
	public String txtFolderBrosur=txtPathUserData+"Brosur"+File.separator;
	public String txtFolderAbsen=txtPathUserData+"Absen"+File.separator;
	public String txtFolderActivity=txtPathUserData+"Activity"+File.separator;
	public String txtDatabaseName=txtPathApp+"spgmobile2";
	public String txtVersion="1";
	public String txtTable_mConfig = "mconfig";
	public String txtTable_tNotification = "tNotification";
	public String txtTable_clsFileAttach_mobile = "clsFileAttach_mobile";
	public String txtTable_mEmployeeBranch = "mEmployeeBranch";
	public String txtTable_mEmployeeArea = "mEmployeeArea";
	public String txtTable_mBrosurMobile = "mBrosurMobile";
	public String txtTable_mBrosurDetailMobile = "mBrosurDetailMobile";
	public String txtTable_mCategoryBrosurMobile = "mCategoryBrosurMobile";
	public String txtTable_tEmployeeBRWithLOB = "tEmployeeBRWithLOB";
	public String txtTable_tUserLOB = "tUserLOB";
	public String txtTable_mAbsenCheckin = "mAbsenCheckin";
	public String txtTable_mGeolocationOutletSPG = "mGeolocationOutletSPG";
	public String txtTable_mUserRole = "mUserRole";
	public String txtTable_mDownloadMasterData_mobile = "mDownloadMasterData_mobile";
	public String txtTable_mUserCabang = "mUserCabang";
	public String txtTable_mEmployeeSalesProduct = "mEmployeeSalesProduct";
	public String txtTable_mNotification = "mNotification";
	public String txtTable_mProductBrandHeaderData = "mProductBrandHeaderData";
	public String txtTable_tErrorLog = "tErrorLog";
	public String txtTable_tDisplayPicture = "tDisplayPicture";
	public String txtTable_mTypeLeaveMobile = "mTypeLeaveMobile";
	public String txtTable_tLeaveMobile = "tLeaveMobile";
	public String txtPhotoNotAvailabe = "../styles/css/images/no_image.gif";
	public String txtTable_mMenu = "mMenu";
	public String txtTable_mCountNumber = "mCountNumber";
	public String txtTable_tUserLogin = "tUserLogin";
	public String txtTable_tDeviceInfoUser = "tDeviceInfoUser";
	public String txtTable_tCustomerBase = "tCustomerBase";
	public String txtTable_tCustomerBaseDetail = "tCustomerBaseDetail";
	public String txtTable_tAbsenUser = "tAbsenUser";
	public String txtTable_tActivity = "tActivity";
	public String txtTable_tCheckinOutletMD = "tCheckinOutletMD";
	public String txtTable_tSalesProductDetail = "tSalesProductDetail";
	public String txtTable_tSalesProductHeader = "tSalesProductHeader";
	public String txtTable_tPurchaseOrderDetail = "tPurchaseOrderDetail";
	public String txtTable_tPurchaseOrderHeader = "tPurchaseOrderHeader";
	public String txtTable_mParent = "mParent";
	public String txtTable_mKategori = "mKategori";
	public String txtTable_mPertanyaan = "mPertanyaan";
	public String txtTable_tJawabanUser = "tJawabanUser";
	public String txtTable_mTypePertanyaan = "mTypePertanyaan";
	public String txtTable_mListJawaban = "mListJawaban";
	public String txtTable_tInventorySPGDetail_mobile = "tInventorySPGDetail_mobile";
	public String txtTable_tInventorySPGHeader_mobile = "tInventorySPGHeader_mobile";
	public String txtTable_tSalesOrderHeader_mobile = "tSalesOrderHeader_mobile";
	public String txtTable_tSalesOrderDetail_mobile = "tSalesOrderDetail_mobile";
	public String txtTable_tStockOpnameHeader_mobile = "tStockOpnameHeader_mobile";
	public String txtTable_tStockOpnameDetail_mobile = "tStockOpnameDetail_mobile";
	public String txtTable_tGRNHeader_mobile = "tGRNHeader_mobile";
	public String txtTable_tGRNDetail_mobile = "tGRNDetail_mobile";
	public String txtTable_mItemSalesPack_Stock = "mItemSalesPack_Stock";
	public String txtTable_mItemSalesPack_StockLog = "mItemSalesPack_StockLog";
	public String txtTable_mProductBarcode = "mProductBarcode";
	public String txtTable_tTransactionDetail = "tTransactionDetail";
	public String txtTable_mStatusDocument = "mStatusDocument";
	public String txtTable_mTypePenguaranMobile = "mTypePenguaranMobile";
	public String txtTable_tPenguaranHeader_Mobile = "tPenguaranHeader_Mobile";
	public String txtTable_tPenguaranDetail_Mobile= "tPenguaranDetail_Mobile";
	public String txtTable_tPenguaranStatus_Mobile= "tPenguaranStatus_Mobile";
	public String txtTable_tPOHeader_Mobile = "tPOHeader_Mobile";
	public String txtTable_tPODetail_Mobile= "tPODetail_Mobile";
	public String txtTable_tPOStatus_Mobile= "tPOStatus_Mobile";
	public String txtTable_mStockAwal="mStockAwal_Mobile";
	public String txtTable_mReason="mReason_Mobile";
	public String txtTable_mPriceInOutlet="mPriceInOutlet";

	public String txtStatus_SaveData = "SaveData";
	public String txtStatus_SaveDataCustomerBase = "SaveDataCustomerBase";
	public String txtStatus_SaveDataSales = "SaveDataSales";
	public String txtStatus_SaveDataUserLoginAndMmenu = "SaveDataUserLoginAndMmenu";
	public String txtStatus_UpdateData = "UpdateData";

	public String txtStatus_TypeDataBB = "BB";
	public String txtStatus_TypeDataGRN = "GRN";
	public String txtStatus_TypeDataRESO = "SO";
	public String txtStatus_TypeDataStockOpname = "OP";
	public String txtStatus_TypeDataPengeluaran="PB";
	public String txtStatus_TypeDataReserved = "RSV";

	public String txtTable_tCustomerBasedMobileHeader = "tCustomerBasedMobileHeader";
	public String txtTable_tCustomerBasedMobileDetail = "tCustomerBasedMobileDetail";
	public String txtTable_tCustomerBasedMobileDetailProduct = "tCustomerBasedMobileDetailProduct";

	public String txtStatus_CheckDataActiveCheckIn = "CheckDataActiveCheckIn";
	public String txtStatus_CheckOutAbsen = "CheckOutAbsen";
	public String txtStatus_GetData = "GetData";
	public String txtStatus_GetDataDetail = "GetDataDetail";
	public String txtStatus_UserDataLogout = "UserDataLogout";
	public String txtStatus_GetDatabyNoSo = "GetDatabyNoSo";
	public String txtStatus_GetDataTsalesProductHeaderByOutletCode = "GetDataTsalesProductHeaderByOutletCode";
	public String txtStatus_SyncData = "SyncData";
	public String txtStatus_GetDataSubmit = "GetDataSubmit";
	public String txtStatus_GetDataNew = "GetDataNew";
	public String txtStatus_GetDataNotSync = "GetDataNotSync";
	public String txtStatus_GetDataByAbsenId = "GetDataByAbsenId";
	public String txtStatus_CheckUserLogin = "CheckUserLogin";
	public String txtStatus_EXIT = "EXIT";
	public String txtStatus_GetMenuData = "GetMenuData";
	public String txtStatus_GetInfoDevice = "GetInfoDevice";
	public String txtStatus_DeleteData = "DeleteData";
	public String txtStatus_UpdateDataStatusRead = "UpdateDataStatusRead";
	public String txtStatus_GetDataOutletCode = "GetDataOutletCode";
	public String txtStatus_UpdateDataItemForSubmit = "UpdateDataItemForSubmit";
	public String txtStatus_getAllDataAreaByCabId = "getAllDataAreaByCabId";
	public String txtStatus_getAllDataBranchByCabId = "getAllDataBranchByCabId";
	public String txtStatus_getNoSalesOrder = "getNoSalesOrder";
	public String txtStatus_ReportingSOByOutlet = "ReportingSOByOutlet";
	public String txtStatus_ReportingSummarySOByOutlet = "ReportingSummarySOByOutlet";
	public String txtStatus_RegisterLoginData = "RegisterLoginData";
	public String txtStatus_DestroyData = "DestroyData";
	public String txtStatus_GetDataBrosurWithDetail = "GetDataBrosurWithDetail";

	public String txtMethod_GreetingUser = "GreetingUser";
	public String txtMethod_SearchData = "SearchData";
	public String txtMethod_initialize = "initialize";
	public String txtMethod_GetDateTimerServer = "getDateTimerServer";
	public String txtMethod_getlinkAPI = "getlinkAPI";
	public String txtMethod_getDataOutstanding = "getDataOutstanding";
	public String txtMethod_PushData = "PushData";
	public String txtMethod_GetCountData = "GetCountData";
	public String txtMethod_getlinkAPIAndTxtFooter = "getlinkAPIAndTxtFooter";
	public String txtMethod_getNoSo = "getNoSo";
	public String txtMethod_DownloadData = "DownloadData";
	public String txtMethod_loginUser = "loginUser";
	public String txtMethod_TypeMobile = "TypeMobile";
	public String txtMethod_GetDetailByHeaderId = "GetDetailByHeaderId";
	public String txtMethod_DomainKalbe = "DomainKalbe";
	public String txtMethod_DeleteAllDB = "DeleteAllDB";
	public String txtMethod_ApplicationName = "ApplicationName";
	public String txtMethod_TextFooter = "TextFooter";
	public String txtMethod_WidthScreen = "WidthScreen";
	public String txtMethod_SaveWidthScreen = "SaveWidthScreen";
	public String txtMethod_BackgroundServiceOnline = "BackgroundServiceOnline";
	public String txtMethod_UsernameAdministrator = "UsernameAdministrator";
	public String txtMethod_PasswordAdministrator = "PasswordAdministrator";
	public String txtMethod_MenuDataByParentId = "MenuDataByParentId";
	public String txtMethod_GetMenuPushData = "GetMenuPushData";
	public String txtMethod_MenuDataByParentIdForSPGMOBILE = "MenuDataByParentIdForSPGMOBILE";
	public String txtError_InvalidData = "Invalid Data";
	public String txtSuccess_SaveData = "Success Save Data";
	public String txtSuccess_DeleteData = "Success Delete Data";
	public String txtSuccess_initializeData = "Success initialize Data";

	public String txtMessNetworkOffline = "Please Check Your Network!!";
	public String txtMessGetUserRole = "Getting User Role..";
	public String txtMessGetListPO = "Getting List PO..";
	public String txtMessGetMItemSalesPackStock = "Getting Stock Outlet";
	public String txtMessGetProduct = "Getting Product";
	public String txtMessGetReason = "Getting Reason";
	public String txtMessGetOutlet = "Getting Outlet";
	public String txtMessGetBranch = "Getting Branch";
	public String txtMessGetStockAwal = "Getting Stock Awal";
	public String txtMessGetAllData = "Getting All Data";
	public String txtMessGetDetailPO = "Getting Detail PO..";
	public String txtMessLogOut = "Logout..";
	public String txtMessLogin = "Login..";
	public String txtMessDownloadBranch = "Download Branch";
	public String txtMessDownloadOutlet = "Download Outlet";
	public String txtMessDownloadProduct = "Download Product";
	public String txtMessDownloadTypeLeave = "Download Type Leave";
	public String txtMessDownloadTypePengeluaran = "Download Type Pengeluaran";
	public String txtMessDownloadPriceProduct = "Download Price Product";
	public String txtMessDownloadStatusDocument = "Download Status Document";
	public String txtMessErrorConnection = "Error connecting to Server\r\n please try again!!";
	public String txtMessCancelRequest = "Canceled Request Data";
	public String txtMessDataNotFound = "Data Not Found";
	public String txtMessSuccessDownload = "Success Download";
	public String txtMessUnableToConnect = "Unable To Connect";
	public String txtTokenAPI = "129195202189197196195202189175";
	public String txtTable_mCategoryVisitPlan = "mCategoryVisitPlan";
	public String txtTable_tVisitPlanHeader_Mobile="tVisitPlanHeader_Mobile";
	public String txtTable_tVisitplanRealisasi="tVisitplanRealisasi";
	public String txtMessGetVisitPLan = "Getting Visit Plan";
	public String txtMessGetVisitPlanTr = "Getting Visit Plan Transaction";

	public String intSuccess = "1";
	public String intFailed = "0";

	public String txtQuery_GetTimesStamp = "select strftime('%s','now');";
	public String txtQuery_ConvertDateFromTimeStamp = "SELECT datetime(@code@, 'unixepoch', 'localtime');";
	public String txtStatus_UploadDataimage="UploadDataimage";
	public String txtTable_mTypeSubmissionMobile="mTypeSubmissionMobile";
	public String txtTable_mProductCompetitorData="mProductCompetitorData";
	public String txtTable_tSalesQuantityStock="tSalesProductQuantityHeader";
	public String txtTable_tSalesProductQuantityDetail="tSalesProductQuantityDetail";
	public String txtTable_tSalesProductQuantityImage="tSalesProductQuantityImage";
	public String txtTable_trackingLocation="trackingLocation";
	public String txtTable_KoordinasiOutlet="KoordinasiOutlet";
	public String txtTable_KoordinasiOutletImage="KoordinasiOutletImage";

	public String dtDateTimeNow(){
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String aii=ft.format(date);
		return aii;
	}
	public String dtDateNow(){
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		String aii=ft.format(date);
		return aii;
	}
	public String dtGenerateID(String Style,int sumNumber,SQLiteDatabase db){
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd");
		String aii=ft.format(date);
		tUserLoginData user= new tUserLoginDA(db).CheckDataLogin(db, aii);
		String generatenum=("0000"+String.valueOf((sumNumber+1)));
		return Style+aii+"-"+user.Property_TxtEmpId+"-"+generatenum.substring(generatenum.length()-5, generatenum.length()-1);
	}
}