package library.salesforce.common;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.UUID;

import library.salesforce.dal.clsFileAttach_mobileDA;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumConfigData;
import library.salesforce.dal.mMenuDA;
import library.salesforce.dal.mconfigDA;
import library.salesforce.dal.tAbsenUserDA;
import library.salesforce.dal.tDisplayPictureDA;
import library.salesforce.dal.tUserLoginDA;
import library.salesforce.dal.trackingLocationDA;

//import library.salesforce.dal.KoordinasiOutletDA;
//import library.salesforce.dal.KoordinasiOutletImageDA;
//import library.salesforce.dal.mCategoryVisitPlanDA;
//import library.salesforce.dal.mCounterNumberDA;
//import library.salesforce.dal.mDownloadMasterData_mobileDA;
//import library.salesforce.dal.mEmployeeAreaDA;
//import library.salesforce.dal.mEmployeeBranchDA;
//import library.salesforce.dal.mEmployeeSalesProductDA;
//import library.salesforce.dal.mKategoriDA;
//import library.salesforce.dal.mListJawabanDA;
//import library.salesforce.dal.mNotificationDA;
//import library.salesforce.dal.mParentDA;
//import library.salesforce.dal.mPertanyaanDA;
//import library.salesforce.dal.mPriceInOutletDA;
//import library.salesforce.dal.mProductBarcodeDA;
//import library.salesforce.dal.mProductBrandHeaderDA;
//import library.salesforce.dal.mProductCompetitorDA;
//import library.salesforce.dal.mTypeLeaveMobileDA;
//import library.salesforce.dal.mTypePertanyaanDA;
//import library.salesforce.dal.mTypeSubmissionMobileDA;
//import library.salesforce.dal.mUserRoleDA;
//import library.salesforce.dal.tActivityDA;
//import library.salesforce.dal.tCustomerBasedMobileDetailDA;
//import library.salesforce.dal.tCustomerBasedMobileDetailProductDA;
//import library.salesforce.dal.tCustomerBasedMobileHeaderDA;
//import library.salesforce.dal.tDeviceInfoUserDA;
//import library.salesforce.dal.tDisplayPictureDA;
//import library.salesforce.dal.tJawabanUserDA;
//import library.salesforce.dal.tLeaveMobileDA;
//import library.salesforce.dal.tNotificationDA;
//import library.salesforce.dal.tPurchaseOrderDetailDA;
//import library.salesforce.dal.tPurchaseOrderHeaderDA;
//import library.salesforce.dal.tSalesProductDetailDA;
//import library.salesforce.dal.tSalesProductHeaderDA;
//import library.salesforce.dal.tSalesProductQuantityDetailDA;
//import library.salesforce.dal.tSalesProductQuantityHeaderDA;
//import library.salesforce.dal.tSalesProductQuantityImageDA;
//import library.salesforce.dal.tVisitPlanHeader_MobileDA;
//import library.salesforce.dal.tVisitPlanRealisasiDA;

