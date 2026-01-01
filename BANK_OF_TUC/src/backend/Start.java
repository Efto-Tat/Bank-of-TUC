package backend;

import managers.AccountManager;
import managers.BillManager;
import managers.UserManager;

public class Start {
	
	public Start() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		UserManager userManager = UserManager.getUserManager();
		AccountManager accManager = AccountManager.getAccountManager();
		BillManager billManager = BillManager.getBillManager();
		userManager.loadUsers();
		accManager.loadAccounts();
		billManager.initBills();
		BillRequest newRequest = new BillRequest();
		newRequest.setAmountPerBill((float) 34.5);
		newRequest.setRecipientIBAN("GR100202503111546003");
		newRequest.setSenderIBAN("GR200202503131719401");
		newRequest.setPerMonths(1);
		newRequest.setDayOfIssue(2);
		newRequest.setNumOfIssues(5);
		billManager.createBill(newRequest);
		accManager.updateDB();
		billManager.updateDB();
	}
	
	

}
