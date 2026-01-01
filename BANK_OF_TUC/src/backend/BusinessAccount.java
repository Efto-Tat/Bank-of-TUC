package backend;

import java.util.HashMap;

public class BusinessAccount extends ClientAccount {
	private float monthlyFee;
	private HashMap<String,Bill> issuedBills;
	private HashMap<String,BillPayment> issuedPayments;
	
	public BusinessAccount() {
		super();
		this.issuedBills = new HashMap<String,Bill>();
		this.issuedPayments = new HashMap<String,BillPayment>();
	}
	
	public HashMap<String, BillPayment> getIssuedPayments() {
		return issuedPayments;
	}

	public HashMap<String,Bill> getIssuedBills(){
		return this.issuedBills;
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
