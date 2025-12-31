package backend;

import java.util.HashMap;

public class Bill extends RepeatedPayment {
	private float amountPerBill;
	private int remainingIssues;
	private int dayOfIssue;
	private String rfCode;
	private Integer issueFrequency;
	private float totalAmountOwed;
	private HashMap<String,BillPayment> issues;
	
	public Bill() {
		this.issues = new HashMap<String,BillPayment>();
	}
	
	public int getDayOfIssue() {
		return dayOfIssue;
	}

	public void setDayOfIssue(int dayOfIssue) {
		this.dayOfIssue = dayOfIssue;
	}

	public float getTotalAmountOwed() {
		return totalAmountOwed;
	}

	public void setTotalAmountOwed(float totalAmountOwed) {
		this.totalAmountOwed = totalAmountOwed;
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

	public Integer getIssueFrequency() {
		return issueFrequency;
	}

	public void setIssueFrequency(Integer issueFrequency) {
		this.issueFrequency = issueFrequency;
	}
	
	

}
