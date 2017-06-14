package library.salesforce.dal;

public enum enumCounterData {
	 NoDataSO			(1),
	 DateTimeServer  (2),
	 MonitorSchedule  (3),
	 NoStockOpname	(4),
	 NoGRN (5),
	 NoSalesOrder (6),
	 DatePartNow (7),
	 PeriodeNow (8),
	 NoPenguaran (9),
	 NoPurchaseOrder (10),
	 NoQuantityStock (11),
	 dtPushKBN (12),
	 CustomerBase (13),
	 MonitoringLocation(14)
	 ; 
	 enumCounterData(int idConfigData) {this.idConfigData = idConfigData;}
	public int getidCounterData() {
       return this.idConfigData;
   }
	private  final int idConfigData;
}
