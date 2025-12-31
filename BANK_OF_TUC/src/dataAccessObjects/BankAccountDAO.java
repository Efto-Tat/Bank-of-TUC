package dataAccessObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import backend.Account;
import backend.BusinessAccount;
import backend.ClientAccount;
import backend.Individual;
import backend.IndividualAccount;

public class BankAccountDAO extends DAO{

	public BankAccountDAO() {
		this.fileName = "bankAccounts";
	}
	
	public void updateDatabase(HashMap<String,Account> accounts) { //Maybe improve this?
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("bankAccounts.csv"))){
			Collection<Account> bankAccounts = accounts.values();
			bw.write("ACCOUNT IBAN, AFM, USERNAME, PASSWORD, INTEREST, BALANCE, SECONDARY HOLDERS/MONTHLY FEE");
			bw.newLine();
			for(Account curAcc : bankAccounts) {
				bw.write(((ClientAccount) curAcc).getAccountIBAN());
				bw.write(",");
				bw.write(curAcc.getOwner().getAFM());
				bw.write(",");
				bw.write(curAcc.getUsername());
				bw.write(",");
				bw.write(curAcc.getPassword());
				bw.write(",");
				bw.write(formatInterest(Float.toString(((ClientAccount) curAcc).getInterestRate())));
				bw.write(",");
				bw.write(formatBalance(Float.toString(((ClientAccount) curAcc).getBalance())));
				bw.write(",");
				if(curAcc instanceof BusinessAccount) {
					bw.write("€"+((BusinessAccount) curAcc).getMonthlyFee());
				}
				if((curAcc instanceof IndividualAccount) && ((IndividualAccount) curAcc).hasSecHolders()) {
					Collection<Individual> secHolders = ((IndividualAccount) curAcc).getSecondaryHolders().values();
					bw.write(formatSecHolders(secHolders));
				}
				bw.write(",");
				bw.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String formatInterest(String interest) {
		return (interest+"%");
	}
	
	public String formatBalance(String balance) {
		return ('"'+"€"+balance+'"');
	}
	
	public String formatSecHolders(Collection<Individual> secHolders) {
		String buffer = "";
		for(Individual secHolder : secHolders) {
			buffer = buffer+" ";
			if(secHolders.size() == 1) {
				return (secHolder.getAFM());
			}
			buffer = buffer+secHolder.getAFM();
		}
		return ('"'+buffer+'"');
	}
}
