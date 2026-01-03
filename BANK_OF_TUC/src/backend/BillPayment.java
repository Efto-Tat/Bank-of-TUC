package backend;

public class BillPayment extends Transaction{
	private String dateIssued;
	private String dueDate;
	private String billID;
	private String rfCode;
	
	public BillPayment() {
		// TODO Auto-generated constructor stub
	}
	

	public String getRfCode() {
		return rfCode;
	}

	public void setRfCode(String rfCode) {
		this.rfCode = rfCode;
	}

	public String getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(String dateIssued) {
		this.dateIssued = dateIssued;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getbillID() {
		return billID;
	}

	public void setbillID(String billID) {
		this.billID = billID;
	}
	
	

}
