package builders;

import backend.TransferStandingOrder;

public class TransferStandingOrderBuilder {
	TransferStandingOrder standingOrder;
	
	public TransferStandingOrderBuilder() {
		this.standingOrder = new TransferStandingOrder();
	}
	
	public TransferStandingOrder build() {
		return standingOrder;
	}
	
	public TransferStandingOrderBuilder setSenderIBAN(String senderIBAN) {
		this.standingOrder.setSenderIBAN(senderIBAN);
		return this;
	}
	
	public TransferStandingOrderBuilder setStartDate(String startDate) {
		this.standingOrder.setStartDate(startDate);
		return this;
	}

	public TransferStandingOrderBuilder setOrderName(String orderName) {
		this.standingOrder.setOrderName(orderName);
		return this;
	}

	public TransferStandingOrderBuilder setDescription(String description) {
		this.standingOrder.setDescription(description);
		return this;
	}

	public TransferStandingOrderBuilder setEndDate(String endDate) {
		this.standingOrder.setEndDate(endDate);
		return this;
	}

	public TransferStandingOrderBuilder setPaymentFee(float paymentFee) {
		this.standingOrder.setPaymentFee(paymentFee);
		return this;
	}

	public TransferStandingOrderBuilder setPaymentAmount(float paymentAmount) {
		this.standingOrder.setPaymentAmount(paymentAmount);
		return this;
	}

	public TransferStandingOrderBuilder setReceiverIBAN(String receiverIBAN) {
		this.standingOrder.setReceiverIBAN(receiverIBAN);
		return this;
	}
	
	public TransferStandingOrderBuilder setPerMonths(int perMonths) {
		this.standingOrder.setPerMonths(perMonths);
		return this;
	}

	public TransferStandingOrderBuilder setDayOfPayment(int dayOfPayment) {
		this.standingOrder.setDayOfPayment(dayOfPayment);
		return this;
	}
}
