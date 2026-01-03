package backend;

import java.io.IOException;

import javax.swing.SwingUtilities;

import backend.calendar.ConcreteCalendar;
import gui.BankFrame;
import managers.AccountManager;
import managers.BillManager;
import managers.StandingOrderManager;
import managers.StatementManager;
import managers.TransactionManager;
import managers.UserManager;

public class Start {
	
	public Start() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		UserManager userManager = UserManager.getUserManager();
		AccountManager accManager = AccountManager.getAccountManager();
		BillManager billManager = BillManager.getBillManager();
		StandingOrderManager orderManager = StandingOrderManager.getStandingOrderManager();
		StatementManager statementManager = StatementManager.getStatementManager();
		TransactionManager transactionManager = TransactionManager.getTransactionManager();
		ConcreteCalendar calendar = ConcreteCalendar.getCalendar();
		calendar.loadDate();
		userManager.loadUsers();
		accManager.loadAccounts();
		billManager.initBills();
		orderManager.initStandingOrders();
		statementManager.loadStatements();
		SwingUtilities.invokeLater(BankFrame::new);
		accManager.updateDB();
		billManager.updateDB();
		orderManager.updateDB();
		try {
			calendar.saveDate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			calendar.saveDate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
