package builders;

import backend.Bill;

public class BillBuilder {
	private Bill bill = new Bill();
	
	public Bill build() {
		return this.bill;
	}
	
	public BillBuilder setSenderIBAN(String senderIBAN) {
		bill.setSenderIBAN(senderIBAN);
		return this;
	}
	
	public BillBuilder setReceiverIBAN(String receiverIBAN) {
		bill.setReceiverIBAN(receiverIBAN);
		return this;
	}
	
	public BillBuilder setTotalAmount(float totalOwedAmount) {
		bill.setTotalAmountOwed(totalOwedAmount);
		return this;
	}
	

	public BillBuilder setRfCode(String rfCode) {
		bill.setRfCode(rfCode);
		return this;
	}

	public BillBuilder setAmountPerBill(float amountPerBill) {
		bill.setAmountPerBill(amountPerBill);
		return this;
	}

	public BillBuilder setRemainingIssues(Integer remainingIssues) {
		bill.setRemainingIssues(remainingIssues);
		return this;
	}

	public BillBuilder setIssueFrequency(int issueFrequency) {
		bill.setIssueFrequency(issueFrequency);
		return this;
	}
	
	public BillBuilder setDayOfIssue(int dayOfIssue) {
		bill.setDayOfIssue(dayOfIssue);
		return this;
	}
	
}
