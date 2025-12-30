package builders;

import backend.Bill;
import backend.InteractionStatus;

public abstract class BillBuilder {
	
	protected float amountPerBill;
	protected String rfCode;
	protected String receiverIBAN;
	protected String senderIBAN;
	protected String dateIssued;
	protected String dueDate;
	protected String billCode;
	protected Integer issueFrequency;
	protected Integer remainingIssues;
	protected String nextDateIssued;
	protected InteractionStatus status;
	
	protected BillBuilder setAmountPerBill(float amountPerBill) {
		this.amountPerBill = amountPerBill;
		return this;
	}
	protected BillBuilder setRemainingIssues(int remainingIssues) {
		this.remainingIssues = remainingIssues;
		return this;
	}
	protected BillBuilder setRfCode(String rfCode) {
		this.rfCode = rfCode;
		return this;
	}
	protected BillBuilder setDateIssued(String dateIssued) {
		this.dateIssued = dateIssued;
		return this;
	}
	protected BillBuilder setDueDate(String dueDate) {
		this.dueDate = dueDate;
		return this;
	}
	protected BillBuilder setNextDateIssued(String nextDateIssued) {
		this.nextDateIssued = nextDateIssued;
		return this;
	}
	protected BillBuilder setBillCode(String billCode) {
		this.billCode = billCode;
		return this;
	}
	protected BillBuilder setIssueFrequency(int issueFrequency) {
		this.issueFrequency = issueFrequency;
		return this;
	}
	
	public void setStatus(InteractionStatus status) {
		this.status = status;
	}
	protected abstract Bill build();
	
	

}
