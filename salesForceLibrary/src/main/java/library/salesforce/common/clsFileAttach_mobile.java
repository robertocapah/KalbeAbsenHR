package library.salesforce.common;

/**
 * Created by aan.junianto on 8/06/2017.
 */

public class clsFileAttach_mobile {

    public synchronized String get_txtIDFile() {
        return _txtIDFile;
    }

    public synchronized void set_txtIDFile(String _txtIDFile) {
        this._txtIDFile = _txtIDFile;
    }

    public synchronized String get_txtIdHeaderNotif() {
        return _txtIdHeaderNotif;
    }

    public synchronized void set_txtIdHeaderNotif(String _txtIdHeaderNotif) {
        this._txtIdHeaderNotif = _txtIdHeaderNotif;
    }

    public synchronized String get_txtDesc() {
        return _txtDesc;
    }

    public synchronized void set_txtDesc(String _txtDesc) {
        this._txtDesc = _txtDesc;
    }

    public synchronized String get_byteInitialVector() {
        return _byteInitialVector;
    }

    public synchronized void set_byteInitialVector(String _byteInitialVector) {
        this._byteInitialVector = _byteInitialVector;
    }

    public String get_txtLinkFileAttach() {
        return _txtLinkFileAttach;
    }

    public synchronized void set_txtLinkFileAttach(String _txtLinkFileAttach) {
        this._txtLinkFileAttach = _txtLinkFileAttach;
    }

    public synchronized String get_txtNameFileEncrypt() {
        return _txtNameFileEncrypt;
    }

    public synchronized void set_txtNameFileEncrypt(String _txtNameFileEncrypt) {
        this._txtNameFileEncrypt = _txtNameFileEncrypt;
    }

    public synchronized String get_txtTypeFile() {
        return _txtTypeFile;
    }

    public synchronized void set_txtTypeFile(String _txtTypeFile) {
        this._txtTypeFile = _txtTypeFile;
    }

    public synchronized String get_intActive() {
        return _intActive;
    }

    public synchronized void set_intActive(String _intActive) {
        this._intActive = _intActive;
    }

    public synchronized String get_dtInserted() {
        return _dtInserted;
    }

    public synchronized void set_dtInserted(String _dtInserted) {
        this._dtInserted = _dtInserted;
    }

    public synchronized String get_dtUpdated() {
        return _dtUpdated;
    }

    public synchronized void set_dtUpdated(String _dtUpdated) {
        this._dtUpdated = _dtUpdated;
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

    private String _txtIDFile;
    private String _txtIdHeaderNotif;
    private String _txtDesc;
    private String _byteInitialVector;
    private String _txtLinkFileAttach;
    private String _txtNameFileEncrypt;
    private String _txtTypeFile;
    private String _intActive;
    private String _dtInserted;
    private String _dtUpdated;
    private String _intSubmit;
    private String _intSync;

    public synchronized String get_intStatus() {
        return _intStatus;
    }

    public synchronized void set_intStatus(String _intStatus) {
        this._intStatus = _intStatus;
    }

    private  String _intStatus;

    public String Property_txtIDFile="_txtIDFile";
    public String Property_txtIdHeaderNotif="_txtIdHeaderNotif";
    public String Property_txtDesc="_txtDesc";
    public String Property_byteInitialVector="_byteInitialVector";
    public String Property_txtLinkFileAttach="_txtLinkFileAttach";
    public String Property_txtNameFileEncrypt="_txtNameFileEncrypt";
    public String Property_txtTypeFile="_txtTypeFile";
    public String Property_intActive="_intActive";
    public String Property_dtInserted="_dtInserted";
    public String Property_dtUpdated="_dtUpdated";
    public String Property_intSubmit="_intSubmit";
    public String Property_intSync="_intSync";
    public String Property_intStatus="_intStatus";

    public String PropertyAll=Property_txtIDFile+","+Property_txtIdHeaderNotif+","+Property_txtDesc+","+Property_byteInitialVector+","+Property_txtLinkFileAttach+","+
            Property_txtNameFileEncrypt+","+Property_txtTypeFile+","+Property_intActive+","+Property_dtInserted+","+Property_dtUpdated+","+Property_intSubmit+","+Property_intSync+","+Property_intStatus;

}
