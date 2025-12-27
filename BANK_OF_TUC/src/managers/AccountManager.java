package managers;

import java.util.HashMap;

import backend.Account;
import dataAccessObjects.BankAccountDAO;

public class AccountManager {
	private static AccountManager instance;
	private BankAccountDAO accDao;
	private HashMap<String,Account> accounts;
	
	public AccountManager() {
		// TODO Auto-generated constructor stub
	}
	
	

}
