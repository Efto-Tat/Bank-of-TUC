package factories;

import java.util.List;

import backend.AccountOwner;
import backend.Admin;
import backend.Business;
import backend.Individual;

public class AccountOwnerFactory {

	public static AccountOwner createAccountOwner(List<String> personalInfo) {
		String accountType = personalInfo.get(0);
		if(accountType.equals("INDIVIDUAL")) {
			Individual newIndividual = new Individual();
			newIndividual.setName(personalInfo.get(1));
			newIndividual.setAFM(personalInfo.get(2));
			return newIndividual;
		}
		if(accountType.equals("BUSINESS")) {
			Business newBusiness = new Business();
			newBusiness.setName(personalInfo.get(1));
			newBusiness.setAFM(personalInfo.get(2));
			return newBusiness;
		}
		if(accountType.equals("ADMIN")) {
			Admin newAdmin = new Admin();
			newAdmin.setName(personalInfo.get(1));
			newAdmin.setAFM(personalInfo.get(2));
			return newAdmin;
		}
		throw new IllegalArgumentException("Unknown account type.");
	}
}
