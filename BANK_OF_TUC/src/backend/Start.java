package backend;
import java.util.ArrayList;
import java.util.List;

import managers.ConcreteUserManager;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Start {
	
	public static final String delimiter = ",";

	public Start() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ConcreteUserManager userManager = ConcreteUserManager.getUserManager();
		
	}
	
	public static List<List<String>> readFile(String fileName) throws FileNotFoundException, IOException {
		List<List<String>> readInfo = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName+".csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(delimiter);
		        readInfo.add(Arrays.asList(values));
		    }
		}
		return readInfo;
	}
	
	public static void addUsers(List<List<String>> userAccounts) {
		int rows = userAccounts.size();
		int index=rows-1;
		while(index>=0) {
			List<String> userInfo = userAccounts.get(index);
			
		}
	}

}
