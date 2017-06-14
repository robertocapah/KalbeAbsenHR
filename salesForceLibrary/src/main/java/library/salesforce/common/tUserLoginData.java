package library.salesforce.common;

public class tUserLoginData {

	public synchronized String get_txtDataId() {
		return _txtDataId;
	}
	public synchronized void set_txtDataId(String _txtDataId) {
		this._txtDataId = _txtDataId;
	}
	public synchronized String get_txtCab() {
		return _txtCab;
	}
	public synchronized void set_txtCab(String _txtCab) {
		this._txtCab = _txtCab;
	}
	public synchronized String get_txtDeviceId() {
		return _txtDeviceId;
	}
	public synchronized void set_txtDeviceId(String _txtDeviceId) {
		this._txtDeviceId = _txtDeviceId;
	}
	public synchronized String get_dtCheckIn() {
		return _dtCheckIn;
	}
	public synchronized void set_dtCheckIn(String _dtCheckIn) {
		this._dtCheckIn = _dtCheckIn;
	}
	public synchronized String get_dtCheckOut() {
		return _dtCheckOut;
	}
	public synchronized void set_dtCheckOut(String _dtCheckOut) {
		this._dtCheckOut = _dtCheckOut;
	}
	public synchronized String get_dtLogOut() {
		return _dtLogOut;
	}
	public synchronized void set_dtLogOut(String _dtLogOut) {
		this._dtLogOut = _dtLogOut;
	}
	public synchronized String get_dtLastLogin() {
		return _dtLastLogin;
	}
	public synchronized void set_dtLastLogin(String _dtLastLogin) {
		this._dtLastLogin = _dtLastLogin;
	}
	public synchronized String get_TxtEmail() {
		return _TxtEmail;
	}
	public synchronized void set_TxtEmail(String _TxtEmail) {
		this._TxtEmail = _TxtEmail;
	}
	public synchronized String get_TxtEmpId() {
		return _TxtEmpId;
	}
	public synchronized void set_TxtEmpId(String _TxtEmpId) {
		this._TxtEmpId = _TxtEmpId;
	}
	public synchronized String get_txtName() {
		return _txtName;
	}
	public synchronized void set_txtName(String _txtName) {
		this._txtName = _txtName;
	}
	public synchronized String get_txtUserName() {
		return _txtUserName;
	}
	public synchronized void set_txtUserName(String _txtUserName) {
		this._txtUserName = _txtUserName;
	}
	public tUserLoginData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tUserLoginData(int _intId, String _txtUserId, String _txtRoleId,
						  String _txtRoleName, String _txtPassword, String _txtUserName, String _txtName, String _txtPathImage,String _TxtEmail,String _TxtEmpId,String _dtLastLogin,
						  String _txtDeviceId,String _dtCheckIn,String _dtCheckOut,String _dtLogOut,String _txtCab,String _DataId, String _txtOutletCode, String _txtOutletName, String _txtBranchCode, String _txtSubmissionID, String _txtCheckLocation, String _txtImei) {
		this._intId = _intId;
		this._txtUserId = _txtUserId;
		this._txtRoleId = _txtRoleId;
		this._txtRoleName = _txtRoleName;
		this._txtPassword = _txtPassword;
		this._txtUserName = _txtUserName;
		this._txtName = _txtName;
		this._txtPathImage = _txtPathImage;
		this._TxtEmail = _TxtEmail;
		this._TxtEmpId = _TxtEmpId;
		this._dtLastLogin=_dtLastLogin;
		this._txtDeviceId=_txtDeviceId;
		this._dtCheckIn=_dtCheckIn;
		this._dtCheckOut=_dtCheckOut;
		this._dtLogOut=_dtLogOut;
		this._txtCab=_txtCab;
		this._txtDataId=_DataId;
		this._txtOutletCode=_txtOutletCode;
		this._txtOutletName=_txtOutletName;
		this._txtBranchCode=_txtBranchCode;
        this._txtSubmissionID=_txtSubmissionID;
        this._txtCheckLocation = _txtCheckLocation;
		this._txtImei = _txtImei;
	}
	public int get_intId() {
		return _intId;
	}
	public void set_intId(int _intId) {
		this._intId = _intId;
	}
	public String get_txtUserId() {
		return _txtUserId;
	}
	public void set_txtUserId(String _txtUserId) {
		this._txtUserId = _txtUserId;
	}
	public String get_txtRoleId() {
		return _txtRoleId;
	}
	public void set_txtRoleId(String _txtRoleId) {
		this._txtRoleId = _txtRoleId;
	}
	public String get_txtRoleName() {
		return _txtRoleName;
	}
	public void set_txtRoleName(String _txtRoleName) {
		this._txtRoleName = _txtRoleName;
	}
	public String get_txtPassword() {
		return _txtPassword;
	}
	public void set_txtPassword(String _txtPassword) {
		this._txtPassword = _txtPassword;
	}
	public String get_txtPathImage() {
		return _txtPathImage;
	}
	public void set_txtPathImage(String _txtPathImage) {
		this._txtPathImage = _txtPathImage;
	}
	public String get_txtOutletCode() {
		return _txtOutletCode;
	}
	public void set_txtOutletCode(String _txtOutletCode) {
		this._txtOutletCode = _txtOutletCode;
	}
	public String get_txtOutletName() {
		return _txtOutletName;
	}
	public void set_txtOutletName(String _txtOutletName) {
		this._txtOutletName = _txtOutletName;
	}
	public String get_txtBranchCode() {
		return _txtBranchCode;
	}
	public void set_txtBranchCode(String _txtBranchCode) {
		this._txtBranchCode = _txtBranchCode;
	}

