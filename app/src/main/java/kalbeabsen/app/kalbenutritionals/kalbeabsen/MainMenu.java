package kalbeabsen.app.kalbenutritionals.kalbeabsen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bl.clsHelperBL;
import bl.tAbsenUserBL;
import bl.tDisplayPictureBL;
import bl.tNotificationBL;
import bl.tUserLoginBL;
import come.example.viewbadger.ShortcutBadger;
import de.hdodenhof.circleimageview.CircleImageView;
import library.salesforce.common.tAbsenUserData;
import library.salesforce.common.tNotificationData;
import library.salesforce.common.tUserLoginData;
import library.salesforce.dal.clsHardCode;
import service.MyTrackingLocationService;

//import bl.tNotificationBL;
//import bl.tVisitPlanRealisasiBL;
//import come.example.viewbadger.ShortcutBadger;
//import de.hdodenhof.circleimageview.CircleImageView;
//import library.salesforce.common.tNotificationData;
//import library.salesforce.common.tVisitPlanRealisasiData;
//import library.salesforce.common.visitplanAbsenData;
//import service.MyServiceNative;
//import service.MyTrackingLocationService;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private tAbsenUserBL _tAbsenUserBL;
    private tAbsenUserData dttAbsenUserData;

    private TextView tvUsername, tvEmail;
    private CircleImageView ivProfile;
    private library.salesforce.common.tDisplayPictureData tDisplayPictureData;

    PackageInfo pInfo = null;

    int selectedId;
    private static int menuId = 0;
    Boolean isSubMenu = false;

    clsMainActivity _clsMainActivity = new clsMainActivity();

    String[] listMenu;
    String[] linkMenu;

    private GoogleApiClient client;

    String i_view = null;
    Intent intent;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit");
        builder.setMessage("Do you want to exit?");

        builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedId = 0;

        /*Intent serviceIntentMyServiceNative = new Intent(getApplicationContext(), MyServiceNative.class);
        getApplicationContext().startService(serviceIntentMyServiceNative);*/

        Intent serviceIntentMyTrackingLocationService = new Intent(getApplicationContext(), MyTrackingLocationService.class);
        getApplicationContext().startService(serviceIntentMyTrackingLocationService);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color_theme));
        }

        try {
            pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        FragmentInformation homeFragment = new FragmentInformation();
        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
        fragmentTransactionHome.replace(R.id.frame, homeFragment);
        fragmentTransactionHome.commit();
        selectedId = 99;

        tUserLoginData dt = new tUserLoginBL().getUserActive();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View vwHeader = navigationView.getHeaderView(0);

        ivProfile = (CircleImageView) vwHeader.findViewById(R.id.profile_image);
        tvUsername = (TextView) vwHeader.findViewById(R.id.username);
        tvEmail = (TextView) vwHeader.findViewById(R.id.email);
        tvUsername.setText(_clsMainActivity.greetings() + dt.get_txtName());
        tvEmail.setText(dt.get_TxtEmail());

        tDisplayPictureData = new tDisplayPictureBL().getData();

        if (tDisplayPictureData.get_image() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(tDisplayPictureData.get_image(), 0, tDisplayPictureData.get_image().length);
            ivProfile.setImageBitmap(bitmap);
        } else {
            ivProfile.setImageBitmap(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.drawable.profile));
        }

        ivProfile.setOnClickListener(this);

       /* dtAbsensVisitplan = new clsHelperBL().getDataCheckInActive();
        Menu header = navigationView.getMenu();

        dtRealisasi = new tVisitPlanRealisasiBL().getDataCheckinActive();*/

        Intent intent = getIntent();
        String i_view = intent.getStringExtra("key_view");

        int statusAbsen = 0;
        int menuActive = 0;

        /*if (dtRealisasi.get_txtDataIDRealisasi() == null){
            menuActive = R.id.groupListMenu;
            header.removeItem(R.id.checkoutVisitplan);
        }

        if (dtAbsensVisitplan!=null && dtAbsensVisitplan.getType().toString().equals("visitPlan")) {
            header.removeItem(R.id.logout);
            header.removeItem(R.id.checkout);
            mMenuData data = new mMenuBL().getMenuDataByMenuName("mnVisitPlanMobile");
            menuId = Integer.parseInt(data.get_IntMenuID());
            statusAbsen = menuId;
            menuActive = R.id.groupListMenu1;

            List<mMenuData> menu = new mMenuBL().getDatabyParentId(statusAbsen);
            listMenu = new String[menu.size()];

            for (int i = 0; i < menu.size(); i++) {
                listMenu[i] = menu.get(i).get_TxtMenuName();
            }

            if (i_view != null){

                intent_activity();
            }

        }else if(dtAbsensVisitplan!=null && dtAbsensVisitplan.getType().toString().equals("absen")) {

            header.removeItem(R.id.logout);
            header.removeItem(R.id.checkoutVisitplan);
            mMenuData data = new mMenuBL().getMenuDataByMenuName("mnAbsenSPG");
            menuId = Integer.parseInt(data.get_IntMenuID());
            statusAbsen = menuId;
            menuActive = R.id.groupListMenu1;

            List<mMenuData> menu = new mMenuBL().getDatabyParentId(statusAbsen);
            listMenu = new String[menu.size()];

            for (int i = 0; i < menu.size(); i++) {
                listMenu[i] = menu.get(i).get_TxtMenuName();
            }

            if (i_view != null){
                intent_activity();
            }
        } else {
            menuActive = R.id.groupListMenu;
            header.removeItem(R.id.checkout);
        }*/

        /*List<mMenuData> menu;

        if (dtAbsensVisitplan == null) {
            menu = new mMenuBL().getDatabyParentId(0);
        } else {
            menu = new mMenuBL().getDatabyParentId(statusAbsen);
        }

        linkMenu = new String[menu.size()];
        listMenu = new String[menu.size()];

        if (menu != null) {
            for (int i = 0; i < menu.size(); i++) {

                int resId = getResources().getIdentifier(String.valueOf(menu.get(i).get_TxtDescription().toLowerCase()), "drawable", MainMenu.this.getPackageName());
                Drawable icon = MainMenu.this.getResources().getDrawable(resId);

                header.add(menuActive, i, 1, menu.get(i).get_TxtMenuName()).setIcon(icon).setCheckable(true);

                linkMenu[i] = menu.get(i).get_TxtLink();
                listMenu[i] = menu.get(i).get_TxtMenuName();
            }
        }

//        TextView view = (TextView) navigationView.getMenu().findItem(R.id.home).getActionView();
//        view.setText("99");
        if (i_view!=null){
            if (i_view.equals("View Reso")){
                navigationView.getMenu().findItem(0).setChecked(true);
            } else if (i_view.equals("View Actvity")){
                navigationView.getMenu().findItem(1).setChecked(true);
            } else if (i_view.equals("View Customer Base")){
//                navigationView.getMenu().findItem(2).setChecked(true);
            } else if (i_view.equals("Notifcation")){
//                    navigationView.getMenu().findItem(R.id.information).setChecked(true);
            } else if (i_view.equals("View PO")){
                navigationView.getMenu().findItem(R.id.PO).setChecked(true);
            } else if (i_view.equals("View Quantity")){
                navigationView.getMenu().findItem(R.id.quantityStock).setChecked(true);
            }
        }*/


