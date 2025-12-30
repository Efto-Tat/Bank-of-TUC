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
		addMoney(account,amount);
		//StatementManager.getStatementManager();
		//ISSUE A STATEMENT FOR THE DEPOSIT!
	}
	
	public void withdraw(ClientAccount account, float amount) {
		checkForIllegalAmount(amount);
		float oldBalance = account.getBalance();
		account.setBalance(oldBalance-amount);
		//ISSUE A STATEMENT FOR THE WITHDRAWAL!!!
	}
	
	public void transfer(ClientAccount sender, ClientAccount receiver, float amount) {
		subtractMoney(sender, amount);
		addMoney(receiver,amount);
		//ISSUE A STATEMENT FOR THE STRANSFER!!!
	}
	
	public void subtractMoney(ClientAccount account, float amount) {
		checkForIllegalAmount(amount);
		float oldBalance = account.getBalance();
		checkForSufficientBalance(oldBalance,amount);
		account.setBalance(oldBalance-amount);
	}
	
	public void addMoney(ClientAccount account, float amount) {
		checkForIllegalAmount(amount);
		float oldBalance = account.getBalance();
		account.setBalance(oldBalance+amount);
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
	
	public void checkForIllegalAmount(float amount) {
		if(amount<=0)
			throw new IllegalArgumentException("The amount must be positive.");
	}
	
	public void checkForSufficientBalance(float balance, float amount) {
		if(balance<amount)
			throw new  IllegalStateException("Insufficient Balance.");
	}
	
	public void updateDB() {
		accDao.updateDatabase(accounts);
	}

	

}
