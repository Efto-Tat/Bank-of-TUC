package backend;

public class Statement {
	private InteractionStatus status;
	private InteractionType type;
	private float amount;
	private float balanceAfter;
	private String senderIBAN;
	private String receiverIBAN;
	private String rfCode;
	private long ID;
	private String date;
	private String time;
	private String reasoning;


	public Statement() {
	}
	
	
	public long getID() {
		return ID;
	}



	public void setID(long iD) {
		ID = iD;
	}



	public float getBalanceAfter() {
		return balanceAfter;
	}



	public void setBalanceAfter(float balanceAfter) {
		this.balanceAfter = balanceAfter;
	}



	public InteractionType getType() {
		return type;
	}

	public void setType(InteractionType type) {
		this.type = type;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getAmount() {
		return amount;
	}

	public InteractionStatus getStatus() {
		return status;
	}

	public void setStatus(InteractionStatus status) {
		this.status = status;
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

	public String getRfCode() {
		return rfCode;
	}

	public void setRfCode(String rfCode) {
		this.rfCode = rfCode;
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

	public String getReasoning() {
		return reasoning;
	}

	public void setReasoning(String reasoning) {
		this.reasoning = reasoning;
	}
	
}
