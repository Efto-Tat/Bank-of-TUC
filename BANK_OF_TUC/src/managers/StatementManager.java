package managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import backend.Interaction;
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
		this.statementDao = (StatementDAO) DAOFactory.createDAO("statements");
	}
	
	public static StatementManager getStatementManager() {
		if(instance == null) {
			instance = new StatementManager();
		}
		return instance;
	}
	
	public void loadStatements() {
		try {
			List<List<String>> statements = readStatements();
			for(List<String> curStatement : statements) {
				loadStatement(curStatement);
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
		StatementBuilder builder = new StatementBuilder();
		StatementDirector director = new StatementDirector();
		Statement loadedStatement = director.createStatementFromFile(builder, statementInfo);
		
	}
	
	public void issueStatement(Interaction interaction) {
		StatementBuilder statementBuilder = new StatementBuilder();
		StatementDirector director = new StatementDirector();
		Statement newStatement = director.createStatement(statementBuilder, interaction);
		statementDao.logStatement(newStatement);
	}
	

}
