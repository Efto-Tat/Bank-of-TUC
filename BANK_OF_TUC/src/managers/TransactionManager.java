package managers;

import backend.ClientAccount;
import builders.TransactionBuilder;

public class TransactionManager {
	private static TransactionManager instance;
	
	
	private TransactionManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static TransactionManager getTransactionManager() {
		if(instance == null)
			instance = new TransactionManager();
		return instance;
	}
	
	
	
	
}
