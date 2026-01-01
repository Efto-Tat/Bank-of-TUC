package backend;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ClientAccount extends Account{
	
	String accountIBAN;
	float balance;
	float interestRate;
	private HashMap<String,Bill> pendingBills;
	private ArrayList<Statement> history;
	private ArrayList<StandingOrder> standingOrders;
	private HashMap<String,BillPayment> pendingBillPayments;
	
	public ClientAccount() {
		this.pendingBills = new HashMap<String,Bill>();
		this.history = new ArrayList<Statement>();
		this.standingOrders = new ArrayList<StandingOrder>();
		this.pendingBillPayments = new HashMap<String,BillPayment>();
	}
	
	public HashMap<String, BillPayment> getPendingBillPayments() {
		return pendingBillPayments;
	}

	public HashMap<String, Bill> getPendingBills() {
		return pendingBills;
	}


	public ArrayList<Statement> getHistory() {
		return history;
	}



	public ArrayList<StandingOrder> getStandingOrders() {
		return standingOrders;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountIBAN() {
		return accountIBAN;
	}

	public void setAccountIBAN(String accountIBAN) {
		this.accountIBAN = accountIBAN;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float f) {
		this.balance = f;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
	
	public AccountOwner getOwner() {
		return owner;
	}

	public void setOwner(AccountOwner owner) {
		this.owner = owner;
	}

}
