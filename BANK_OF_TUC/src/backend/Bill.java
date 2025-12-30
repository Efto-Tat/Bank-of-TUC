package backend;

import java.util.HashMap;

public class Bill extends RepeatedPayment {
	private float amountPerBill;
	private int remainingIssues;
	private String rfCode;
	private String nextIssueDate;
	private int issueFrequency;
	private HashMap<String,BillPayment> issues;
	
	public Bill() {
		this.issues = new HashMap<String,BillPayment>();
	}
	
	public HashMap<String, BillPayment> getIssues() {
		return issues;
	}

	public void setIssues(HashMap<String, BillPayment> issues) {
		this.issues = issues;
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

	public String getNextIssueDate() {
		return nextIssueDate;
	}

	public void setNextIssueDate(String nextIssueDate) {
		this.nextIssueDate = nextIssueDate;
	}

	public int getIssueFrequency() {
		return issueFrequency;
	}

	public void setIssueFrequency(int issueFrequency) {
		this.issueFrequency = issueFrequency;
	}
	
	

}
