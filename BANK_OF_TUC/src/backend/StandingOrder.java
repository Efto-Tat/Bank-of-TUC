package backend;

public abstract class StandingOrder extends RepeatedPayment {
	private String orderName;
	private String description;
	private String startDate;
	private String endDate;
	private float paymentFee;
	private ClientAccount owner;
	private String chargedAccIBAN;
	
	public StandingOrder() {
		// TODO Auto-generated constructor stub
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public float getPaymentFee() {
		return paymentFee;
	}

	public void setPaymentFee(float paymentFee) {
		this.paymentFee = paymentFee;
	}

	public ClientAccount getOwner() {
		return owner;
	}

	public void setOwner(ClientAccount owner) {
		this.owner = owner;
	}

	public String getChargedAccIBAN() {
		return chargedAccIBAN;
	}

	public void setChargedAccIBAN(String chargedAccIBAN) {
		this.chargedAccIBAN = chargedAccIBAN;
	}
	
	

}