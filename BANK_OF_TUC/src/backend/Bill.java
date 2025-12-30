package backend;

public class Bill extends Interaction {
	
	private float amountPerBill;
	private int remainingIssues;
	private String rfCode;
	private String dateIssued;
	private String dueDate;
	private String nextDateIssued;
	private String billCode;
	private int issueFrequency;
	
	public Bill() {
		// TODO Auto-generated constructor stub
	}
	
	public String getDueDate() {
		return dueDate;
	}




	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}




	public String getRfCode() {
		return rfCode;
	}



	public void setRfCode(String rfCode) {
		this.rfCode = rfCode;
	}



	public float getAmountPerBill() {
		return amountPerBill;
	}

	public void setAmountPerBill(float amountPerBill) {
		this.amountPerBill = amountPerBill;
	}

	public int getRemainingIssues() {
		return remainingIssues;
	}

	public void setRemainingIssues(int remainingIssues) {
		this.remainingIssues = remainingIssues;
	}

	public String getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(String dateIssued) {
		this.dateIssued = dateIssued;
	}

	public String getNextDateIssued() {
		return nextDateIssued;
	}

	public void setNextDateIssued(String nextDateIssued) {
		this.nextDateIssued = nextDateIssued;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public int getIssueFrequency() {
		return issueFrequency;
	}

	public void setIssueFrequency(int issueFrequency) {
		this.issueFrequency = issueFrequency;
	}
	
	

}
