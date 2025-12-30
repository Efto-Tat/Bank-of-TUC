package dataAccessObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import backend.AccountOwner;
import backend.Bill;

public class BillDAO extends DAO{
	
	public BillDAO() {
		this.fileName = "bills";
	}
	
	public void updateDatabase(HashMap<String,Bill> allBills, HashMap<String,AccountOwner> users) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("bankAccounts.csv"))){
			Collection<Bill> bills = allBills.values();
			for(Bill curBill : bills) {
				bw.write(users.get(curBill.getSenderIBAN()).getName());
				bw.write(",");
				bw.write(curBill.getRfCode());
				bw.write(",");
				bw.write(curBill.getBillCode());
				bw.write(",");
				bw.write(users.get(curBill.getReceiverIBAN()).getName());
				bw.write(",");
				bw.write("€"+curBill.getAmountPerBill());
				bw.write(",");
				bw.write(curBill.getDateIssued());
				bw.write(",");
				bw.write(curBill.getDueDate());
				bw.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
