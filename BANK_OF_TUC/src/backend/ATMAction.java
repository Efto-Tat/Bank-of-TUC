package backend;

public abstract class ATMAction extends Interaction{
	private float amount;
	private String date;
	private String time;
	
	public ATMAction() {
		// TODO Auto-generated constructor stub
	}
	
	

	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	

}
