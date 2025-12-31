package managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import backend.Account;
import backend.Bill;
import backend.BillPayment;
import backend.BillRequest;
import backend.ClientAccount;
import builders.BillBuilder;
import builders.BillPaymentBuilder;
import builders.BillRequestBuilder;
import builders.BillDirector;
import dataAccessObjects.BillDAO;
import factories.DAOFactory;

public class BillManager {
	private static BillManager instance;
	private BillDAO billDao;
	private HashMap<String,Bill> bills;
	

	public BillManager() {
		this.billDao = (BillDAO) DAOFactory.createDAOFactory("bills");
		this.bills = new HashMap<String, Bill>();
	}
	
	public static BillManager getBillManager() {
		if(instance == null) {
			instance = new BillManager();
			return instance;
		}
		else
			return instance;
	}
	
	public void loadPayments() {
		
	}
	
	public void loadBills() {
		try {
			List<List<String>> billList = readBills();
			int rows = billList.size();
			int index=rows-1;
			while(index>=0) {
				addBillFromFile(billList.get(index));
				index--;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<List<String>> readBills() throws FileNotFoundException, IOException{
		return billDao.loadInfo();
	}
	
	public void addBillFromFile(List<String> billInfo) {
		BillDirector director = new BillDirector();
		BillRequestBuilder builder = new BillRequestBuilder();
		BillRequest request = director.createRequestFromString(builder, billInfo);
		createBill(request);
	}
	
	public void addPaymentFromFile(List<String> paymentInfo) {
		BillDirector director = new BillDirector();
		BillPaymentBuilder builder = new BillPaymentBuilder();
		BillPayment payment = director.createPaymentFromString(builder, paymentInfo);
		issuePayment(payment);
	}
	
	public void createBill(BillRequest request) {
		BillDirector director = new BillDirector();
		BillBuilder builder = new BillBuilder();
		BillPaymentBuilder paymentBuilder = new BillPaymentBuilder();
		Bill newBill = director.createBillFromRequest(builder, request);
		BillPayment firstIssue = director.createPaymentFromRequest(paymentBuilder, request);
	}
	
	public void assignBill(Bill bill, HashMap<String,Account> accounts) {
		ClientAccount issuer = (ClientAccount) accounts.get(bill.getSenderIBAN());
		ClientAccount receiver = (ClientAccount) accounts.get(bill.getReceiverIBAN());
		issuer.getPendingBills().put(bill.getRfCode(), bill);
	}
	
	public void issuePayment(BillPayment billPayment) {
		Bill ownerBill = bills.get(billPayment.getRfCode());
		ownerBill.getIssues().put(billPayment.getbillID(), billPayment);
	}
	
	
	public void updateDB() {
		billDao.updateDatabase(bills);
	}
}
