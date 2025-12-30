package builders;

import backend.BillPayment;
import backend.InteractionType;
import backend.Statement;
import backend.Transaction;
import backend.Transfer;

public class StatementDirector {

	public StatementDirector() {
		// TODO Auto-generated constructor stub
	}
	
	public Statement createStatement(StatementBuilder builder, Transaction transaction) {
		if(transaction instanceof Transfer)
			return createTransferStatement(builder, (Transfer) transaction);
		if(transaction instanceof BillPayment)
			return createBillStatement(builder, (BillPayment) transaction);
		throw new IllegalArgumentException("Invalid transaction type.");
	}
	
	public Statement createTransferStatement(StatementBuilder builder, Transfer transfer) {
		builder.setType(InteractionType.TRANSFER)
			.setStatus(transfer.getStatus())
			.setAmount(transfer.getAmount())
			.setReceiverIban(transfer.getReceiverIBAN())
			.setSenderIban(transfer.getSenderIBAN())
			.setDate(transfer.getDate())
			.setTime(transfer.getTime());
		
		if(transfer.getReason() != null)
			builder.setReasoning(transfer.getReason());
		
		return builder.build();
	}
	
	public Statement createBillStatement(StatementBuilder builder, BillPayment billPayment) {
		builder.setType(InteractionType.BILLPAYMENT)
			.setStatus(billPayment.getStatus())
			.setRfCode(billPayment.getRfCode())
			.setAmount(billPayment.getAmount())
			.setReceiverIban(billPayment.getReceiverIBAN())
			.setSenderIban(billPayment.getSenderIBAN())
			.setDate(billPayment.getDate())
			.setTime(billPayment.getTime());
		
		return builder.build();
	}

}
