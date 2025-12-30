package backend;

public abstract class Transaction extends Interaction {
	private float amount;
	private InteractionStatus status;
	private String date;
	private String time;
	
	public Transaction() {
		
	}
	
	

	public float getAmount() {
		return amount;
	}



	public void setAmount(float amount) {
		this.amount = amount;
	}



	public InteractionStatus getStatus() {
		return status;
	}



	public void setStatus(InteractionStatus status) {
		this.status = status;
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
	
	
}
