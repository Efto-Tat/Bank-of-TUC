package bank.of.tuc;

import java.util.List;

public abstract class ClientAccount extends Account{
	
	String accountIBAN;
	int balance;
	List<Bill> pendingBills;
	List<Transaction> transactionHistory;
	List<StandingOrder> standingOrders;
	
	public ClientAccount() {
		// TODO Auto-generated constructor stub
	}

}
