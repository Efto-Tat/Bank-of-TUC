package builders;

import backend.InteractionStatus;
import backend.Transfer;

public class TransferBuilder {
	private Transfer transfer = new Transfer();
	
	public Transfer build() {
		return transfer;
	}
	
	public TransferBuilder setReason(String reason) {
		this.transfer.setReason(reason);
		return this;
	}
	

	public TransferBuilder setSenderIBAN(String senderIBAN) {
		this.transfer.setSenderIBAN(senderIBAN);
		return this;
	}


	public TransferBuilder setReceiverIBAN(String receiverIBAN) {
		this.transfer.setReceiverIBAN(receiverIBAN);
		return this;
	}


	public TransferBuilder setAmount(float amount) {
		this.transfer.setAmount(amount);
		return this;
	}


	public TransferBuilder setStatus(InteractionStatus status) {
		this.transfer.setStatus(status);
		return this;
	}
	

	public TransferBuilder setDate(String date) {
		this.transfer.setDate(date);
		return this;
	}
	

	public TransferBuilder setTime(String time) {
		this.transfer.setTime(time);
		return this;
	}
}
