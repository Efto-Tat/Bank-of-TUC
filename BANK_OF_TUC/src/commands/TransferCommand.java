package commands;

import backend.AdminAccount;
import backend.TransferRequest;
import backend.ClientAccount;
import backend.Transfer;
import managers.AccountManager;
import managers.TransactionManager;

public class TransferCommand implements TransactionCommand{
	
	private TransferRequest request;
	
	public TransferCommand(TransferRequest request) {
		this.request = request;
	}

	@Override
	public void execute() {
		if(AccountManager.getAccountManager().getAccounts().get(request.getReceiverIBAN()) == null) {
			throw new IllegalArgumentException("Invalid IBAN!");
		}
		ClientAccount sender = (ClientAccount) request.getSender();
		ClientAccount receiver = (ClientAccount) AccountManager.getAccountManager().getAccounts().get(request.getReceiverIBAN());
		Transfer newTransfer = new Transfer();
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
