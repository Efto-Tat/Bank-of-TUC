package backend;

import java.util.HashMap;

public class BusinessAccount extends ClientAccount {
	private float monthlyFee;
	private HashMap<String,Bill> issuedBills;
	
	public BusinessAccount() {
		super();
		this.issuedBills = new HashMap<String,Bill>();
	}
	
	public void issueBill() {
		
	}
	
	public String getAccountIBAN() {
		return this.accountIBAN;
	}

	public void setAccountIBAN(String accountIBAN) {
		this.accountIBAN = accountIBAN;
	}

	public float getBalance() {
		return this.balance;
	}

	public void setBalance(float f) {
		this.balance = f;
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

	public float getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(float monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	
}
