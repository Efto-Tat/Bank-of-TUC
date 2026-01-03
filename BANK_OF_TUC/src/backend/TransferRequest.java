package backend;

public class TransferRequest {
	private Account sender;
	private String receiverIBAN;
	private float transferAmount;
	private String reasoning;
	
	public TransferRequest(Account sender, String receiverIBAN, float transferAmount, String reasoning) {
		this.sender = sender;
		this.receiverIBAN = receiverIBAN;
		this.transferAmount = transferAmount;
		this.reasoning = reasoning;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public String getReceiverIBAN() {
		return receiverIBAN;
	}

	public void setReceiverIBAN(String receiverIBAN) {
		this.receiverIBAN = receiverIBAN;
	}

	public float getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(float transferAmount) {
		this.transferAmount = transferAmount;
	}

	public String getReasoning() {
		return reasoning;
	}

	public void setReasoning(String reasoning) {
		this.reasoning = reasoning;
	}
	
	
}
