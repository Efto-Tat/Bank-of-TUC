package dataAccessObjects;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import backend.Statement;


public class StatementDAO extends DAO{

	public StatementDAO() {
		this.fileName = "statements";
	}
	
	public void logStatement(Statement statement) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("statements.csv",true))){
			bw.newLine();
			bw.write(statement.getType().name());
			bw.write(",");
			bw.write(statement.getStatus().name());
			bw.write(",");
			if(statement.isStandingOrder()) {
				bw.write("standingOrder");
				bw.write(",");
			}
			bw.write(statement.getSenderIBAN());
			bw.write(",");
			bw.write(statement.getReceiverIBAN());
			bw.write(",");
			if(statement.getRfCode() != null) {
				bw.write(statement.getRfCode());
				bw.write(",");
			}
			bw.write(statement.getDate());
			bw.write(",");
			bw.write(statement.getTime());
			bw.write(",");
			bw.write(statement.getReasoning());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
