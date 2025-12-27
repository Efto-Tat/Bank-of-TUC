package factories;

import java.util.List;

import backend.AccountOwner;
import backend.Business;
import backend.Individual;

public class AccountOwnerFactory {

	public static AccountOwner createAccountOwner(List<String> personalInfo) {
		String accountType = personalInfo.get(0);
		if(accountType.equals("INDIVIDUAL")) {
			Individual newIndividual = new Individual();
			newIndividual.setName(personalInfo.get(1));
			newIndividual.setAFM(personalInfo.get(4));
		}
		if(accountType.equals("BUSINESS")) {
			Business newBusiness = new Business();
			newBusiness.setName(personalInfo.get(1));
			newBusiness.setAFM(personalInfo.get(4));
		}
		return null;
	}
}
