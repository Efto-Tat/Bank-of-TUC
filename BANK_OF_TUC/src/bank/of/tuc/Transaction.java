package bank.of.tuc;

public abstract class Transaction extends Interaction {
	
	public enum transactionStatus{
		PENDING,
		SUCCESFUL,
		FAILED
	}
	String date;
	String time;
	
	public Transaction() {
		
	}
}
