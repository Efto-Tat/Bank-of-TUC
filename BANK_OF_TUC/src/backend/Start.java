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
		accManager.updateDB();
		billManager.updateDB();
	}
	
	

}
