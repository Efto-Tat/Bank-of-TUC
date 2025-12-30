package builders;

import backend.Bill;

public class ConcreteBillBuilder extends BillBuilder{

	@Override
	public Bill build() {
		Bill newBill = new Bill();
		newBill.setAmountPerBill(amountPerBill);
		if(this.remainingIssues != null) {
			newBill.setRemainingIssues(remainingIssues);
		}
		if(this.issueFrequency != null) {
			newBill.setIssueFrequency(issueFrequency);
		}
		newBill.setBillCode(billCode);
		newBill.setDateIssued(dateIssued);
		newBill.setDueDate(dueDate);
		newBill.setNextDateIssued(nextDateIssued);
		newBill.setReceiverIBAN(receiverIBAN);
		newBill.setRfCode(rfCode);
		newBill.setSenderIBAN(senderIBAN);
		newBill.setStatus(status);
		return newBill;
	}

}