//        TextView view = (TextView) navigationView.getMenu().findItem(R.id.information).getActionView();
//        List<tNotificationData> ListData=new tNotificationBL().getAllDataWillAlert("2");
//        view.setText("1");

       /* SubMenu subMenuVersion = header.addSubMenu(R.id.groupVersion, 0, 3, "Version");
        try {
            subMenuVersion.add(getPackageManager().getPackageInfo(getPackageName(), 0).versionName + " \u00a9 KN-IT").setIcon(R.drawable.ic_android).setEnabled(false);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }*/

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                Fragment fragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.logout:
                        //funcLogOut();
                        LayoutInflater layoutInflater = LayoutInflater.from(MainMenu.this);
                        final View promptView = layoutInflater.inflate(R.layout.confirm_data, null);

                        final TextView _tvConfirm = (TextView) promptView.findViewById(R.id.tvTitle);
                        final TextView _tvDesc = (TextView) promptView.findViewById(R.id.tvDesc);
                        _tvDesc.setVisibility(View.INVISIBLE);
                        _tvConfirm.setText("Log Out Application ?");
                        _tvConfirm.setTextSize(18);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainMenu.this);
                        alertDialogBuilder.setView(promptView);
                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
//                                        stopService(new Intent(getApplicationContext(), MyServiceNative.class));
                                        stopService(new Intent(getApplicationContext(), MyTrackingLocationService.class));
                                        AsyncCallLogOut task = new AsyncCallLogOut();
                                        task.execute();
                                        new clsHelperBL().DeleteAllDB();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        final AlertDialog alertD = alertDialogBuilder.create();
                        alertD.show();
                        return true;

                    /*case R.id.reporting:
                        toolbar.setTitle("Reporting");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

                        FragmentReporting reportingFragment = new FragmentReporting();
                        FragmentTransaction fragmentTransactionReport = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionReport.replace(R.id.frame, reportingFragment);
                        fragmentTransactionReport.commit();
                        selectedId = 100;

                        return true;*/

                    case R.id.home:
                        toolbar.setTitle("Home");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentInformation homeFragment = new FragmentInformation();
                        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionHome.replace(R.id.frame, homeFragment);
                        fragmentTransactionHome.commit();
                        selectedId = 99;

                        return true;

                    /*case R.id.quantityStock:
                        toolbar.setTitle("View Quantity Stock");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentViewQuantityStock quantityStock = new FragmentViewQuantityStock();
                        FragmentTransaction fragmentTransactionQuantityStock = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionQuantityStock.replace(R.id.frame, quantityStock);
                        fragmentTransactionQuantityStock.commit();
                        selectedId = 99;

                        return true;

                    case R.id.koordinasiOutlet:
                        toolbar.setTitle("Koordinasi Outlet");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentAddKoordinasi koordinasi = new FragmentAddKoordinasi();
                        FragmentTransaction FragmentTransactionKoordinasi = getSupportFragmentManager().beginTransaction();
                        FragmentTransactionKoordinasi.replace(R.id.frame, koordinasi);
                        FragmentTransactionKoordinasi.commit();
                        selectedId = 99;

                        return true;


                    case R.id.historyAbsen:
                        toolbar.setTitle("History Absen");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentViewHistoryAbsen fragmentViewHistoryAbsen = new FragmentViewHistoryAbsen();
                        FragmentTransaction fragmentTransactionHistoryAbsen = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionHistoryAbsen.replace(R.id.frame, fragmentViewHistoryAbsen);
                        fragmentTransactionHistoryAbsen.commit();
                        selectedId = 99;

                        return true;

                    case R.id.PO:
                        toolbar.setTitle("Purchase Order");
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        FragmentPO fragmentPO = new FragmentPO();
                        FragmentTransaction fragmentTransactionPO = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionPO.replace(R.id.frame, fragmentPO);
                        fragmentTransactionPO.commit();
                        selectedId = 99;
                        return true;

                    case R.id.soalkuesioner:
                        toolbar.setTitle("Kuesioner / Quiz");
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        FragmentKuesionerAwal fragmentKuesioner = new FragmentKuesionerAwal();
                        FragmentTransaction fragmentTransactionKuesioner = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionKuesioner.replace(R.id.frame, fragmentKuesioner);
                        fragmentTransactionKuesioner.commit();
                        return true;*/

