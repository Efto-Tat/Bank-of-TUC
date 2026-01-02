package backend;

public class TransferStandingOrder extends StandingOrder{
	private float paymentAmount;
	private int perMonths;
	private int dayOfPayment;
	
	public TransferStandingOrder() {
		// TODO Auto-generated constructor stub
	}

	public float getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}


	public int getPerMonths() {
		return perMonths;
	}

	public void setPerMonths(int perMonths) {
		this.perMonths = perMonths;
	}

	public int getDayOfPayment() {
		return dayOfPayment;
	}

	public void setDayOfPayment(int dayOfPayment) {
		this.dayOfPayment = dayOfPayment;
	}
	
	

}
