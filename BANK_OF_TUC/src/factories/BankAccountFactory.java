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
			newBusiness.setAccountIBAN(accDetails.get(0));
			newBusiness.setInterestRate(Float.parseFloat(accDetails.get(2)));
			newBusiness.setBalance(Float.parseFloat(accDetails.get(3)));
			newBusiness.setMonthlyFee(Float.parseFloat(accDetails.get(4)));
			newBusiness.setOwner(user);
			((Business) user).setAccount(newBusiness);
			return newBusiness;
		}
		else if(user instanceof Individual) {
			IndividualAccount newIndividualAcc = new IndividualAccount();
			newIndividualAcc.setAccountIBAN(accDetails.get(0));
			newIndividualAcc.setInterestRate(Float.parseFloat(accDetails.get(2)));
			newIndividualAcc.setBalance(Float.parseFloat(accDetails.get(3)));
			((Individual) user).getAccounts().put(newIndividualAcc.getAccountIBAN(), newIndividualAcc);
			if(accDetails.size()>4) {
				String secHolders = accDetails.get(4);
				String[] splitHolders = secHolders.split(" ");
				for(int i=0; i<splitHolders.length; i++) {
	  				Individual secHolder = (Individual) userDatabase.get(splitHolders[i]);
	  				System.out.println("Added Secondary Holder: "+secHolder.getName()+" "+secHolder.getAFM());
	  				newIndividualAcc.getSecondaryHolders().put(secHolder.getAFM(),((Individual)secHolder));
	  			}
			}
			newIndividualAcc.setOwner(user);
  			return newIndividualAcc;
		}
		
		throw new IllegalArgumentException("Unknown account type."); 
	}
	
	

}