public class clsHelper {
	public void InitlizeDB(){
		SQLiteDatabase db;
		clsHardCode clsdthc= new clsHardCode();
		db=SQLiteDatabase.openOrCreateDatabase(clsdthc.txtDatabaseName, null);
		mconfigDA _mconfigDA=new mconfigDA(db);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		tDisplayPictureDA _tDisplayPictureDA = new tDisplayPictureDA(db);
		_mconfigDA.DropTable(db);
		_tUserLoginDA.DropTable(db);
		_mconfigDA=new mconfigDA(db);
		_tUserLoginDA=new tUserLoginDA(db);
		_tDisplayPictureDA= new tDisplayPictureDA(db);
		db.close();
	}
	public String GenerateGuid(){
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		return randomUUIDString;
	}
	public String PushDataWithFile(String urlToRead,String DataJson,Integer intTimeOut,HashMap<String,byte[]> ListOfDataFile){
		String charset = "UTF-8";

		String requestURL = urlToRead;
		String Result="";
		clsHelper _clsClsHelper = new clsHelper();

		File folder = new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata");
		folder.mkdir();

		try {
			MultipartUtility multipart = new MultipartUtility(requestURL, charset,intTimeOut);

			//multipart.addHeaderField("User-Agent", "CodeJava");
			//multipart.addHeaderField("DataHeader", DataJson);

			multipart.addFormField("dataField",DataJson);
			//multipart.addFormField("keywords", "Java,upload,Spring");

			for(Entry<String, byte[]> entry : ListOfDataFile.entrySet()) {
				String key = entry.getKey();
//                String value = entry.getValue();

				byte [] array = entry.getValue();
				File file = File.createTempFile("image-", ".jpg", new File(Environment.getExternalStorageDirectory().toString() + "/data/data/Kalbespgmobile/tempdata"));
				FileOutputStream out = new FileOutputStream( file );
				out.write( array );
				out.close();

				multipart.addFilePart(key, new File(file.getAbsolutePath()));
			}
			List<String> response = multipart.finish();
			//System.out.println("SERVER REPLIED:");

			for (String line : response) {
				Result+=line;
				System.out.println(line);
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}

		if (folder.isDirectory())
		{
			String[] children = folder.list();
			for (int i = 0; i < children.length; i++)
			{
				new File(folder, children[i]).delete();
			}
			folder.delete();
		}

		return _clsClsHelper.ResultJsonData(Result);
	}
	public String PushDataWithFile(String urlToRead,String DataJson,Integer intTimeOut,String File1,String File2){
		String charset = "UTF-8";
		File uploadFile1 = null;
		File uploadFile2 = null;
		if(File1.contains("file:")){
			uploadFile1 = new File(File1.substring(7));
		}

		if(File2.contains("file:")){
			uploadFile2 = new File(File2.substring(7));
		}

		String requestURL = urlToRead;
		String Result="";
		clsHelper _clsClsHelper = new clsHelper();
		try {
			MultipartUtility multipart = new MultipartUtility(requestURL, charset,intTimeOut);

			multipart.addHeaderField("User-Agent", "CodeJava");
			multipart.addHeaderField("DataHeader", DataJson);

			multipart.addFormField("dataField",DataJson);
			multipart.addFormField("keywords", "Java,upload,Spring");
			if(uploadFile1 != null){
				if(uploadFile1.exists()){
					multipart.addFilePart("fileUpload1", uploadFile1);
				}
			}
			if(uploadFile2 != null){
				if(uploadFile2.exists()){
					multipart.addFilePart("fileUpload2", uploadFile2);
				}
			}
			List<String> response = multipart.finish();

			System.out.println("SERVER REPLIED:");

			for (String line : response) {
				Result+=line;
				System.out.println(line);
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
		return _clsClsHelper.ResultJsonData(Result);
	}
	public String pushtData(String urlToRead,String DataJson,Integer intTimeOut) {
		//notify("asa","asda","asdas");
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		clsHelper _clsClsHelper = new clsHelper();
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(intTimeOut);
			conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
			conn.setRequestProperty("Accept","*/*");
			String param=DataJson;
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setFixedLengthStreamingMode(param.getBytes().length);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("charset", "utf-8");
			//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.close();
			String response= "";
			Scanner inStream = new Scanner(conn.getInputStream());
			while(inStream.hasNextLine())
			{
				response+=(inStream.nextLine());
			}
			conn.disconnect();
			result=_clsClsHelper.ResultJsonData(response);
		} catch (IOException e) {
			result=e.getMessage();
		} catch (Exception e) {
			result=e.getMessage();
		}
		return result;
	}

	public void DeleteAllDB(SQLiteDatabase db){
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);

		clsFileAttach_mobileDA _clsFileAttach_mobileDA = new clsFileAttach_mobileDA(db);
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		mMenuDA _mMenuDA=new mMenuDA(db);
		tDisplayPictureDA _tDisplayPictureDA = new tDisplayPictureDA(db);
		trackingLocationDA _trackingLocationDA = new trackingLocationDA(db);
		_tDisplayPictureDA = new tDisplayPictureDA(db);

		_clsFileAttach_mobileDA.DropTable(db);
		_tUserLoginDA.DropTable(db);
		_mMenuDA.DropTable(db);
		_tAbsenUserDA.DropTable(db);
		_trackingLocationDA.DropTable(db);

		_tDisplayPictureDA = new tDisplayPictureDA(db);
		_trackingLocationDA = new trackingLocationDA(db);
		_tUserLoginDA=new tUserLoginDA(db);
		_tAbsenUserDA=new tAbsenUserDA(db);
		_mMenuDA=new mMenuDA(db);
		_clsFileAttach_mobileDA = new clsFileAttach_mobileDA(db);
		clsHardCode clsdthc=new clsHardCode();
		clsHelper _clsHelper=new clsHelper();
		File dir = new File(clsdthc.txtPathUserData);
		_clsHelper.DeleteRecursive(dir);
		mconfigDA _mconfigDA = new mconfigDA(db);
		int sumdata = _mconfigDA.getContactsCount(db);
		if (sumdata == 0) {
			_mconfigDA.InsertDefaultMconfig(db);
		}

	}

	public String ResultJsonData(String dt){
		return dt.substring(16,dt.length()-2);
	}
	public JSONArray ResultJsonArray(String dt) throws ParseException{
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(dt);
		JSONArray lang= (JSONArray) obj;
		return lang;
	}
	public String linkAPI(SQLiteDatabase db){
		//ambil linkapi Database sqllite
		mconfigDA _mconfigDA=new mconfigDA(db);
		String strVal2="";
		mconfigData dataAPI = _mconfigDA.getData(db,
				enumConfigData.ApiKalbe.getidConfigData());
		strVal2 = dataAPI.get_txtValue();
		if (dataAPI.get_txtValue() == "") {
			strVal2 = dataAPI.get_txtDefaultValue();
		}
		//ambil version dari webservices
		linkAPI dtlinkAPI=new linkAPI();
		return dtlinkAPI.QueryString(strVal2);
	}
	void DeleteRecursive(File fileOrDirectory) {
		if (fileOrDirectory.isDirectory())
			for (File child : fileOrDirectory.listFiles())
				DeleteRecursive(child);
		fileOrDirectory.delete();
	}
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// CREATE A MATRIX FOR THE MANIPULATION
		Matrix matrix = new Matrix();
		// RESIZE THE BIT MAP
		matrix.postScale(scaleWidth, scaleHeight);

		// "RECREATE" THE NEW BITMAP
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);

