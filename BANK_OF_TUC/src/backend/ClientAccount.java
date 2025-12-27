package backend;

import java.util.List;

public abstract class ClientAccount extends Account{
	
	String accountIBAN;
	int balance;
	List<Bill> pendingBills;
	List<Transaction> transactionHistory; //change to statements
	List<StandingOrder> standingOrders;
	
	public ClientAccount() {
		// TODO Auto-generated constructor stub
	}

}