	public String get_txtImei() {
		return _txtImei;
	}

	public void set_txtImei(String _txtImei) {
		this._txtImei = _txtImei;
	}

	private int _intId;
	private String _txtUserId;
	private String _txtRoleId;
	private String _txtRoleName;
	private String _txtPassword;
	private String _txtPathImage;
	private String _txtUserName;
	private String _txtName;
	private String _TxtEmail;
	private String _TxtEmpId;
	private String _dtLastLogin;
	private String _txtDeviceId;
	private String _dtCheckIn;
	private String _dtCheckOut;
	private String _dtLogOut;
	private String _txtCab;
	private String _txtDataId;
	private String _txtOutletCode;
	private String _txtOutletName;
	private String _txtBranchCode;
	private String _txtSubmissionID;
	private String _txtCheckLocation;
	private String _txtImei;

	public String Property_txtOutletCode="txtOutletCode";
	public String Property_txtOutletName="txtOutletName";
	public String Property_txtBranchCode="txtBranchCode";
	public String Property_txtDataId="txtDataId";
	public String Property_intId="intId";
	public String Property_TxtCab="TxtCab";
	public String Property_txtUserId="txtUserId";
	public String Property_txtRoleId="txtRoleId";
	public String Property_txtRoleName="txtRoleName";
	public String Property_txtPassword="txtPassword";
	public String Property_txtPathImage="txtPathImage";
	public String Property_txtUserName="txtUserName";
	public String Property_txtName="txtName";
	public String Property_TxtEmail = "TxtEmail";
	public String Property_TxtEmpId = "TxtEmpId";
	public String Property_DtLastLogin = "DtLastLogin";
	public String Property_TxtDeviceId = "TxtDeviceId";
	public String Property_DtCheckIn = "dtCheckIn";
	public String Property_DtCheckOut = "dtCheckOut";
	public String Property_DtLogOut = "dtLogOut";
	public String Property_ListOftUserLoginData="ListOftUserLoginData";
    public String Property_txtSubmissionID = "txtSubmissionID";
    public String Property_checkLocation = "txtCheckLocation";
	public String Property_txtImei = "txtImei";
	public String Property_All=Property_intId +","+
			Property_txtUserId +","+
			Property_txtRoleId +","+
			Property_txtRoleName +","+
			Property_txtPassword +","+
			Property_txtUserName +","+
			Property_txtName +","+
			Property_TxtEmail +","+
			Property_TxtEmpId +","+
			Property_txtPathImage +","+
			Property_DtLastLogin +","+
			Property_TxtDeviceId +","+
			Property_DtCheckIn +","+
			Property_DtCheckOut +","+
			Property_DtLogOut +","+
			Property_TxtCab+","+
			Property_txtDataId+","+
			Property_txtOutletCode+","+
			Property_txtOutletName+","+
			Property_txtBranchCode+","+
            Property_txtSubmissionID+","+
            Property_checkLocation+","+
			Property_txtImei;

    public String get_txtSubmissionID() {
        return _txtSubmissionID;
    }

    public void set_txtSubmissionID(String _txtSubmissionID) {
        this._txtSubmissionID = _txtSubmissionID;
    }

    public String get_txtCheckLocation() {
        return _txtCheckLocation;
    }

    public void set_txtCheckLocation(String _txtCheckLocation) {
        this._txtCheckLocation = _txtCheckLocation;
    }
}
