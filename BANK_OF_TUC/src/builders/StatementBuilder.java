package builders;

import backend.InteractionStatus;
import backend.InteractionType;
import backend.Statement;

public class StatementBuilder {
	
	private Statement statement = new Statement();
	
	public Statement build() {
		return this.statement;
	}
	
	
	public StatementBuilder setType(InteractionType type) {
		this.statement.setType(type);
		return this;
	}

	public StatementBuilder setReceiverIban(String receiverIban) {
		this.statement.setReceiverIBAN(receiverIban);
		return this;
	}

	public StatementBuilder setSenderIban(String senderIban) {
		this.statement.setSenderIBAN(senderIban);
		return this;
	}

	public StatementBuilder setRfCode(String rfCode) {
		this.statement.setRfCode(rfCode);
		return this;
	}


	public StatementBuilder setReasoning(String reasoning) {
		this.statement.setReasoning(reasoning);
		return this;
	}

	public StatementBuilder setAmount(float amount) {
		this.statement.setAmount(amount);
		return this;
	}


	public StatementBuilder setDate(String date) {
		this.statement.setDate(date);
		return this;
	}


	public StatementBuilder setTime(String time) {
		this.statement.setTime(time);
		return this;
	}

	public StatementBuilder setStatus(InteractionStatus status) {
		this.statement.setStatus(status);
		return this;
	}
	
	public StatementBuilder setBalanceAfter(float balanceAfter) {
		this.statement.setBalanceAfter(balanceAfter);
		return this;
	}
	
	public StatementBuilder setID(long id) {
		this.statement.setID(id);
		return this;
	}
}

