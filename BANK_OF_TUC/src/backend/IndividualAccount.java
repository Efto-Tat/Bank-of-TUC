package backend;

import java.util.HashMap;

public class IndividualAccount extends ClientAccount {
	Individual primaryHolder;
	HashMap<String,Individual> secondaryHolders;
	
	public IndividualAccount() {
		this.secondaryHolders = new HashMap<String,Individual>();
	}

	public HashMap<String, Individual> getSecondaryHolders() {
		return secondaryHolders;
	}
	
	public boolean hasSecHolders() {
		if(this.secondaryHolders.isEmpty())
			return false;
		else
			return true;
	}

	public void setSecondaryHolders(HashMap<String, Individual> secondaryHolders) {
		this.secondaryHolders = secondaryHolders;
	}

	public Individual getPrimaryHolder() {
		return primaryHolder;
	}

	public void setPrimaryHolder(Individual primaryHolder) {
		this.primaryHolder = primaryHolder;
	}
	
	
	
	

}
