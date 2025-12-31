package backend;

public abstract class RepeatedPayment extends Interaction{
	private String senderIBAN;
	private String receiverIBAN;
	
	public RepeatedPayment() {
		// TODO Auto-generated constructor stub
	}

	public String getSenderIBAN() {
		return senderIBAN;
	}

	public void setSenderIBAN(String senderIBAN) {
		this.senderIBAN = senderIBAN;
	}

	public String getReceiverIBAN() {
		return receiverIBAN;
	}

	public void setReceiverIBAN(String receiverIBAN) {
		this.receiverIBAN = receiverIBAN;
	}
	
	

}
