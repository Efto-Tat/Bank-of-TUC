package factories;

import dataAccessObjects.BankAccountDAO;
import dataAccessObjects.BillDAO;
import dataAccessObjects.DAO;
import dataAccessObjects.StandingOrderDAO;
import dataAccessObjects.UserDAO;

public abstract class DAOFactory {
	
	public static DAO createDAOFactory(String fileName) {
		if(fileName.equals("users")) {
			UserDAO usersAccess = new UserDAO();
			return usersAccess;
		}
		if(fileName.equals("bills")) {
			BillDAO billsAccess = new BillDAO();
			return billsAccess;
		}
		if(fileName.equals("standingOrders")) {
			StandingOrderDAO standOrdersAccess = new StandingOrderDAO();
			return standOrdersAccess;
		}
		if(fileName.equals("bankAccounts")) {
			BankAccountDAO bankAccountsAccess = new BankAccountDAO();
			return bankAccountsAccess;
		}
		throw new RuntimeException("Unknown file.");
	}
}
