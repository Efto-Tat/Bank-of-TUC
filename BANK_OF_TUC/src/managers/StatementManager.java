package managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import backend.Statement;
import backend.Transaction;
import dataAccessObjects.StatementDAO;
import factories.DAOFactory;
import builders.StatementBuilder;
import builders.StatementDirector;

public class StatementManager {
	private static StatementManager instance;
	private StatementDAO statementDao;
	
	private StatementManager() {
		this.statementDao = (StatementDAO) DAOFactory.createDAOFactory("statements");
	}
	
	public static StatementManager getStatementManager() {
		if(instance == null) {
			instance = new StatementManager();
		}
		return instance;
	}
	
	public void loadHistories() {
		try {
			List<List<String>> statementList = readStatements();
			int rows = statementList.size();
			int index=rows-1;
			while(index>=0) {
				loadStatement(statementList.get(index));
				index--;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<List<String>> readStatements() throws FileNotFoundException, IOException{
		return statementDao.loadInfo();
	}
	
	public void loadStatement(List<String> statementInfo) {
		StatementBuilder statementBuilder = new StatementBuilder();
		StatementDirector director = new StatementDirector();
		
	}
	
	public void issueStatement(Transaction transaction) {
		StatementBuilder statementBuilder = new StatementBuilder();
		StatementDirector director = new StatementDirector();
		Statement newStatement = director.createStatement(statementBuilder, transaction);
		statementDao.logStatement(newStatement);
	}
	

}
