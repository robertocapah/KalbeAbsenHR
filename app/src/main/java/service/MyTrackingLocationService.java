package service;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import bl.tUserLoginBL;
import bl.trackingLocationBL;
import kalbeabsen.app.kalbenutritionals.kalbeabsen.clsMainActivity;
import library.salesforce.common.mCounterNumberData;
import library.salesforce.common.mDownloadMasterData_mobileData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.common.trackingLocationData;
import library.salesforce.dal.clsHardCode;
import library.salesforce.dal.enumCounterData;
import library.salesforce.dal.mCounterNumberDA;
import library.salesforce.dal.mDownloadMasterData_mobileDA;
import library.salesforce.dal.tUserLoginDA;

/**
 * Created by Rian Andrivani on 5/18/2017.
 */

public class MyTrackingLocationService extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    List<tUserLoginData> dtLogin;
    List<mDownloadMasterData_mobileData> dtDownload;
    public MyTrackingLocationService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        buildGoogleApiClient();
    }
    private GoogleApiClient mGoogleApiClient;
    private void buildGoogleApiClient() {
        // TODO Auto-generated method stub
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

    }
    private Location mLastLocation;
    Handler mHandler = new Handler();
    private final static int INTERVAL = 1000 * 60; //2 minutes
    private static long UPDATE_INTERVAL = 1*360*1000;;  //default
    private static long UPDATE_INTERVAL_TESTING = /*1000 * 60 * 2*/3000; //2 minutes
    private static Timer timer = new Timer();
    private void startService() throws JSONException {
        try {
            doService();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void doService() throws JSONException {
        SQLiteDatabase db;
        String versionName="";
        try {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        clsHardCode clsdthc = new clsHardCode();
        db = SQLiteDatabase.openOrCreateDatabase(clsdthc.txtDatabaseName,null); // create file database
        tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
        mDownloadMasterData_mobileDA _mDownloadMasterData_mobileDA = new mDownloadMasterData_mobileDA(db);

        if (_mDownloadMasterData_mobileDA.getContactsCount(db)> 0) {
//            tUserLoginData _tUserLoginData=_tUserLoginDA.getData(db, 1);
            mDownloadMasterData_mobileData mDownloadMasterData_mobileData = _mDownloadMasterData_mobileDA.getData(db,1);

            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                mCounterNumberDA _mCounterNumberDA=new mCounterNumberDA(db);
                mCounterNumberData _data =new mCounterNumberData();
                _data.set_intId(enumCounterData.MonitoringLocation.getidCounterData());
                _data.set_txtDeskripsi("value menunjukan waktu terakhir menjalankan services");
                _data.set_txtName("Monitor Service Location");
                _data.set_txtValue(dateFormat.format(cal.getTime()));
                _mCounterNumberDA.SaveDataMConfig(db, _data);

                startRepeatingTask();
                //new clsInit().PushData(db,versionName);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


//            trackingLocation();

        } else {
            shutdownService();
        }
        db.close();
    }

    public Location trackingLocation() {
        try {
            LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

            // getting GPS status
            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            boolean canGetLocation=false;
            Location location = null;

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
                new clsMainActivity().showCustomToast(getApplicationContext(), "no network provider is enabled", false );
            } else {
                canGetLocation = true;
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            1000,
                            0, this);
                    Log.d("Network", "Network Enabled");
                    if (locationManager != null) {
                        mLastLocation = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (mLastLocation == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                1000,
                                0, this);
                        Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                double latitude = location.getLatitude();
                                double longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        trackingLocationData dataLocation = new trackingLocationData();
        tUserLoginData dataUserActive = new tUserLoginBL().getUserActive();
        tUserLoginData _tUserLoginData = new tUserLoginData();
//        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Calendar cal = Calendar.getInstance();

        dataLocation.set_intId(new clsMainActivity().GenerateGuid());
        dataLocation.set_txtLongitude(String.valueOf(mLastLocation.getLongitude()));
        dataLocation.set_txtLatitude(String.valueOf(mLastLocation.getLongitude()));
        dataLocation.set_txtAccuracy(String.valueOf(mLastLocation.getAccuracy()));
        dataLocation.set_txtUserId(dataUserActive.get_txtUserId());
        dataLocation.set_txtUsername(dataUserActive.get_txtUserName());
        dataLocation.set_txtRoleId(dataUserActive.get_txtRoleId());
        dataLocation.set_txtDeviceId(dataUserActive.get_txtDeviceId());
        dataLocation.set_txtBranchCode(dataUserActive.get_txtBranchCode());
        dataLocation.set_txtNIK(dataUserActive.get_TxtEmpId());
        dataLocation.set_intSubmit("1");
        dataLocation.set_intSync("0");

        List<trackingLocationData> dtList = new ArrayList<>();
        dtList.add(dataLocation);
        new trackingLocationBL().SaveDataLocation(dtList);

        return mLastLocation;
    }

    Runnable mHandlerTask = new Runnable()
    {
        @Override
        public void run() {
            trackingLocation();
            mHandler.postDelayed(mHandlerTask, INTERVAL);
        }
    };
    void startRepeatingTask()
    {
        mHandlerTask.run();
    }

    private void shutdownService() {
        if (timer != null) timer.cancel();
        Log.i(getClass().getSimpleName(), "Timer stopped...");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // For time consuming an long tasks you can launch a new thread here...
        //Toast.makeText(this, "Welcome Kalbe SPG Mobile", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    startService();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, 25000);

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        //Toast.makeText(this, " onStartCommand", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    startService();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, 25000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        shutdownService();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
