package backend;

public class Statement {
	private InteractionType type;
	private InteractionStatus status;
	private boolean isStandingOrder;
	private String senderIBAN;
	private String receiverIBAN;
	private String rfCode;
	private String date;
	private String time;
	private String reasoning;
	

	public Statement(InteractionType type, InteractionStatus status, boolean isStandingOrder, String senderIBAN,
			String receiverIBAN, String rfCode, String date, String time, String reasoning) {
		super();
		this.type = type;
		this.status = status;
		this.isStandingOrder = isStandingOrder;
		this.senderIBAN = senderIBAN;
		this.receiverIBAN = receiverIBAN;
		this.rfCode = rfCode;
		this.date = date;
		this.time = time;
		this.reasoning = reasoning;
	}

	public InteractionType getType() {
		return type;
	}

	public void setType(InteractionType type) {
		this.type = type;
	}

	public InteractionStatus getStatus() {
		return status;
	}

	public void setStatus(InteractionStatus status) {
		this.status = status;
	}

	public boolean isStandingOrder() {
		return isStandingOrder;
	}

	public void setStandingOrder(boolean isStandingOrder) {
		this.isStandingOrder = isStandingOrder;
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
