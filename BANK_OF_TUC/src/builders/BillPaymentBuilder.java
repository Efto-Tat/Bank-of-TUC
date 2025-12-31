package builders;

import backend.BillPayment;
import backend.InteractionStatus;

public class BillPaymentBuilder {
	private BillPayment billPayment = new BillPayment();
	
	public BillPaymentBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	public BillPayment build() {
		return billPayment;
	}
	
	public BillPaymentBuilder setRfCode(String rfCode) {
		billPayment.setRfCode(rfCode);
		return this;
	}

	public BillPaymentBuilder setDateIssued(String dateIssued) {
		billPayment.setDateIssued(dateIssued);
		return this;
	}


	public BillPaymentBuilder setDueDate(String dueDate) {
		billPayment.setDueDate(dueDate);
		return this;
	}


	public BillPaymentBuilder setbillID(String billID) {
		billPayment.setbillID(billID);
		return this;
	}
	
	public BillPaymentBuilder setSenderIBAN(String senderIBAN) {
		billPayment.setSenderIBAN(senderIBAN);
		return this;
	}


	public BillPaymentBuilder setReceiverIBAN(String receiverIBAN) {
		billPayment.setReceiverIBAN(receiverIBAN);
		return this;
	}


	public BillPaymentBuilder setAmount(float amount) {
		billPayment.setAmount(amount);
		return this;
	}


	public BillPaymentBuilder setStatus(InteractionStatus status) {
		billPayment.setStatus(status);
		return this;
	}

	public BillPaymentBuilder setDate(String date) {
		billPayment.setDate(date);
		return this;
	}

	public BillPaymentBuilder setTime(String time) {
		billPayment.setTime(time);
		return this;
	}
	
	

}