//                    case R.id.soalkuesioner:
//                        toolbar.setTitle("Kuesioner / Quiz");
//                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                        FragmentKuesioner fragmentKuesioner = new FragmentKuesioner();
//                        FragmentTransaction fragmentTransactionKuesioner = getSupportFragmentManager().beginTransaction();
//                        fragmentTransactionKuesioner.replace(R.id.frame, fragmentKuesioner);
//                        fragmentTransactionKuesioner.commit();
//                        return true;


                  /*  case R.id.viewVisitPlan:
                        toolbar.setTitle("Visit Plan");
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        FragmentViewVisitplan fragmentVP = new FragmentViewVisitplan();
                        FragmentTransaction fragmentTransactionVP = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionVP.replace(R.id.frame, fragmentVP);
                        fragmentTransactionVP.commit();
                        selectedId = 99;
                        return true;*/
                    /*case R.id.information:
                        toolbar.setTitle("Information");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentNotification fragmentNotification = new FragmentNotification();
                        FragmentTransaction fragmentTransactionNotifcation = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionNotifcation.replace(R.id.frame, fragmentNotification);
                        fragmentTransactionNotifcation.commit();
                        selectedId = 99;

                        return  true;*/
                   /* case R.id.checkoutVisitplan:
                        LayoutInflater _layoutInflater2 = LayoutInflater.from(MainMenu.this);
                        final View _promptView2 = _layoutInflater2.inflate(R.layout.confirm_data, null);

                        final TextView tvConfirm2 = (TextView) _promptView2.findViewById(R.id.tvTitle);
                        final TextView tvDesc2 = (TextView) _promptView2.findViewById(R.id.tvDesc);
                        tvDesc2.setVisibility(View.INVISIBLE);
                        tvConfirm2.setText("Check Out Data ?");
                        tvConfirm2.setTextSize(18);

                        AlertDialog.Builder _alertDialogBuilder2 = new AlertDialog.Builder(MainMenu.this);
                        _alertDialogBuilder2.setView(_promptView2);
                        _alertDialogBuilder2
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        tVisitPlanRealisasiData data = new tVisitPlanRealisasiData();
                                        data = dtRealisasi;
                                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Calendar cal = Calendar.getInstance();
                                        data.set_dateCheckout(dateFormat.format(cal.getTime()));
                                        data.set_intPush("0");
                                        new tVisitPlanRealisasiBL().UpdateData(data);
                                        finish();
                                        Intent nextScreen = new Intent(getApplicationContext(), MainMenu.class);
                                        nextScreen.putExtra("keyMainMenu", "main_menu");
                                        finish();
                                        startActivity(nextScreen);
                                        *//* _tAbsenUserBL = new tAbsenUserBL();
                                        dttAbsenUserData = new tAbsenUserData();

                                        dttAbsenUserData = _tAbsenUserBL.getDataCheckInActive();
                                        tAbsenUserData datatAbsenUserData = dttAbsenUserData;
                                        List<tAbsenUserData> absenUserDatas = new ArrayList<tAbsenUserData>();

                                        if (dttAbsenUserData != null) {
                                            datatAbsenUserData.set_intId(dttAbsenUserData.get_intId());
                                            datatAbsenUserData.set_dtDateCheckOut(_clsMainActivity.FormatDateDB().toString());
                                            datatAbsenUserData.set_intSubmit("1");
                                            datatAbsenUserData.set_intSync("0");
                                            datatAbsenUserData.set_txtAbsen("0");
                                            absenUserDatas.add(datatAbsenUserData);
                                            new tAbsenUserBL().saveData(absenUserDatas);

                                            finish();
                                            Intent nextScreen = new Intent(getApplicationContext(), MainMenu.class);
                                            nextScreen.putExtra("keyMainMenu", "main_menu");
                                            finish();
                                            startActivity(nextScreen);
                                        }*//*
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        final AlertDialog _alertD2 = _alertDialogBuilder2.create();
                        _alertD2.show();

                        return true;*/

                    /*case R.id.checkout:
                        LayoutInflater _layoutInflater = LayoutInflater.from(MainMenu.this);
                        final View _promptView = _layoutInflater.inflate(R.layout.confirm_data, null);

                        final TextView tvConfirm = (TextView) _promptView.findViewById(R.id.tvTitle);
                        final TextView tvDesc = (TextView) _promptView.findViewById(R.id.tvDesc);
                        tvDesc.setVisibility(View.INVISIBLE);
                        tvConfirm.setText("Check Out Data ?");
                        tvConfirm.setTextSize(18);

                        AlertDialog.Builder _alertDialogBuilder = new AlertDialog.Builder(MainMenu.this);
                        _alertDialogBuilder.setView(_promptView);
                        _alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        _tAbsenUserBL = new tAbsenUserBL();
                                        dttAbsenUserData = new tAbsenUserData();

                                        dttAbsenUserData = _tAbsenUserBL.getDataCheckInActive();
                                        tAbsenUserData datatAbsenUserData = dttAbsenUserData;
                                        List<tAbsenUserData> absenUserDatas = new ArrayList<tAbsenUserData>();

                                        if (dttAbsenUserData != null) {
                                            datatAbsenUserData.set_intId(dttAbsenUserData.get_intId());
                                            datatAbsenUserData.set_dtDateCheckOut(_clsMainActivity.FormatDateDB().toString());
                                            datatAbsenUserData.set_intSubmit("1");
                                            datatAbsenUserData.set_intSync("0");
                                            datatAbsenUserData.set_txtAbsen("0");
                                            absenUserDatas.add(datatAbsenUserData);
                                            new tAbsenUserBL().saveData(absenUserDatas);

                                            finish();
                                            Intent nextScreen = new Intent(getApplicationContext(), MainMenu.class);
                                            nextScreen.putExtra("keyMainMenu", "main_menu");
                                            finish();
                                            startActivity(nextScreen);
                                        }
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        final AlertDialog _alertD = _alertDialogBuilder.create();
                        _alertD.show();

                        return true;*/
                    default:
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                        try {
                            Class<?> fragmentClass = Class.forName(linkMenu[menuItem.getItemId()]);
                            try {
                                /*if (dtAbsensVisitplan != null) {
                                    toolbar.setTitle("View " + menuItem.getTitle().toString());
                                } else {
                                    toolbar.setTitle(menuItem.getTitle().toString());
                                }*/

                                fragment = (Fragment) fragmentClass.newInstance();
                                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.frame, fragment);
                                fragmentTransaction.addToBackStack(fragment.getClass().getName());
                                fragmentTransaction.commit();
                                selectedId = menuItem.getItemId();
                                isSubMenu = false;

                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        return true;

                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        toolbar.setTitle(item.getTitle());

        Class<?> fragmentClass;
        try {
            Fragment fragment;

            fragmentClass = Class.forName("com.kalbenutritionals.app.kalbespgmobile.Fragment" + String.valueOf(item.getTitle()).replaceAll("\\s+", ""));
            fragment = (Fragment) fragmentClass.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();

            selectedId=id;

            if (!isSubMenu) isSubMenu = true;
            else isSubMenu = false;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        menu.clear();
//        if (listMenu.length <= selectedId) {
//            if (toolbar.getTitle().equals("Reporting")&&!isSubMenu||isSubMenu && dtAbsensVisitplan != null&&listMenu.length>0&&toolbar.getTitle()=="Reporting"){
//                for(String s : listMenu){
//                    if(s.contains("Reso SPG")) {
//                        int a = listMenu.length;
//                        for (int i=0; i<a; i++ ){
//                            if (i==1){
//                                menu.removeItem(i);
//                            } else {
//                                menu.add(0, i, 0, "Add " + listMenu[i]);
//                            }
//                        }
//                    } else {
//                        menu.add(4, 0, 0, "-");
//                        menu.setGroupEnabled(4, false);
//                        break;
//                    }
//                    break;
//                }
//            } else {
//                menu.add(4, 0, 0, "-");
//                menu.setGroupEnabled(4, false);
//            }
//        } else if (!isSubMenu && dtAbsensVisitplan != null) {
//            menu.add(0, selectedId, 0, "Add " + listMenu[selectedId]);
//        } else if (isSubMenu && dtAbsensVisitplan != null) {
//            menu.add(1, selectedId, 0, "View " + listMenu[selectedId]);
//        }
//        else {
//            menu.add(4, 0, 0, "-");
//            menu.setGroupEnabled(4, false);
//        }
//
//        return super.onPrepareOptionsMenu(menu);
//
//    }


    int intProcesscancel = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_image:
                pickImage2();
                break;
        }
    }

    public void pickImage2()
    {
        CropImage.startPickImageActivity(this);
    }

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);

            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                Intent intent = new Intent(this, CropDisplayPicture.class);
                String strName = imageUri.toString();
                intent.putExtra("uriPicture", strName);
                startActivity(intent);
                finish();
            }
        } else if (requestCode==100 || requestCode == 130){
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }

    }

    private class AsyncCallLogOut extends AsyncTask<JSONArray, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONArray... params) {
            JSONArray Json = null;

            try {
                List<tAbsenUserData> listAbsenData = new ArrayList<tAbsenUserData>();
                tAbsenUserData dtTabsenData = new tAbsenUserBL().getDataCheckInActive();
                if (dtTabsenData != null) {
                    dtTabsenData.set_dtDateCheckOut(_clsMainActivity.FormatDateComplete().toString());
                    dtTabsenData.set_intSubmit("1");
                    dtTabsenData.set_intSync("0");
                    listAbsenData.add(dtTabsenData);
                    new tAbsenUserBL().saveData(listAbsenData);
                }
                String versionName = "";
                try {
                    versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
//                clsPushData dtJson = new clsHelperBL().pushData(versionName);
                /*if (dtJson != null) {
                    try {
                        JSONArray Jresult = null;
                        if (dtJson.getDtdataJson().getListOftAbsenUserData() != null) {
                            List<tAbsenUserData> listAbsen = dtJson.getDtdataJson().getListOftAbsenUserData();
                            if (listAbsen.get(0).get_txtAbsen().equals("0")) {
                                Jresult = new clsHelperBL().callPushDataReturnJson(versionName, dtJson.getDtdataJson().txtJSON().toString(), dtJson.getFileUpload());
                            } else {
                                Jresult = new clsHelperBL().callPushDataReturnJson(versionName, dtJson.getDtdataJson().txtJSON().toString(), null);
                            }
//                            new clsHelperBL().saveDataPush(dtJson.getDtdataJson(), Jresult);
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }*/

                try {
                    pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                Json = new tUserLoginBL().Logout(pInfo.versionName);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
            return Json;
        }

        private ProgressDialog Dialog = new ProgressDialog(MainMenu.this);

        @Override
        protected void onCancelled() {
            Dialog.dismiss();
            Toast toast = Toast.makeText(MainMenu.this, "cancel", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONArray roledata) {
            if (roledata != null) {
                Iterator i = roledata.iterator();
                while (i.hasNext()) {
                    JSONObject innerObj = (JSONObject) i.next();
                    Long IntResult = (Long) innerObj.get("_pboolValid");
                    String PstrMessage = (String) innerObj.get("_pstrMessage");

                    if (IntResult == 1) {
                        tNotificationBL _tNotificationBL = new tNotificationBL();
                        List<tNotificationData> ListData = _tNotificationBL.getAllDataWillAlert("1");
                        if (ListData != null) {
                            for (tNotificationData dttNotificationData : ListData) {
                                NotificationManager tnotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                tnotificationManager.cancelAll();
                                ShortcutBadger.applyCount(MainMenu.this, 0);
                                System.gc();
                            }
                        }
                        new clsHelperBL().DeleteAllDB();
                        finish();
                        Intent nextScreen = new Intent(MainMenu.this, Splash.class);
                        startActivity(nextScreen);
                    } else {
                        Toast toast = Toast.makeText(MainMenu.this,
                                PstrMessage, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 25, 400);
                        toast.show();
                    }
                }

            } else {
                if (intProcesscancel == 1) {
                    onCancelled();
                } else {
                    _clsMainActivity.showCustomToast(getApplicationContext(), "Offline", false);
                }

            }
            Dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            Dialog.setMessage(new clsHardCode().txtMessLogOut);
            Dialog.setCancelable(false);
            Dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intProcesscancel = 1;
                }
            });
            Dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Dialog.dismiss();
        }

    }

    private void intent_activity(){
        if (i_view.equals("Notification")){
            String id = intent.getStringExtra("id");
            Class<?> fragmentClass = null;
            try {
                fragmentClass = Class.forName("com.kalbenutritionals.app.kalbespgmobile.Fragment" + i_view);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            toolbar.setTitle("Information");
            Fragment fragment = null;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("TAG_UUID", id);
                fragment.setArguments(bundle);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
            selectedId = 99;
        } else {
            try {
                Class<?> fragmentClass = Class.forName("com.kalbenutritionals.app.kalbespgmobile.Fragment" + i_view.replaceAll("\\s+", "") + "SPG");
                try {
                    for (int i = 0; i < listMenu.length; i++) {
                        if (("View " + listMenu[i]).equals(i_view + " SPG")) {
                            selectedId = i;
                            break;
                        }
                    }
                    toolbar.setTitle(i_view);
                    Fragment fragment = (Fragment) fragmentClass.newInstance();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent!=null){
            String view = intent .getStringExtra("key_view");
            if(view!=null){
                if (view.equals("Notification")){
                    String id = intent.getStringExtra("id");
                    Class<?> fragmentClass = null;
                    try {
                        fragmentClass = Class.forName("com.kalbenutritionals.app.kalbespgmobile.Fragment" + view);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    toolbar.setTitle("Information");
                    Fragment fragment = null;
                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                        Bundle bundle = new Bundle();
                        bundle.putString("TAG_UUID", id);
                        fragment.setArguments(bundle);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();
                    selectedId = 99;
                }
            }
        }
    }
}
