package managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import backend.Bill;
import backend.BillPayment;
import backend.BillRequest;
import backend.BusinessAccount;
import backend.ClientAccount;
import builders.BillBuilder;
import builders.BillPaymentBuilder;
import builders.BillDirector;
import dataAccessObjects.BillDAO;
import dataAccessObjects.BillPaymentDAO;
import factories.DAOFactory;

public class BillManager {
	private static BillManager instance;
	private BillDAO billDao;
	private BillPaymentDAO paymentDAO;
	private HashMap<String,Bill> bills;
	private long rfCodeCounter;
	private long idCounter;
	

	public BillManager() {
		this.billDao = (BillDAO) DAOFactory.createDAO("bills");
		this.paymentDAO = (BillPaymentDAO) DAOFactory.createDAO("issuedBills");
		this.bills = new HashMap<String, Bill>();
		this.rfCodeCounter = 0;
		this.idCounter = 0;
	}
	
	public static BillManager getBillManager() {
		if(instance == null) {
			instance = new BillManager();
			return instance;
		}
		else
			return instance;
	}
	
	public void initBills() {
		loadBills();
		loadPayments();
		calculateOwedAmounts();
	}
	
	public void loadPayments() {
		try {
			List<List<String>> paymentList = readPayments();
			int rows = paymentList.size();
			int index=rows-1;
			while(index>=0) {
				addPaymentFromFile(paymentList.get(index));
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
	
	public List<List<String>> readPayments() throws FileNotFoundException, IOException{
		return paymentDAO.loadInfo();
	}
	
	public void addBillFromFile(List<String> billInfo) {
		BillDirector director = new BillDirector();
		BillBuilder builder = new BillBuilder();
		Bill loadedBill = director.createBillFromFile(builder, billInfo);
		ClientAccount receiver = (ClientAccount) AccountManager.getAccountManager().getAccounts().get(loadedBill.getReceiverIBAN());
		BusinessAccount sender = (BusinessAccount) AccountManager.getAccountManager().getAccounts().get(loadedBill.getSenderIBAN());
		receiver.getPendingBills().put(loadedBill.getRfCode(), loadedBill);
		sender.getIssuedBills().put(loadedBill.getRfCode(), loadedBill);
		bills.put(loadedBill.getRfCode(), loadedBill);
		updateRfCounter(loadedBill);
	}
	
	public void updateRfCounter(Bill bill) {
		long rfNumber = Long.parseLong(bill.getRfCode().replace("R", "").replace("F", ""));
		if(rfNumber>this.rfCodeCounter)
			this.rfCodeCounter = rfNumber+1;
	}
	
	public void updateIdCounter(BillPayment payment) {
		if(Long.parseLong(payment.getbillID()) > this.idCounter)
			this.idCounter = Long.parseLong(payment.getbillID()) + 1;
	}
	
	public void addPaymentFromFile(List<String> paymentInfo) {
		BillDirector director = new BillDirector();
		BillPaymentBuilder builder = new BillPaymentBuilder();
		BillPayment payment = director.createPaymentFromFile(builder, paymentInfo);
		ClientAccount receiver = (ClientAccount) AccountManager.getAccountManager().getAccounts().get(payment.getReceiverIBAN());
		BusinessAccount sender = (BusinessAccount) AccountManager.getAccountManager().getAccounts().get(payment.getSenderIBAN());
		receiver.getPendingBillPayments().put(payment.getbillID(), payment);
		sender.getIssuedBills().get(payment.getRfCode()).getIssues().put(payment.getbillID(), payment);
		updateIdCounter(payment);
	}
	
	public void createBill(BillRequest request) {
		BillDirector director = new BillDirector();
		BillBuilder builder = new BillBuilder();
		Bill newBill = director.createBillFromRequest(builder, request, this.rfCodeCounter);
		this.rfCodeCounter++;
		assignBill(newBill);
		bills.put(newBill.getRfCode(), newBill);
	}
	
	public void calculateOwedAmounts() {
		Collection<Bill> allBills = bills.values();
		for(Bill bill : allBills) {
			Collection<BillPayment> issuedPayments = bill.getIssues().values();
			for(BillPayment curPayment : issuedPayments) {
				bill.addToTotal();
			}
		}
	}
	
	public void issuePayment(Bill bill) {
		bill.decreaseRemainingIssues();
		bill.addToTotal();
		BillDirector director = new BillDirector();
		BillPaymentBuilder builder = new BillPaymentBuilder();
		BillPayment nextPayment = director.createPayment(builder, bill, idCounter);
		idCounter++;
		bill.getIssues().put(nextPayment.getbillID(), nextPayment);
		ClientAccount receiver = (ClientAccount) AccountManager.getAccountManager().getAccounts().get(bill.getReceiverIBAN());
		receiver.getPendingBillPayments().put(nextPayment.getbillID(), nextPayment);
	}
	
	public void assignBill(Bill bill) {
		BusinessAccount issuer = (BusinessAccount) AccountManager.getAccountManager().getAccounts().get(bill.getSenderIBAN());
		ClientAccount receiver = (ClientAccount) AccountManager.getAccountManager().getAccounts().get(bill.getReceiverIBAN());
		receiver.getPendingBills().put(bill.getRfCode(), bill);
		issuer.getIssuedBills().put(bill.getRfCode(), bill);
	}
	
	public void deleteBill(Bill bill) {
		BusinessAccount issuer = (BusinessAccount) AccountManager.getAccountManager().getAccounts().get(bill.getSenderIBAN());
		ClientAccount receiver = (ClientAccount) AccountManager.getAccountManager().getAccounts().get(bill.getReceiverIBAN());
		receiver.getPendingBills().remove(bill.getRfCode());
		Collection<BillPayment> allIssues = bill.getIssues().values();
		for(BillPayment issue : allIssues) {
			receiver.getPendingBillPayments().remove(issue.getbillID());
		}
		issuer.getIssuedBills().remove(bill.getRfCode());
		bills.remove(bill.getRfCode());
	}
	
	public void updateDB() {
		billDao.updateDatabase(bills);
		paymentDAO.updateDatabase(bills);
	}
}
