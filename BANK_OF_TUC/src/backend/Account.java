package backend;

public abstract class Account {
	AccountOwner owner;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}


	public AccountOwner getOwner() {
		return owner;
	}

	public void setOwner(AccountOwner owner) {
		this.owner = owner;
	}
	
	

}
