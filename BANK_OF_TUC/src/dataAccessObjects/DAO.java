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
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); //regex
		        readInfo.add(Arrays.asList(values));
		    }
		}
		
		return cleanUp(readInfo);
	}
	
	
	
	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public List<List<String>> cleanUp(List<List<String>> dataMatrix){
			int rows = dataMatrix.size();
			int index=rows-1;
			String buffer;
			while(index>=0) {
				List<String> curRow = dataMatrix.get(index);
				int elements = curRow.size();
				int subindex = elements;
				while(subindex>0) {
					buffer = curRow.get(subindex-1).replace("%", "").replace("€", "").replace(",", "").replace('"', ' ').trim();
					curRow.set(subindex-1, buffer);
					subindex--;
				}
				index--;
			}
			return dataMatrix;
	}
	
}
