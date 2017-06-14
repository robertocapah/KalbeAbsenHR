package addons.tableview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import kalbeabsen.app.kalbenutritionals.kalbeabsen.R;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.SortStateViewProviders;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;
import library.salesforce.common.ReportTable;


public class SortableReportTableView extends SortableTableView<ReportTable> {

    public SortableReportTableView(final Context context) {
        this(context, null);
    }

    public SortableReportTableView(final Context context, final AttributeSet attributes) {
        this(context, attributes, android.R.attr.listViewStyle);
    }

    public SortableReportTableView(final Context context, final AttributeSet attributes, final int styleAttributes) {
        super(context, attributes, styleAttributes);

        final SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(context, "");
        simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(context, R.color.table_header_text));

        setHeaderAdapter(simpleTableHeaderAdapter);
//        SortableReportTableView table = (SortableReportTableView) findViewById(R.id.tableView);
//        table.setColumnCount(0);
        final int rowColorEven = ContextCompat.getColor(context, R.color.table_data_row_even);
        final int rowColorOdd = ContextCompat.getColor(context, R.color.table_data_row_odd);
        setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(rowColorEven, rowColorOdd));
        setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());

        setColumnWeight(0, 0);
    }
}
