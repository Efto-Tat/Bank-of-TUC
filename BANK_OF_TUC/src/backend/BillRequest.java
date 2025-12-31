package backend;

public class BillRequest {
	private String senderIBAN;
	private float amountPerBill;
	private String recipientIBAN;
	private String rfCode;
	private int numOfIssues;
	private int dayOfIssue;
	private int perMonths;
	
	public BillRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public String getRfCode() {
		return rfCode;
	}

	public void setRfCode(String rfCode) {
		this.rfCode = rfCode;
	}

	public String getSenderIBAN() {
		return senderIBAN;
	}

	public void setSenderIBAN(String senderIBAN) {
		this.senderIBAN = senderIBAN;
	}

	public float getAmountPerBill() {
		return amountPerBill;
	}

	public void setAmountPerBill(float amountPerBill) {
		this.amountPerBill = amountPerBill;
	}

	public String getRecipientIBAN() {
		return recipientIBAN;
	}

	public void setRecipientIBAN(String recipientIBAN) {
		this.recipientIBAN = recipientIBAN;
	}

	public int getNumOfIssues() {
		return numOfIssues;
	}

	public void setNumOfIssues(int numOfIssues) {
		this.numOfIssues = numOfIssues;
	}

	public int getDayOfIssue() {
		return dayOfIssue;
	}

	public void setDayOfIssue(int dayOfIssue) {
		this.dayOfIssue = dayOfIssue;
	}

	public int getPerMonths() {
		return perMonths;
	}

	public void setPerMonths(int perMonths) {
		this.perMonths = perMonths;
	}
	
	

}
