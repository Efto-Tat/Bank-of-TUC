package backend;

import java.util.HashMap;

public class Individual extends Client {
	String dateOfBirth;
	HashMap<String,IndividualAccount> accounts;
	
	public Individual() {
		this.accounts = new HashMap<String,IndividualAccount>();
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public HashMap<String, IndividualAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(HashMap<String, IndividualAccount> accounts) {
		this.accounts = accounts;
	}
	

}
