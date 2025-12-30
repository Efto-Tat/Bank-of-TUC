package managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import backend.Account;
import backend.ClientAccount;
import dataAccessObjects.BankAccountDAO;
import factories.BankAccountFactory;
import factories.DAOFactory;

public class AccountManager {
	private BankAccountFactory accFactory;
	private static AccountManager instance;
	private BankAccountDAO accDao;
	private HashMap<String,Account> accounts;
	
	private AccountManager() {
		this.accDao = (BankAccountDAO) DAOFactory.createDAOFactory("bankAccounts");
		this.accFactory = new BankAccountFactory();
		this.accounts = new HashMap<String, Account>();
	}
	
	
	
	public BankAccountDAO getAccDao() {
		return accDao;
	}

	public static AccountManager getAccountManager() {
		if(instance == null) {
			instance = new AccountManager();
			return instance;
		}
		else
			return instance;
	}
	
	public void deposit(ClientAccount account, float amount) {
		float oldBalance = account.getBalance();
		account.setBalance(oldBalance+amount);
		StatementManager.getStatementManager();
	}

	public HashMap<String, Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(HashMap<String, Account> accounts) {
		this.accounts = accounts;
	}
	
	public void loadAccounts() {
		try {
			List<List<String>> accountList = readAccounts();
			int rows = accountList.size();
			int index=rows-1;
			while(index>=0) {
				addAccount(accountList.get(index));
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
	
	public List<List<String>> readAccounts() throws FileNotFoundException, IOException {
		return accDao.loadInfo();
	}
	
	public void addAccount(List<String> accDetails) {
		Account newAcc = accFactory.createBankAccount(accDetails, UserManager.getUserManager().getUsers());
		accounts.put(((ClientAccount) newAcc).getAccountIBAN(), newAcc);
		System.out.println("Added account: "+newAcc.getUsername()+" "+((ClientAccount) newAcc).getAccountIBAN());
	}
	
	public void updateDB() {
		accDao.updateDatabase(accounts);
	}

	

}
