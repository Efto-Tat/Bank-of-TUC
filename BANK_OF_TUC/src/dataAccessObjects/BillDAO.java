package dataAccessObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import backend.Bill;

public class BillDAO extends DAO{
	
	public BillDAO() {
		this.fileName = "bills";
	}
	
	public void updateDatabase(HashMap<String,Bill> allBills) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("bills.csv"))){
			Collection<Bill> bills = allBills.values();
			for(Bill curBill : bills) {
					bw.write(curBill.getSenderIBAN());
					bw.write(",");
					bw.write(curBill.getRfCode());
					bw.write(",");
					bw.write(curBill.getReceiverIBAN());
					bw.write(",");
					bw.write("€"+Float.toString(curBill.getAmountPerBill()));
					bw.write(",");
					if(curBill.getIssueFrequency() != null)	
						bw.write(curBill.getIssueFrequency());
					bw.write(",");
					bw.write("€"+Float.toString(curBill.getTotalAmountOwed()));
					bw.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
