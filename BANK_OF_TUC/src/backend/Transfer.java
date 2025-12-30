package backend;

public class Transfer extends Transaction {
	private String reason;
	private String senderAFM;
	
	
	public Transfer() {
		// TODO Auto-generated constructor stub
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getSenderAFM() {
		return senderAFM;
	}


	public void setSenderAFM(String senderAFM) {
		this.senderAFM = senderAFM;
	}
	
	

}
