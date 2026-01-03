package dataAccessObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import backend.TransferStandingOrder;

public class TransferStandingOrderDAO extends DAO{
	
	public TransferStandingOrderDAO() {
		fileName="transferStandingOrders";
	}
	
	public void updateDatabase(HashMap<String,TransferStandingOrder> standingOrders) { //Maybe improve this?
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("transferStandingOrders.csv"))){
			Collection<TransferStandingOrder> allTransferStandingOrders = standingOrders.values();
			bw.write("NAME, DESCRIPTION, OWNER, AMOUNT, START DATE, END DATE, FEE, SENDER IBAN, RECEIVER IBAN, PER # MONTHS, DAY OF PAYMENT");
			bw.newLine();
			for(TransferStandingOrder curOrder : allTransferStandingOrders) {
				bw.write(curOrder.getOrderName());
				bw.write(",");
				bw.write(curOrder.getDescription());
				bw.write(",");
				bw.write("€"+Float.toString(((TransferStandingOrder) curOrder).getPaymentAmount()));
				bw.write(",");
				bw.write(curOrder.getStartDate());
				bw.write(",");
				bw.write(curOrder.getEndDate());
				bw.write(",");
				bw.write("€"+curOrder.getPaymentFee());
				bw.write(",");
				bw.write(curOrder.getSenderIBAN());
				bw.write(",");
				bw.write(curOrder.getReceiverIBAN());
				bw.write(",");
				bw.write(Integer.toString(curOrder.getPerMonths()));
				bw.write(",");
				bw.write(Integer.toString(curOrder.getDayOfPayment()));
				bw.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
