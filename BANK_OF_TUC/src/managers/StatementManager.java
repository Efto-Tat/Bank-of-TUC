package managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import backend.Interaction;
import backend.Statement;
import dataAccessObjects.StatementDAO;
import factories.DAOFactory;
import builders.ConcreteStatementBuilder;
import builders.StatementDirector;

public class StatementManager {
	private ConcreteStatementBuilder statementBuilder;
	private static StatementManager instance;
	private StatementDAO statementDao;
	
	private StatementManager() {
		this.statementDao = (StatementDAO) DAOFactory.createDAOFactory("statements");
		this.statementBuilder = new ConcreteStatementBuilder();
	}
	
	public static StatementManager getStatementManager() {
		if(instance == null) {
			instance = new StatementManager();
			return instance;
		}
		else
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
		ConcreteStatementBuilder statementBuilder = new ConcreteStatementBuilder();
		StatementDirector director = new StatementDirector();
		
	}
	
	public Statement issueStatement(Interaction interaction) {
		
		return null;
	}

}
