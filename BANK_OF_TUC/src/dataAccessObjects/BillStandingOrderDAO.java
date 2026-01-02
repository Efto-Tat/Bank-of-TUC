package dataAccessObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import backend.BillStandingOrder;

public class BillStandingOrderDAO extends DAO{
	
	public BillStandingOrderDAO() {
		fileName="billStandingOrders";
	}
	
	public void updateDatabase(HashMap<String,BillStandingOrder> standingOrders) { //Maybe improve this?
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("billStandingOrders.csv"))){
			Collection<BillStandingOrder> allBillStandingOrders = standingOrders.values();
			bw.write("RF CODE, NAME, DESCRIPTION, OWNER, AMOUNT THRESHOLD, START DATE, END DATE, FEE, OWNER IBAN");
			bw.newLine();
			for(BillStandingOrder curOrder : allBillStandingOrders) {
				bw.write(curOrder.getRfCode());
				bw.write(",");
				bw.write(curOrder.getOrderName());
				bw.write(",");
				bw.write(curOrder.getDescription());
				bw.write(",");
				bw.write(curOrder.getSenderIBAN()); //ADD OWNER NAME
				bw.write(",");
				bw.write("€"+Float.toString(((BillStandingOrder) curOrder).getMaxAmount()));
				bw.write(",");
				bw.write(curOrder.getStartDate());
				bw.write(",");
				bw.write(curOrder.getEndDate());
				bw.write(",");
				bw.write("€"+curOrder.getPaymentFee());
				bw.write(",");
				bw.write(curOrder.getSenderIBAN());
				bw.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
