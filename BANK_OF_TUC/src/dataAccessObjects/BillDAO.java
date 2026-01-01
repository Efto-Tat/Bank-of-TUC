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
			bw.write("SENDER IBAN, RF CODE, RECEIVER IBAN, AMOUNT PER ISSUE, ISSUE FREQUENCY (1 ISSUE PER # MONTHS), DAY OF ISSUE, TOTAL OWED AMOUNT, REMAINING NUMBER OF ISSUES");
			bw.newLine();
			for(Bill curBill : bills) {
					bw.write(curBill.getSenderIBAN());
					bw.write(",");
					bw.write(curBill.getRfCode());
					bw.write(",");
					bw.write(curBill.getReceiverIBAN());
					bw.write(",");
					bw.write("€"+Float.toString(curBill.getAmountPerBill()));
					bw.write(",");
					if(curBill.getIssueFrequency() != null) {
						bw.write(curBill.getIssueFrequency().toString());
					}
					bw.write(",");
					bw.write(curBill.getDayOfIssue().toString());
					bw.write(",");
					bw.write("€"+Float.toString(curBill.getTotalAmountOwed()));
					bw.write(",");
					bw.write(curBill.getRemainingIssues().toString());
					bw.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
