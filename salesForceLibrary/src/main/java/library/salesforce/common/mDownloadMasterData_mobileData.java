package library.salesforce.common;

/**
 * Created by ASUS ZE on 02/05/2017.
 */

public class mDownloadMasterData_mobileData {
    public synchronized String get_intId() {
        return _intId;
    }

    public synchronized void set_intId(String _intId) {
        this._intId = _intId;
    }

    public synchronized String get_intModule() {
        return _intModule;
    }

    public synchronized void set_intModule(String _intModule) {
        this._intModule = _intModule;
    }

    public synchronized String get_txtModuleName() {
        return _txtModuleName;
    }

    public synchronized void set_txtModuleName(String _txtModuleName) {
        this._txtModuleName = _txtModuleName;
    }

    public synchronized String get_txtMasterData() {
        return _txtMasterData;
    }

    public synchronized void set_txtMasterData(String _txtMasterData) {
        this._txtMasterData = _txtMasterData;
    }

    public synchronized String get_intVersionApp() {
        return _intVersionApp;
    }

    public synchronized void set_intVersionApp(String _intVersionApp) {
        this._intVersionApp = _intVersionApp;
    }

    public synchronized String get_txtTypeApp() {
        return _txtTypeApp;
    }

    public synchronized void set_txtTypeApp(String _txtTypeApp) {
        this._txtTypeApp = _txtTypeApp;
    }

    private String _intId;
    public String _intModule;
    public String _txtModuleName;
    public String _txtMasterData;
    public String _intVersionApp;
    public String _txtTypeApp;

    public String get_txtVersion() {
        return _txtVersion;
    }

    public void set_txtVersion(String _txtVersion) {
        this._txtVersion = _txtVersion;
    }

    public  String _txtVersion;


    public mDownloadMasterData_mobileData() {
        super();
        // TODO Auto-generated constructor stub
    }
    public mDownloadMasterData_mobileData(String _intId, String _intModule,
                                          String _txtModuleName,
                                          String _txtMasterData, String _intVersionApp,
                                          String _txtTypeApp, String _txtVersion) {
        this._intId = _intId;
        this._intModule = _intModule;
        this._txtModuleName = _txtModuleName;
        this._txtMasterData = _txtMasterData;
        this._intVersionApp = _intVersionApp;
        this._txtTypeApp = _txtTypeApp;
        this._txtVersion = _txtVersion;
    }

    public String Property_intId="intId";
    public String Property_intModule="intModule";
    public String Property_txtModuleName="txtModuleName";
    public String Property_txtMasterData="txtMasterData";
    public String Property_intVersionApp="intVersionApp";
    public String Property_txtTypeApp="txtTypeApp";
    public String Property_txtVersion="txtVersion";
    public String Property_All= Property_intId +","+ Property_intModule+","+Property_txtModuleName+","+
            Property_txtMasterData+","+Property_intVersionApp+","+Property_txtTypeApp+","+Property_txtVersion;


}