		return resizedBitmap;
	}
	public Bitmap downloadFile(String fileUrl){
		Bitmap bmImg=null;
		URL myFileUrl =null;
		try {
			myFileUrl= new URL(fileUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bmImg = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bmImg;
	}
	public String getHTML(String urlToRead) throws Exception {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	public void createFolderUserData(){
		clsHardCode clsdthc = new clsHardCode();

		File appDirUser = new File(clsdthc.txtPathUserData);

		if(!appDirUser.exists() && !appDirUser.isDirectory())
		{
			// create empty directory
			if (appDirUser.mkdirs())
			{
				Log.i("CreateDir","App dir created");
			}
			else
			{
				Log.w("CreateDir","Unable to create app dir!");
			}
		}
		else
		{
			Log.i("CreateDir","App dir already exists");
		}
	}
	public void createFolderApp(){
		clsHardCode clsdthc = new clsHardCode();
		File appDir=new File(clsdthc.txtPathApp);

		if(!appDir.exists() && !appDir.isDirectory())
		{
			// create empty directory
			if (appDir.mkdirs())
			{
				Log.i("CreateDir","App dir created");
			}
			else
			{
				Log.w("CreateDir","Unable to create app dir!");
			}
		}
		else
		{
			Log.i("CreateDir","App dir already exists");
		}
	}
	public String generateNewId(String OldId,String Separator,String Length){
		String itemStyle = OldId;
		String[] split = itemStyle.split(Separator,0);
		String itemID = split[1];
		Long num0x= (long) 0 ;

		if(itemID.contains("0")){
			num0x = Long.valueOf(itemID.substring(itemID.indexOf("0")));
		}else{
			num0x = Long.valueOf(itemID);
		}
		String second = split[0]+Separator+String.format("%0"+Length+"d", num0x + 1);
		return second;
	}
	public static boolean externalMemoryAvailable() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}

	public static String getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return formatSize(availableBlocks * blockSize);
	}

	public static String getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return formatSize(totalBlocks * blockSize);
	}

	public static String getAvailableExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return formatSize(availableBlocks * blockSize);
		} else {
			return "-1";
		}
	}

	public static String getTotalExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return formatSize(totalBlocks * blockSize);
		} else {
			return "-1";
		}
	}

	public static String formatSize(long size) {
		String suffix = null;

		if (size >= 1024) {
			suffix = "KB";
			size /= 1024;
			if (size >= 1024) {
				suffix = "MB";
				size /= 1024;
			}
		}

		StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

		int commaOffset = resultBuffer.length() - 3;
		while (commaOffset > 0) {
			resultBuffer.insert(commaOffset, ',');
			commaOffset -= 3;
		}

		if (suffix != null) resultBuffer.append(suffix);
		return resultBuffer.toString();
	}
}
