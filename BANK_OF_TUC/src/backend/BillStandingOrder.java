package backend;

public class BillStandingOrder extends StandingOrder{
	private String rfCode;
	private float maxAmount;
	
	public BillStandingOrder() {
		// TODO Auto-generated constructor stub
	}

	public String getRfCode() {
		return rfCode;
	}

	public void setRfCode(String rfCode) {
		this.rfCode = rfCode;
	}

	public float getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(float maxAmount) {
		this.maxAmount = maxAmount;
	}
	
	

}
