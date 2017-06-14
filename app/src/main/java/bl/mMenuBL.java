package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.salesforce.common.mMenuData;
import library.salesforce.dal.mMenuDA;

public class mMenuBL extends clsMainBL {
    public void SaveData(List<mMenuData> Listdata) {
        SQLiteDatabase db = getDb();
        mMenuDA _mMenuDA = new mMenuDA(db);
        _mMenuDA.DeleteAllDAta(db);
        Long index = Long.valueOf(_mMenuDA.getContactsCount(db) + 1);
        for (mMenuData data : Listdata) {
            data.set_intId(index);
            _mMenuDA.SaveDataMConfig(db, data);
            index += 1;
        }
        db.close();
    }

    public mMenuData getMenuDataByMenuName(String menuName) {
        SQLiteDatabase db = getDb();
        mMenuDA _mMenuDA = new mMenuDA(db);
        mMenuData dt = _mMenuDA.getDataByNamaMenu(db, menuName);
        db.close();
        return dt;
    }

    public mMenuData getMenuDataByMenuName2(String menuName) {
        SQLiteDatabase db = getDb();
        mMenuDA _mMenuDA = new mMenuDA(db);
        mMenuData dt = _mMenuDA.getDataByNamaMenu2(db, menuName);
        db.close();
        return dt;
    }

    /*public List<mMenuData> getDatabyParentId(int id) {
        SQLiteDatabase db = getDb();
        mMenuDA _mMenuDA = new mMenuDA(db);
        tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(db);
        List<mMenuData> listData = _mMenuDA.getDatabyParentId(db, id);
        List<mMenuData> tmpData = new ArrayList<mMenuData>();
        List<tLeaveMobileData> listDataLeave = new tLeaveMobileBL().getData("");
        for (mMenuData data : listData) {
            if (listDataLeave.size() == 0 && data.get_TxtDescription().contains("mnReporting")) {
                if (_tSalesProductHeaderDA.getContactsCount(db) > 0) {
                    tmpData.add(data);
                }
            } else if (data.get_TxtDescription().contains("mnAbsenSPG")
                    || data.get_TxtDescription().contains("mnPushDataSPG")
                    || data.get_TxtDescription().contains("mnLeaveSPG")) {
                mEmployeeAreaDA _mEmployeeAreaDA = new mEmployeeAreaDA(db);
                mEmployeeBranchDA _mEmployeeBranchDA = new mEmployeeBranchDA(db);
                mProductBarcodeDA _mProductBarcodeDA = new mProductBarcodeDA(db);
                tLeaveMobileDA _tLeaveMobileDA = new tLeaveMobileDA(db);
                mEmployeeSalesProductDA _mEmployeeSalesProductDA = new mEmployeeSalesProductDA(db);
                if (_mEmployeeAreaDA.getContactsCount(db) > 0 && _mEmployeeBranchDA.getContactsCount(db) > 0) {
                    if (listDataLeave.size() == 0 && _mEmployeeAreaDA.getContactsCount(db) > 0 && _mEmployeeBranchDA.getContactsCount(db) > 0 && data.get_TxtDescription().contains("mnAbsenSPG")) {

                        int validate = 0;
                        if (data.get_TxtDescription().contains("mnAbsenSPG")||data.get_TxtDescription().contains("mnVisitPlanMobile")) {

                            validate = 1;

                            List<mEmployeeAreaData> datamEmployeeArea = new mEmployeeAreaBL().GetAllData();

                            for (mEmployeeAreaData dt : datamEmployeeArea) {
                                if (dt.get_txtLatitude() == "" || dt.get_txtLatitude() == null || dt.get_txtLatitude().equals("")
                                        && dt.get_txtLongitude() == "" || dt.get_txtLongitude() == null || dt.get_txtLongitude().equals("")) {
                                    validate = 0;
                                }
                            }
                        }

                        if (validate == 1) {
                            tmpData.add(data);
                        }
                    } else if (data.get_TxtDescription().contains("mnLeave") && listDataLeave.size() > 0 && _mEmployeeAreaDA.getContactsCount(db) > 0 && _mEmployeeBranchDA.getContactsCount(db) > 0) {
                        tmpData.add(data);

                    } else if (data.get_TxtDescription().contains("mnPushDataSPG") && listDataLeave.size() == 0) {
                        tmpData.add(data);
                    }

                    else if (data.get_TxtDescription().contains("mnLeave")) {
                        mTypeLeaveMobileDA _mTypeLeaveMobileDA = new mTypeLeaveMobileDA(db);
                        tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
                        if (_tAbsenUserDA.getContactsCountSubmit(db) == 0 && _mTypeLeaveMobileDA.getContactsCount(db) > 0) {
                            tmpData.add(data);
                        }
                    }


                } else if (data.get_TxtDescription().contains("mnLeave")) {
                    mTypeLeaveMobileDA _mTypeLeaveMobileDA = new mTypeLeaveMobileDA(db);
                    tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
                    if (_tAbsenUserDA.getContactsCountSubmit(db) == 0 && _mTypeLeaveMobileDA.getContactsCount(db) > 0) {
                        tmpData.add(data);
                    }
                }
            } else {
                tmpData.add(data);
            }
        }
        db.close();
        return tmpData;
    }*/
}