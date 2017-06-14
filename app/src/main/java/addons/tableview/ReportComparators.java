package addons.tableview;

import java.util.Comparator;

import library.salesforce.common.ReportTable;

public final class ReportComparators {

    private ReportComparators() {
        //no instance
    }

    public static Comparator<ReportTable> getCustomerNameComparator() {
        return new customerNameComparator();
    }

    public static Comparator<ReportTable> getCustomerSexComparator() {
        return new customerSexComparator();
    }

    public static Comparator<ReportTable> getCustomerNumberComparator() {
        return new customerNumberComparator();
    }

    public static Comparator<ReportTable> getStatusComparator() {
        return new statusComparator();
    }

    public static Comparator<ReportTable> getTotalProductComparator() {
        return new totalProductComparator();
    }

    public  static  Comparator<ReportTable> getNoCbComparator(){
        return  new NoCbComparator();
    }

    public  static  Comparator<ReportTable> getNoTelpComparator(){
        return new NoTelpComparator();
    }

    public  static  Comparator<ReportTable> getPICComparator(){
        return  new PICComparator();
    }

    public  static  Comparator<ReportTable> getTotalMemberComparator(){
        return new TotalMemberComparator();
    }

    public static Comparator<ReportTable> getNoSoComparator() {
        return new noSoComparator();
    }

    public static Comparator<ReportTable> getTotalItemComparator() {
        return new totalItemComparator();
    }

    public static Comparator<ReportTable> getTotalPriceComparator() {
        return new totalPriceComparator();
    }

    private static class noSoComparator implements Comparator<ReportTable> {

        @Override
        public int compare(final ReportTable data1, final ReportTable data2) {
            return data1.get_no_so().compareTo(data2.get_no_so());
        }
    }

    private static class customerNameComparator implements Comparator<ReportTable> {

        @Override
        public int compare(final ReportTable data1, final ReportTable data2) {
        	return data1.get_customer_name().compareTo(data2.get_customer_name());
        }
    }

    private static class customerSexComparator implements Comparator<ReportTable> {

        @Override
        public int compare(final ReportTable data1, final ReportTable data2) {
            return data1.get_customer_sex().compareTo(data2.get_customer_sex());
        }
    }

    private static class customerNumberComparator implements Comparator<ReportTable> {

        @Override
        public int compare(final ReportTable data1, final ReportTable data2) {
            return data1.get_customer_number().compareTo(data2.get_customer_number());
        }
    }

    private static class statusComparator implements Comparator<ReportTable> {

        @Override
        public int compare(final ReportTable data1, final ReportTable data2) {
            return data1.get_status().compareTo(data2.get_status());
        }
    }

    private static class totalProductComparator implements Comparator<ReportTable> {

        @Override
        public int compare(final ReportTable data1, final ReportTable data2) {
            if (Integer.parseInt(data1.get_total_product()) < Integer.parseInt(data2.get_total_product())) return -1;
            if (Integer.parseInt(data1.get_total_product()) > Integer.parseInt(data2.get_total_product())) return 1;
            return 0;
        }
    }

    private static class totalItemComparator implements Comparator<ReportTable> {

        @Override
        public int compare(final ReportTable data1, final ReportTable data2) {
            if (Integer.parseInt(data1.get_total_item()) < Integer.parseInt(data2.get_total_item())) return -1;
            if (Integer.parseInt(data1.get_total_item()) > Integer.parseInt(data2.get_total_item())) return 1;
            return 0;
        }
    }

    private static class totalPriceComparator implements Comparator<ReportTable> {

        @Override
        public int compare(final ReportTable data1, final ReportTable data2) {
            String dt1 = data1.get_total_price().replaceAll(",", "");
            String dt2 = data2.get_total_price().replaceAll(",", "");

            int dt1fix = Integer.parseInt(dt1.replaceAll("\\.", ""));
            int dt2fix = Integer.parseInt(dt2.replaceAll("\\.", ""));

            if (dt1fix < dt2fix) return -1;
            if (dt1fix > dt2fix) return 1;
            return 0;
        }
    }

    private static class NoCbComparator implements Comparator<ReportTable> {
        @Override
        public int compare(ReportTable data1, ReportTable data2) {


            return data1.get_no_cb().compareTo(data2.get_no_cb());
        }
    }

    private static class NoTelpComparator implements Comparator<ReportTable> {
        @Override
        public int compare(ReportTable data1, ReportTable data2) {
            return data1.get_no_tlp().compareTo(data2.get_no_tlp());
        }
    }

    private static class PICComparator implements Comparator<ReportTable> {
        @Override
        public int compare(ReportTable data1, ReportTable data2) {
            return data1.get_pic().compareTo(data2.get_pic());
        }
    }

    private static class TotalMemberComparator implements Comparator<ReportTable> {
        @Override
        public int compare(ReportTable data1, ReportTable data2) {
            return data1.get_total_member().compareTo(data2.get_total_member());
        }
    }
}
