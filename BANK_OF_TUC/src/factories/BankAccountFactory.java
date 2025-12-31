package factories;

import java.util.HashMap;
import java.util.List;

import backend.Account;
import backend.AccountOwner;
import backend.Admin;
import backend.AdminAccount;
import backend.Business;
import backend.BusinessAccount;
import backend.Individual;
import backend.IndividualAccount;
import managers.UserManager;

public class BankAccountFactory {

	public BankAccountFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public Account createBankAccount(List<String> accDetails, HashMap<String, AccountOwner> userDatabase) {
		AccountOwner user = userDatabase.get(accDetails.get(1));
		
		//if(user instanceof Admin) {
		//	AdminAccount newAdmin = new AdminAccount();
		//	
		//}
		if(user instanceof Business) {
			BusinessAccount newBusiness = new BusinessAccount();
			newBusiness.setUsername(accDetails.get(2));
			newBusiness.setAccountIBAN(accDetails.get(0));
			newBusiness.setPassword(accDetails.get(3));
			newBusiness.setInterestRate(Float.parseFloat(accDetails.get(4)));
			newBusiness.setBalance(Float.parseFloat(accDetails.get(5)));
			newBusiness.setMonthlyFee(Float.parseFloat(accDetails.get(6)));
			newBusiness.setOwner(user);
			((Business) user).setAccount(newBusiness);
			return newBusiness;
		}
		else if(user instanceof Individual) {
			IndividualAccount newIndividual = new IndividualAccount();
			newIndividual.setUsername(accDetails.get(2));
			newIndividual.setAccountIBAN(accDetails.get(0));
			newIndividual.setPassword(accDetails.get(3));
			newIndividual.setInterestRate(Float.parseFloat(accDetails.get(4)));
			newIndividual.setBalance(Float.parseFloat(accDetails.get(5)));
			newIndividual.setPrimaryHolder((Individual) user);
			((Individual) user).getAccounts().put(newIndividual.getAccountIBAN(), newIndividual);
			if(accDetails.size()>6) {
				String secHolders = accDetails.get(6);
				String[] splitHolders = secHolders.split(" ");
				
				for(int i=0; i<splitHolders.length; i++) {
	  				AccountOwner secHolder = userDatabase.get(splitHolders[i]);
	  				System.out.println("Added Secondary Holder: "+secHolder.getName()+" "+secHolder.getAFM());
	  				newIndividual.getSecondaryHolders().put(secHolder.getAFM(),((Individual)secHolder));
	  			}
			}
			newIndividual.setOwner(user);
  			return newIndividual;
		}
		
		throw new IllegalArgumentException("Unknown account type."); 
	}
	
	

}
