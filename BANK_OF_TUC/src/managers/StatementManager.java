package managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import backend.Interaction;
import backend.Statement;
import backend.calendar.ConcreteCalendar;
import dataAccessObjects.StatementDAO;
import factories.DAOFactory;
import builders.StatementBuilder;
import builders.StatementDirector;

public class StatementManager {
	private static StatementManager instance;
	private StatementDAO statementDao;
	private HashMap<Long, Statement> statements;
	private long statementIDCounter;
	
	private StatementManager() {
		this.statementDao = (StatementDAO) DAOFactory.createDAO("statements");
		this.statements = new HashMap<Long,Statement>();
		this.statementIDCounter = 0;
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
	
	public void updateID(long parsedID) {
		if(parsedID>this.statementIDCounter)
			statementIDCounter = parsedID +1;
	}
	
	public void loadStatement(List<String> statementInfo) {
		StatementBuilder builder = new StatementBuilder();
		StatementDirector director = new StatementDirector();
		Statement loadedStatement = director.createStatementFromFile(builder, statementInfo);
		updateID(loadedStatement.getID());
		statements.put(loadedStatement.getID(), loadedStatement);
	}
	
	public void issueStatement(Interaction interaction, ConcreteCalendar calendar) {
		StatementBuilder statementBuilder = new StatementBuilder();
		StatementDirector director = new StatementDirector();
		Statement newStatement = director.createStatement(statementBuilder, interaction, calendar);
		statements.put(newStatement.getID(), newStatement);
		statementIDCounter++;
		statementDao.logStatement(newStatement);
	}
	

}
