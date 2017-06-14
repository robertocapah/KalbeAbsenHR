package kalbeabsen.app.kalbenutritionals.kalbeabsen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import bl.tUserLoginBL;
import library.salesforce.common.tUserLoginData;

//import addons.tableview.SortableReportTableView;
//import bl.mEmployeeSalesProductBL;
//import bl.mProductBrandHeaderBL;
//import bl.tActivityBL;
//import bl.tCustomerBasedMobileHeaderBL;
//import bl.tSalesProductHeaderBL;
//import library.salesforce.common.mEmployeeSalesProductData;
//import library.salesforce.common.mProductBrandHeaderData;
//import library.salesforce.common.tActivityData;
//import library.salesforce.common.tCustomerBasedMobileHeaderData;
//import library.salesforce.common.tSalesProductHeaderData;
//import library.salesforce.common.visitplanAbsenData;

//import com.github.amlcurran.showcaseview.ShowcaseView;
//import com.github.amlcurran.showcaseview.SimpleShowcaseEventListener;
//import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class FragmentHome extends Fragment {

    View v;
    private Toolbar toolbar;
//    private SortableReportTableView ReportTableView;
    TextView username, branch, outlet, statusAbsen, totalBrand, totalProduct, totalReso, totalActivity, totalCustomerBase, tv_reso1, tv_reso2, tv_act1, tv_act2, tv_cb1, tv_cb2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_home,container,false);

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) v.findViewById(R.id.coordinatorLayout);

        username = (TextView) v.findViewById(R.id.username);
        branch = (TextView) v.findViewById(R.id.branch);
        outlet = (TextView) v.findViewById(R.id.outlet);
        statusAbsen = (TextView) v.findViewById(R.id.statusAbsen);
        totalBrand = (TextView) v.findViewById(R.id.totalBrand);
        totalProduct = (TextView) v.findViewById(R.id.totalProduct);
        totalReso = (TextView) v.findViewById(R.id.totalReso);
        totalActivity = (TextView) v.findViewById(R.id.totalActivity);
        totalCustomerBase = (TextView) v.findViewById(R.id.totalCustomerBase);
//        ReportTableView = (SortableReportTableView) v.findViewById(R.id.tableView);
        tv_reso1 = (TextView) v.findViewById(R.id.tv_reso1);
        tv_reso2 = (TextView) v.findViewById(R.id.tv_reso2);
        tv_act1 = (TextView) v.findViewById(R.id.tv_act1);
        tv_act2 = (TextView) v.findViewById(R.id.tv_act2);
        tv_cb1 = (TextView) v.findViewById(R.id.tv_cb1);
        tv_cb2 = (TextView) v.findViewById(R.id.tv_cb2);

        tUserLoginData dt=new tUserLoginBL().getUserActive();
//        tAbsenUserData dtAbsen = new tAbsenUserBL().getDataCheckInActive();

        /*visitplanAbsenData dtAbsen = new clsHelperBL().getDataCheckInActive();
        List<mProductBrandHeaderData> dtBrand = new mProductBrandHeaderBL().GetAllData();
        List<mEmployeeSalesProductData> dtProduct = new mEmployeeSalesProductBL().GetAllData();
        List<tSalesProductHeaderData> dtReso = new tSalesProductHeaderBL().getAllSalesProductHeader();
        List<tCustomerBasedMobileHeaderData> dtCbase = new tCustomerBasedMobileHeaderBL().getAllData();
        List<tActivityData> dtActivity = new tActivityBL().getAllData();
*/
        username.setText(dt.get_txtName());

        /*if(dtAbsen != null){
            branch.setText(dtAbsen.get_txtBranchName());
            outlet.setText(dtAbsen.get_txtOutletName());
            statusAbsen.setText("Active" + " " + new clsMainActivity().giveFormatDateTime(dtAbsen.get_dtDateCheckIn()));
        }
        else{
            branch.setText("Inactive");
            outlet.setText("Inactive");
            statusAbsen.setText("Inactive");
        }

        totalBrand.setText(String.valueOf(dtBrand.size()));
        totalProduct.setText(String.valueOf(dtProduct.size()));

        if(dtReso != null) totalReso.setText(String.valueOf(dtReso.size()));
        if(dtActivity != null) totalActivity.setText(String.valueOf(dtActivity.size()));
        if(dtCbase!=null) totalCustomerBase.setText(String.valueOf(dtCbase.size()));

        List<tSalesProductHeaderData> dt_reso_unpush = new tSalesProductHeaderBL().getAllDataByIntSyc("0");
        List<tSalesProductHeaderData> dt_reso_push = new tSalesProductHeaderBL().getAllDataByIntSyc("1");
        List<tActivityData> dt_act_unpush = new tActivityBL().getAllDataByIntSyc("0");
        List<tActivityData> dt_act_push = new tActivityBL().getAllDataByIntSyc("1");
        List<tCustomerBasedMobileHeaderData> dt_cb_unpush = new tCustomerBasedMobileHeaderBL().getAllDataByIntSyc("0");
        List<tCustomerBasedMobileHeaderData> dt_cb_push = new tCustomerBasedMobileHeaderBL().getAllDataByIntSyc("1");
*/
       /* tv_reso1.setText(String.valueOf(dt_reso_unpush.size()));
        tv_reso2.setText(String.valueOf(dt_reso_push.size()));
        tv_act1.setText(String.valueOf(dt_act_unpush.size()));
        tv_act2.setText(String.valueOf(dt_act_push.size()));
        tv_cb1.setText(String.valueOf(dt_cb_unpush.size()));
        tv_cb2.setText(String.valueOf(dt_cb_push.size()));*/

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void onHiddenFirstShowcase() {
        Toast.makeText(getContext(), "Jump", Toast.LENGTH_LONG).show();
    }

}
