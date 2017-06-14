package library.salesforce.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class dataJson {

    public synchronized List<tAbsenUserData> getListOftAbsenUserData() {
        return ListOftAbsenUserData;
    }

    public synchronized void setListOftAbsenUserData(
            List<tAbsenUserData> listOftAbsenUserData) {
        ListOftAbsenUserData = listOftAbsenUserData;
    }

    public synchronized List<tErrorLogData> getListOftErrorLogData() {
        return ListOftErrorLogData;
    }

    public synchronized void setListOftErrorLogData(
            List<tErrorLogData> listOftErrorLogData) {
        ListOftErrorLogData = listOftErrorLogData;
    }
    public synchronized List<trackingLocationData> getListOfTrackingLocationData() {
        return ListOfTrackingLocationData;
    }

    public synchronized void setListOfTrackingLocationData(List<trackingLocationData> listOfTrackingLocationData) {
        ListOfTrackingLocationData = listOfTrackingLocationData;
    }

    public synchronized List<tDeviceInfoUserData> getListDatatDeviceInfoUser() {
        return ListDatatDeviceInfoUser;
    }

    public synchronized void setListDatatDeviceInfoUser(
            List<tDeviceInfoUserData> listDatatDeviceInfoUser) {
        ListDatatDeviceInfoUser = listDatatDeviceInfoUser;
    }

    public synchronized List<mMenuData> getListOfmMenuData() {
        return ListOfmMenuData;
    }

    public synchronized void setListOfmMenuData(List<mMenuData> listOfmMenuData) {
        ListOfmMenuData = listOfmMenuData;
    }

    public synchronized String getTxtValue() {
        return txtValue;
    }

    public synchronized void setTxtValue(String txtValue) {
        this.txtValue = txtValue;
    }

    public String getTxtMethod() {
        return txtMethod;
    }

    public void setTxtMethod(String txtMethod) {
        this.txtMethod = txtMethod;
    }

    public List<tUserLoginData> getListDatatUserLogin() {
        return ListDatatUserLogin;
    }

    public void setListDatatUserLogin(List<tUserLoginData> listDatatUserLogin) {
        ListDatatUserLogin = listDatatUserLogin;
    }

    public JSONObject txtJSON() throws JSONException {
        JSONObject resJson = new JSONObject();
        Collection<JSONObject> itemsListJquey = new ArrayList<JSONObject>();
        try {
            if (this.getListDatamConfig() != null) {
                mconfigData dtConfig = new mconfigData();
                for (mconfigData data : this.getListDatamConfig()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dtConfig.Property_intEditAdmin, String.valueOf(data.get_intEditAdmin()));
                    item1.put(dtConfig.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dtConfig.Property_txtName, String.valueOf(data.get_txtName()));
                    item1.put(dtConfig.Property_txtValue, String.valueOf(data.get_txtValue()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtConfig.Property_ListOfMconfig, new JSONArray(itemsListJquey));
            }

            if (this.getListDatatUserLogin() != null) {
                tUserLoginData dttUserLoginData = new tUserLoginData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tUserLoginData data : this.getListDatatUserLogin()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttUserLoginData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttUserLoginData.Property_txtPassword, String.valueOf(data.get_txtPassword()));
                    item1.put(dttUserLoginData.Property_txtPathImage, String.valueOf(data.get_txtPathImage()));
                    item1.put(dttUserLoginData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item1.put(dttUserLoginData.Property_txtRoleName, String.valueOf(data.get_txtRoleName()));
                    item1.put(dttUserLoginData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item1.put(dttUserLoginData.Property_txtDataId, String.valueOf(data.get_txtDataId()));
                    item1.put(dttUserLoginData.Property_DtCheckIn, String.valueOf(data.get_dtCheckIn()));
                    item1.put(dttUserLoginData.Property_DtCheckOut, String.valueOf(data.get_dtCheckOut()));
                    item1.put(dttUserLoginData.Property_DtLastLogin, String.valueOf(data.get_dtLastLogin()));
                    item1.put(dttUserLoginData.Property_DtLogOut, String.valueOf(data.get_dtLogOut()));
                    item1.put(dttUserLoginData.Property_TxtCab, String.valueOf(data.get_txtCab()));
                    item1.put(dttUserLoginData.Property_TxtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dttUserLoginData.Property_TxtEmail, String.valueOf(data.get_TxtEmail()));
                    item1.put(dttUserLoginData.Property_TxtEmpId, String.valueOf(data.get_TxtEmpId()));
                    item1.put(dttUserLoginData.Property_txtName, String.valueOf(data.get_txtName()));
                    item1.put(dttUserLoginData.Property_txtPassword, String.valueOf(data.get_txtPassword()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttUserLoginData.Property_ListOftUserLoginData, new JSONArray(itemsListJquey));
            }

            if (this.getListDatatDeviceInfoUser() != null) {
                tDeviceInfoUserData dttDeviceInfoUserData = new tDeviceInfoUserData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tDeviceInfoUserData data : this.getListDatatDeviceInfoUser()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttDeviceInfoUserData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttDeviceInfoUserData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dttDeviceInfoUserData.Property_txtDevice, String.valueOf(data.get_txtDevice()));
                    item1.put(dttDeviceInfoUserData.Property_txtModel, String.valueOf(data.get_txtModel()));
                    item1.put(dttDeviceInfoUserData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item1.put(dttDeviceInfoUserData.Property_txtImei, String.valueOf(data.get_txtImei()));
                    item1.put(dttDeviceInfoUserData.Property_txtVersion, String.valueOf(data.get_txtVersion()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttDeviceInfoUserData.Property_ListOftDeviceInfoUserData, new JSONArray(itemsListJquey));
            }

            if (this.getListOfmMenuData() != null) {
                mMenuData dtmMenuData = new mMenuData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (mMenuData data : this.getListOfmMenuData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dtmMenuData.Property_intId, Long.valueOf(data.get_intId()));
                    item1.put(dtmMenuData.Property_IntOrder, Long.valueOf(data.get_IntOrder()));
                    item1.put(dtmMenuData.Property_IntParentID, Long.valueOf(data.get_IntParentID()));
                    item1.put(dtmMenuData.Property_TxtDescription, String.valueOf(data.get_TxtDescription()));
                    item1.put(dtmMenuData.Property_TxtLink, String.valueOf(data.get_TxtLink()));
                    item1.put(dtmMenuData.Property_TxtMenuName, String.valueOf(data.get_TxtMenuName()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtmMenuData.Property_ListOfMMenuData, new JSONArray(itemsListJquey));
            }

            if (this.getListOfTrackingLocationData() != null){
                trackingLocationData dtTrackingLocationData = new trackingLocationData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (trackingLocationData data : this.getListOfTrackingLocationData()){
                    JSONObject item1 = new JSONObject();
                    item1.put(dtTrackingLocationData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dtTrackingLocationData.Property_txtLongitude, String.valueOf(data.get_txtLongitude()));
                    item1.put(dtTrackingLocationData.Property_txtLatitude, String.valueOf(data.get_txtLatitude()));
                    item1.put(dtTrackingLocationData.Property_txtAccuracy, String.valueOf(data.get_txtAccuracy()));
                    item1.put(dtTrackingLocationData.Property_txtTime, String.valueOf(data.get_txtTime()));
                    item1.put(dtTrackingLocationData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item1.put(dtTrackingLocationData.Property_txtUsername, String.valueOf(data.get_txtUsername()));
                    item1.put(dtTrackingLocationData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item1.put(dtTrackingLocationData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dtTrackingLocationData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dtTrackingLocationData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    item1.put(dtTrackingLocationData.Property_txtNIK, String.valueOf(data.get_txtNIK()));
                    item1.put(dtTrackingLocationData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dtTrackingLocationData.Property_intSync, String.valueOf(data.get_intSync()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dtTrackingLocationData.Property_ListOftrackingLocation, new JSONArray(itemsListJquey));
            }

            if (this.getListOftAbsenUserData() != null) {
                tAbsenUserData dttAbsenUserData = new tAbsenUserData();
                itemsListJquey = new ArrayList<JSONObject>();
                for (tAbsenUserData data : this.getListOftAbsenUserData()) {
                    JSONObject item1 = new JSONObject();
                    item1.put(dttAbsenUserData.Property_dtDateCheckIn, String.valueOf(data.get_dtDateCheckIn()));
                    item1.put(dttAbsenUserData.Property_dtDateCheckOut, String.valueOf(data.get_dtDateCheckOut()));
                    item1.put(dttAbsenUserData.Property_intId, String.valueOf(data.get_intId()));
                    item1.put(dttAbsenUserData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
                    item1.put(dttAbsenUserData.Property_intSync, String.valueOf(data.get_intSync()));
                    item1.put(dttAbsenUserData.Property_txtAbsen, String.valueOf(data.get_txtAbsen()));
                    item1.put(dttAbsenUserData.Property_txtAccuracy, String.valueOf(data.get_txtAccuracy()));
                    item1.put(dttAbsenUserData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
                    item1.put(dttAbsenUserData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
                    item1.put(dttAbsenUserData.Property_txtLatitude, String.valueOf(data.get_txtLatitude()));
                    item1.put(dttAbsenUserData.Property_txtLongitude, String.valueOf(data.get_txtLongitude()));
                    item1.put(dttAbsenUserData.Property_txtOutletCode, String.valueOf(data.get_txtOutletCode()));
                    item1.put(dttAbsenUserData.Property_txtOutletName, String.valueOf(data.get_txtOutletName()));
                    item1.put(dttAbsenUserData.Property_txtUserId, String.valueOf(data.get_txtUserId()));
                    item1.put(dttAbsenUserData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
                    item1.put(dttAbsenUserData.Property_txtRoleId, String.valueOf(data.get_txtRoleId()));
                    item1.put(dttAbsenUserData.Property_txtImg1, String.valueOf(data.get_txtImg1()));
                    item1.put(dttAbsenUserData.Property_txtImg2, String.valueOf(data.get_txtImg2()));
                    itemsListJquey.add(item1);
                }
                resJson.put(dttAbsenUserData.Property_ListOftAbsenUser, new JSONArray(itemsListJquey));
            }
        } catch (Exception ex) {

        }
        resJson.put(Property_intResult, getIntResult());
        resJson.put(Property_txtDescription, getTxtDescription());
        resJson.put(Property_txtMessage, getTxtMessage());
        resJson.put(Property_txtMethod, getTxtMethod());
        resJson.put(Property_txtValue, getTxtValue());
        resJson.put(Property_txtSessionLoginId, get_txtSessionLoginId());
        resJson.put(Property_txtUserId, get_txtUserId());
        resJson.put(Property_txtVersionApp, get_txtVersionApp());
        return resJson;
    }

    public String getIntResult() {
        return intResult;
    }

    public void setIntResult(String intResult) {
        this.intResult = intResult;
    }

    public String getTxtMessage() {
        return txtMessage;
    }

    public void setTxtMessage(String txtMessage) {
        this.txtMessage = txtMessage;
    }

    public String getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }

    public List<mconfigData> getListDatamConfig() {
        return ListDatamConfig;
    }

    public void setListDatamConfig(List<mconfigData> listDatamConfig) {
        ListDatamConfig = listDatamConfig;
    }

    public dataJson() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String intResult;
    private String txtMessage;
    private String txtMethod;
    private String txtValue;
    private String txtDescription;
    private List<mconfigData> ListDatamConfig;
    private List<tUserLoginData> ListDatatUserLogin;
    private List<tDeviceInfoUserData> ListDatatDeviceInfoUser;
    private List<mMenuData> ListOfmMenuData;
    private List<trackingLocationData> ListOfTrackingLocationData;
    private List<tErrorLogData> ListOftErrorLogData;
    private List<tAbsenUserData> ListOftAbsenUserData;

    private String _txtSessionLoginId;

    public String get_txtSessionLoginId() {
        return _txtSessionLoginId;
    }

    public void set_txtSessionLoginId(String _txtSessionLoginId) {
        this._txtSessionLoginId = _txtSessionLoginId;
    }

    private String _txtUserId;

    public String get_txtUserId() {
        return _txtUserId;
    }

    public void set_txtUserId(String _txtUserId) {
        this._txtUserId = _txtUserId;
    }

    public String get_txtVersionApp() {
        return _txtVersionApp;
    }

    public void set_txtVersionApp(String _txtVersionApp) {
        this._txtVersionApp = _txtVersionApp;
    }

    public String _txtVersionApp;

    private String Property_txtUserId = "txtUserId";
    private String Property_txtSessionLoginId = "txtSessionLoginId";
    public String Property_intResult = "intResult";
    public String Property_txtMessage = "txtMessage";
    public String Property_txtDescription = "txtDescription";
    public String Property_txtMethod = "txtMethod";
    public String Property_txtValue = "txtValue";
    public String Property_txtVersionApp = "txtVesionApp";
}
