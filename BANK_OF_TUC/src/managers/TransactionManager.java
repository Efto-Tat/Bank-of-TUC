package managers;

import commands.TransactionCommand;

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
	
	public void executeTransaction(TransactionCommand command) {
		command.execute();
	}
	
	
	
	
}
