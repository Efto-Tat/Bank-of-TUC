package builders;

import backend.BillStandingOrder;

public class BillStandingOrderBuilder {
	BillStandingOrder standingOrder;
	
	public BillStandingOrderBuilder() {
		standingOrder = new BillStandingOrder();
	}
	
	public BillStandingOrder build() {
		return this.standingOrder;
	}
	
	public BillStandingOrderBuilder setSenderIBAN(String senderIBAN) {
		this.standingOrder.setSenderIBAN(senderIBAN);
		return this;
	}
	
	public BillStandingOrderBuilder setStartDate(String startDate) {
		this.standingOrder.setStartDate(startDate);
		return this;
	}

	public BillStandingOrderBuilder setOrderName(String orderName) {
		this.standingOrder.setOrderName(orderName);
		return this;
	}

	public BillStandingOrderBuilder setDescription(String description) {
		this.standingOrder.setDescription(description);
		return this;
	}

	public BillStandingOrderBuilder setEndDate(String endDate) {
		this.standingOrder.setEndDate(endDate);
		return this;
	}

	public BillStandingOrderBuilder setPaymentFee(float paymentFee) {
		this.standingOrder.setPaymentFee(paymentFee);
		return this;
	}

	public BillStandingOrderBuilder setReceiverIBAN(String receiverIBAN) {
		this.standingOrder.setReceiverIBAN(receiverIBAN);
		return this;
	}

	public BillStandingOrderBuilder setRfCode(String rfCode) {
		standingOrder.setRfCode(rfCode);
		return this;
	}

	public BillStandingOrderBuilder setMaxAmount(float maxAmount) {
		standingOrder.setMaxAmount(maxAmount);
		return this;
	}
}
