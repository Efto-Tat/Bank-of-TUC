package commands;

public interface TransactionCommand {
	
	public void execute();
	
	public void undo();
	
}
