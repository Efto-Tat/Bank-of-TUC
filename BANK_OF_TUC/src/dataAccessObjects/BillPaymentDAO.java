package dataAccessObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import backend.Bill;
import backend.BillPayment;
import backend.InteractionStatus;

public class BillPaymentDAO extends DAO{
	
	public BillPaymentDAO() {
		this.fileName = "issuedBills";
	}
	
	public void updateDatabase(HashMap<String,Bill> allBills) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("issuedBills.csv"))){
			Collection<Bill> bills = allBills.values();
			bw.write("SENDER IBAN, RF CODE, STATUS, BILL ID, RECEIVER IBAN, AMOUNT, DATE PAID, TIME PAID, DATE ISSUED, DUE DATE");
			bw.newLine();
			for(Bill curBill : bills) {
				Collection<BillPayment> issuedPayments = curBill.getIssues().values();
				for(BillPayment curIssue : issuedPayments) {
					bw.write(curBill.getSenderIBAN());
					bw.write(",");
					bw.write(curBill.getRfCode());
					bw.write(",");
					bw.write(curIssue.getStatus().name());
					bw.write(",");
					bw.write(curIssue.getbillID());
					bw.write(",");
					bw.write(curBill.getReceiverIBAN());
					bw.write(",");
					bw.write(Float.toString(curIssue.getAmount()));
					bw.write(",");
					if(curIssue.getStatus() == InteractionStatus.PENDING) {
						bw.write(",");
						bw.write(",");
					}
					if(curIssue.getStatus() == InteractionStatus.SUCCESFUL) {
						bw.write(curIssue.getDate());
						bw.write(",");
						bw.write(curIssue.getTime());
						bw.write(",");
					}
					bw.write(curIssue.getDateIssued());
					bw.write(",");
					bw.write(curIssue.getDueDate());
					bw.newLine();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
