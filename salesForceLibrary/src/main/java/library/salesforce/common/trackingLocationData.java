package library.salesforce.common;

/**
 * Created by Rian Andrivani on 5/15/2017.
 */

public class trackingLocationData {
    public synchronized String get_intId() {
        return _intId;
    }

    public synchronized void set_intId(String _intId) {
        this._intId = _intId;
    }

    public synchronized String get_txtLongitude() {
        return _txtLongitude;
    }

    public synchronized void set_txtLongitude(String _txtLongitude) {
        this._txtLongitude = _txtLongitude;
    }

    public synchronized String get_txtLatitude() {
        return _txtLatitude;
    }

    public synchronized void set_txtLatitude(String _txtLatitude) {
        this._txtLatitude = _txtLatitude;
    }

    public synchronized String get_txtAccuracy() {
        return _txtAccuracy;
    }

    public synchronized void set_txtAccuracy(String _txtAccuracy) {
        this._txtAccuracy = _txtAccuracy;
    }

    public synchronized String get_txtTime() {
        return _txtTime;
    }

    public synchronized void set_txtTime(String _txtTime) {
        this._txtTime = _txtTime;
    }

    public synchronized String get_txtUserId() {
        return _txtUserId;
    }

    public synchronized void set_txtUserId(String _txtUserId) {
        this._txtUserId = _txtUserId;
    }

    public synchronized String get_txtUsername() {
        return _txtUsername;
    }

    public synchronized void set_txtUsername(String _txtUsername) {
        this._txtUsername = _txtUsername;
    }

    public synchronized String get_txtRoleId() {
        return _txtRoleId;
    }

    public synchronized void set_txtRoleId(String _txtRoleId) {
        this._txtRoleId = _txtRoleId;
    }

    public synchronized String get_txtDeviceId() {
        return _txtDeviceId;
    }

    public synchronized void set_txtDeviceId(String _txtDeviceId) {
        this._txtDeviceId = _txtDeviceId;
    }

    public synchronized String get_txtBranchCode() {
        return _txtBranchCode;
    }

    public synchronized void set_txtBranchCode(String _txtBranchCode) {
        this._txtBranchCode = _txtBranchCode;
    }

    public synchronized String get_txtOutletCode() {
        return _txtOutletCode;
    }

    public synchronized void set_txtOutletCode(String _txtOutletCode) {
        this._txtOutletCode = _txtOutletCode;
    }

    public synchronized String get_txtNIK() {
        return _txtNIK;
    }

    public synchronized void set_txtNIK(String _txtNIK) {
        this._txtNIK = _txtNIK;
    }

    public synchronized String get_intSubmit() {
        return _intSubmit;
    }

    public synchronized void set_intSubmit(String _intSubmit) {
        this._intSubmit = _intSubmit;
    }

    public synchronized String get_intSync() {
        return _intSync;
    }

    public synchronized void set_intSync(String _intSync) {
        this._intSync = _intSync;
    }

    private String _intId;
    private String _txtLongitude;
    private String _txtLatitude;
    private String _txtAccuracy;
    private String _txtTime;
    private String _txtUserId;
    private String _txtUsername;
    private String _txtRoleId;
    private String _txtDeviceId;
    private String _txtBranchCode;
    private String _txtOutletCode;
    private String _txtNIK;
    private String _intSubmit;
    private String _intSync;

    public String Property_intId = "intId";
    public String Property_txtLongitude = "txtLongitude";
    public String Property_txtLatitude = "txtLatitude";
    public String Property_txtAccuracy = "txtAccuracy";
    public String Property_txtTime = "Time";
    public String Property_txtUserId = "txtUserId";
    public String Property_txtUsername = "txtUsername";
    public String Property_txtRoleId = "txtRoleId";
    public String Property_txtDeviceId = "txtDeviceId";
    public String Property_txtBranchCode = "txtBranchCode";
    public String Property_txtOutletCode = "txtOutletCode";
    public String Property_txtNIK = "txtNIK";
    public String Property_intSubmit = "intSubmit";
    public String Property_intSync = "intSync";
    public String Property_ListOftrackingLocation = "ListOfTrackingLocationData";
    public String Property_All = Property_intId + "," +
            Property_txtLongitude + "," +
            Property_txtLatitude + "," +
            Property_txtAccuracy + "," +
            Property_txtTime + "," +
            Property_txtUserId + "," +
            Property_txtUsername + "," +
            Property_txtRoleId + "," +
            Property_txtDeviceId + "," +
            Property_txtBranchCode + "," +
            Property_txtOutletCode + "," +
            Property_txtNIK + "," +
            Property_intSubmit + "," +
            Property_intSync;

    public trackingLocationData() {
        super();
    }

    public trackingLocationData(String _intId, String _txtLongitude, String _txtLatitude, String _txtAccuracy,
                                String _txtTime, String _txtUserId, String _txtUsername, String _txtRoleId,
                                String _txtDeviceId, String _txtBranchCode, String _txtOutletCode, String _txtNIK,
                                String _intSubmit, String _intSync) {
        this._intId = _intId;
        this._txtLongitude = _txtLongitude;
        this._txtLatitude = _txtLatitude;
        this._txtAccuracy = _txtAccuracy;
        this._txtTime = _txtTime;
        this._txtUserId = _txtUserId;
        this._txtUsername = _txtUsername;
        this._txtRoleId = _txtRoleId;
        this._txtDeviceId = _txtDeviceId;
        this._txtBranchCode = _txtBranchCode;
        this._txtOutletCode = _txtOutletCode;
        this._txtNIK = _txtNIK;
        this._intSubmit = _intSubmit;
        this._intSync = _intSync;
    }
}
