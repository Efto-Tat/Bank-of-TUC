package builders;

import backend.BillRequest;

public class BillRequestBuilder {
	private BillRequest billRequest = new BillRequest();
	
	public BillRequest build() {
		return billRequest;
	}
	
	public BillRequestBuilder setRfCode(String rfCode) {
		billRequest.setRfCode(rfCode);
		return this;
	}
	
	public BillRequestBuilder setSenderIBAN(String senderIBAN) {
		billRequest.setSenderIBAN(senderIBAN);
		return this;
	}

	public BillRequestBuilder setAmountPerBill(float amountPerBill) {
		billRequest.setAmountPerBill(amountPerBill);
		return this;
	}

	public BillRequestBuilder setRecipientIBAN(String recipientIBAN) {
		billRequest.setRecipientIBAN(recipientIBAN);
		return this;
	}


	public BillRequestBuilder setNumOfIssues(int numOfIssues) {
		billRequest.setNumOfIssues(numOfIssues);
		return this;
	}


	public BillRequestBuilder setDayOfIssue(int dayOfIssue) {
		billRequest.setDayOfIssue(dayOfIssue);
		return this;
	}


	public BillRequestBuilder setPerMonths(int perMonths) {
		billRequest.setPerMonths(perMonths);
		return this;
	}
}
