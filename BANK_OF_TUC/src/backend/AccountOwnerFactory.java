package backend;

import java.util.List;

public class AccountOwnerFactory {

	public static AccountOwner createAccountOwner(List<String> personalInfo) {
		String accountType = personalInfo.get(0);
		if(accountType.equals("INDIVIDUAL")) {
			Individual newIndividual = new Individual();
			newIndividual.name = personalInfo.get(1);
			newIndividual.AFM = personalInfo.get(4);
		}
		if(accountType.equals("BUSINESS")) {
			Business newBusiness = new Business();
			newBusiness.name = personalInfo.get(1);
			newBusiness.AFM = personalInfo.get(4);
		}
		return null;
	}
}
