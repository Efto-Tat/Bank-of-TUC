package dataAccessObjects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DAO {
	
	protected String fileName;
	
	public List<List<String>> loadInfo() throws FileNotFoundException, IOException {
		List<List<String>> readInfo = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(this.fileName+".csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        readInfo.add(Arrays.asList(values));
		    }
		}
		return readInfo;
	}
}
