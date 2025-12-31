package builders;

import backend.BillPayment;
import backend.Deposit;
import backend.Interaction;
import backend.InteractionType;
import backend.Statement;
import backend.Transfer;
import backend.Withdrawal;

public class StatementDirector {

	public StatementDirector() {
	}
	
	public Statement createStatement(StatementBuilder builder, Interaction interaction) {
		if(interaction instanceof Transfer)
			return createTransferStatement(builder, (Transfer) interaction);
		if(interaction instanceof BillPayment)
			return createBillStatement(builder, (BillPayment) interaction);
		if(interaction instanceof Withdrawal)
			return createWithdrawalStatement(builder, (Withdrawal) interaction);
		if(interaction instanceof Deposit)
			return createDepositStatement(builder, (Deposit) interaction);
			
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
	
	public Statement createWithdrawalStatement(StatementBuilder builder, Withdrawal withdrawal) {
		builder.setType(InteractionType.WITHDRAWAL)
			.setAmount(withdrawal.getAmount())
			.setDate(withdrawal.getDate())
			.setTime(withdrawal.getTime());
		
		return builder.build();
	}
	
	public Statement createDepositStatement(StatementBuilder builder, Deposit deposit) {
		builder.setType(InteractionType.DEPOSIT)
		.setAmount(deposit.getAmount())
		.setDate(deposit.getDate())
		.setTime(deposit.getTime());
	
	return builder.build();
	}

}
