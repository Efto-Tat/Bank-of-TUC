package backend;

public abstract class Transaction extends Interaction {
	private String senderIBAN;
	private String receiverIBAN;
	private float amount;
	private InteractionStatus status;
	private String date;
	private String time;
	
	public Transaction() {
		
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



	public float getAmount() {
		return amount;
	}



	public void setAmount(float amount) {
		this.amount = amount;
	}



	public InteractionStatus getStatus() {
		return status;
	}



	public void setStatus(InteractionStatus status) {
		this.status = status;
	}



	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
}
