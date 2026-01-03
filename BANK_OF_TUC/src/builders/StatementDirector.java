package builders;

import java.util.List;

import backend.BillPayment;
import backend.Deposit;
import backend.Interaction;
import backend.InteractionStatus;
import backend.InteractionType;
import backend.Statement;
import backend.Transfer;
import backend.Withdrawal;
import backend.calendar.ConcreteCalendar;

public class StatementDirector {

	public StatementDirector() {
	}
	
	public Statement createStatement(StatementBuilder builder, Interaction interaction, ConcreteCalendar calendar) {
		if(interaction instanceof Transfer)
			return createTransferStatement(builder, (Transfer) interaction, calendar);
		if(interaction instanceof BillPayment)
			return createBillStatement(builder, (BillPayment) interaction, calendar);
		if(interaction instanceof Withdrawal)
			return createWithdrawalStatement(builder, (Withdrawal) interaction, calendar);
		if(interaction instanceof Deposit)
			return createDepositStatement(builder, (Deposit) interaction, calendar);
			
		throw new IllegalArgumentException("Invalid transaction type.");
	}
	
	public Statement createStatementFromFile(StatementBuilder builder, List<String> statementInfo) {
		if(statementInfo.get(0).equals(InteractionType.TRANSFER.name()))
			builder.setType(InteractionType.TRANSFER);
		else if(statementInfo.get(0).equals(InteractionType.BILLPAYMENT.name())) {
			builder.setType(InteractionType.BILLPAYMENT)
				.setRfCode(statementInfo.get(4));
		}
		else if(statementInfo.get(0).equals(InteractionType.DEPOSIT.name()))
			builder.setType(InteractionType.DEPOSIT);
		else if(statementInfo.get(0).equals(InteractionType.WITHDRAWAL.name()))
			builder.setType(InteractionType.WITHDRAWAL);
		
		if(statementInfo.get(1).equals(InteractionStatus.SUCCESFUL.name()))
			builder.setStatus(InteractionStatus.SUCCESFUL);
		else if(statementInfo.get(1).equals(InteractionStatus.PENDING.name()))
			builder.setStatus(InteractionStatus.PENDING);
		else if(statementInfo.get(1).equals(InteractionStatus.FAILED.name()))
			builder.setStatus(InteractionStatus.FAILED);
		
		builder.setReceiverIban(statementInfo.get(2))
			.setSenderIban(statementInfo.get(3))
			.setDate(statementInfo.get(5))
			.setTime(statementInfo.get(6))
			.setReasoning(statementInfo.get(7))
			.setID(Long.parseLong(statementInfo.get(8)));
		
		return builder.build();
	}
	
	public Statement createTransferStatement(StatementBuilder builder, Transfer transfer, ConcreteCalendar calendar) {
		builder.setType(InteractionType.TRANSFER)
			.setStatus(transfer.getStatus())
			.setAmount(transfer.getAmount())
			.setReceiverIban(transfer.getReceiverIBAN())
			.setSenderIban(transfer.getSenderIBAN())
			.setDate(calendar.curDateString())
			.setTime(calendar.curTimeString());
		
		if(transfer.getReason() != null)
			builder.setReasoning(transfer.getReason());
		
		return builder.build();
	}
	
	public Statement createBillStatement(StatementBuilder builder, BillPayment billPayment, ConcreteCalendar calendar) {
		builder.setType(InteractionType.BILLPAYMENT)
			.setStatus(billPayment.getStatus())
			.setRfCode(billPayment.getRfCode())
			.setAmount(billPayment.getAmount())
			.setReceiverIban(billPayment.getReceiverIBAN())
			.setSenderIban(billPayment.getSenderIBAN())
			.setDate(calendar.curDateString())
			.setTime(calendar.curTimeString());
		
		return builder.build();
	}
	
	public Statement createWithdrawalStatement(StatementBuilder builder, Withdrawal withdrawal, ConcreteCalendar calendar) {
		builder.setType(InteractionType.WITHDRAWAL)
			.setAmount(withdrawal.getAmount())
			.setDate(calendar.curDateString())
			.setTime(calendar.curTimeString());
		
		return builder.build();
	}
	
	public Statement createDepositStatement(StatementBuilder builder, Deposit deposit, ConcreteCalendar calendar) {
		builder.setType(InteractionType.DEPOSIT)
		.setAmount(deposit.getAmount())
		.setDate(calendar.curDateString())
		.setTime(calendar.curTimeString());
	
	return builder.build();
	}

}
