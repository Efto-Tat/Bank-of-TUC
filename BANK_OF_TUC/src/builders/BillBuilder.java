package builders;

import backend.Bill;

public class BillBuilder {
	private Bill bill = new Bill();
	
	public Bill build() {
		return this.bill;
	}

	public BillBuilder setRfCode(String rfCode) {
		bill.setRfCode(rfCode);
		return this;
	}

	public BillBuilder setAmountPerBill(float amountPerBill) {
		bill.setAmountPerBill(amountPerBill);
		return this;
	}

	public BillBuilder setRemainingIssues(int remainingIssues) {
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
