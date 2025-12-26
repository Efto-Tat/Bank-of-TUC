package backend;

public class ConcreteAccManager implements AccountManager {
	static private AccountManager instance;
	
	private ConcreteAccManager() {
		// TODO Auto-generated constructor stub
	}

	public AccountManager getAccountManager() {
		
		return instance;
	}


}
