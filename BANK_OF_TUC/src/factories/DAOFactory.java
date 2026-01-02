package factories;

import dataAccessObjects.BankAccountDAO;
import dataAccessObjects.BillDAO;
import dataAccessObjects.BillPaymentDAO;
import dataAccessObjects.BillStandingOrderDAO;
import dataAccessObjects.DAO;
import dataAccessObjects.StatementDAO;
import dataAccessObjects.TransferStandingOrderDAO;
import dataAccessObjects.UserDAO;

public abstract class DAOFactory {
	
	public static DAO createDAO(String fileName) {
		if(fileName.equals("users")) {
			UserDAO usersAccess = new UserDAO();
			return usersAccess;
		}
		if(fileName.equals("bills")) {
			BillDAO billsAccess = new BillDAO();
			return billsAccess;
		}
		if(fileName.equals("transferStandingOrders")) {
			TransferStandingOrderDAO transferStandingOrderDao = new TransferStandingOrderDAO();
			return transferStandingOrderDao;
		}
		if(fileName.equals("billStandingOrders")) {
			BillStandingOrderDAO billStandingOrderDao = new BillStandingOrderDAO();
			return billStandingOrderDao;
		}
		if(fileName.equals("bankAccounts")) {
			BankAccountDAO bankAccountsAccess = new BankAccountDAO();
			return bankAccountsAccess;
		}
		if(fileName.equals("statements")) {
			StatementDAO statementsAccess = new StatementDAO();
			return statementsAccess;
		}
		if(fileName.equals("issuedBills")) {
			BillPaymentDAO billPaymentsAccess = new BillPaymentDAO();
			return billPaymentsAccess;
		}
		throw new RuntimeException("Unknown file.");
	}
}
