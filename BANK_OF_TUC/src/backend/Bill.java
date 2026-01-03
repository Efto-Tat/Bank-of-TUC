package backend;

import java.util.Calendar;
import java.util.HashMap;

import backend.calendar.ConcreteCalendar;

public class Bill extends RepeatedPayment {
	private float amountPerBill;
	private Integer remainingIssues;
	private Integer dayOfIssue;
	private String rfCode;
	private Integer issueFrequency;
	private String dueDateForEach;
	private String nextIssueDate;
	private float totalAmountOwed;
	private HashMap<String,BillPayment> issues;
	
	public Bill() {
		this.issues = new HashMap<String,BillPayment>();
	}
	
	
	
	public void whenPaymentIsIssued() {
		addToTotal();
		decreaseRemainingIssues();
		if(this.remainingIssues > 0)
			calculateNextIssueDate();
	}
	
	public void calculateNextIssueDate() {
		int nextMonthOfIssue = ConcreteCalendar.getCalendar().getCurDate().get(Calendar.MONTH)+1+issueFrequency;
		this.nextIssueDate = ConcreteCalendar.getCalendar().getCurDate().get(Calendar.YEAR)+"-"+nextMonthOfIssue+"-"+dayOfIssue;
	}
	
	public boolean isDue(ConcreteCalendar calendar) {
		return (calendar.dateCompare(this.nextIssueDate));
	}
	
	public String getNextIssueDate() {
		return nextIssueDate;
	}

	public void setNextIssueDate(String nextIssueDate) {
		this.nextIssueDate = nextIssueDate;
	}

	public String getDueDateForEach() {
		return dueDateForEach;
	}

	public void setDueDateForEach(String dueDateForEach) {
		this.dueDateForEach = dueDateForEach;
	}

	public void decreaseRemainingIssues() {
		this.remainingIssues--;
	}
	
	public void addToTotal() {
		this.totalAmountOwed = totalAmountOwed + amountPerBill;
	}
	
	public void subtractFromTotal() {
		this.totalAmountOwed = totalAmountOwed - amountPerBill;
	}
	
	public Integer getDayOfIssue() {
		return dayOfIssue;
	}

	public void setDayOfIssue(Integer dayOfIssue) {
		this.dayOfIssue = dayOfIssue;
	}

	public float getTotalAmountOwed() {
		return totalAmountOwed;
	}

	public void setTotalAmountOwed(float totalAmountOwed) {
		this.totalAmountOwed = totalAmountOwed;
	}

	public HashMap<String, BillPayment> getIssues() {
		return issues;
	}

	public void setIssues(HashMap<String, BillPayment> issues) {
		this.issues = issues;
	}

	public String getRfCode() {
		return rfCode;
	}

	public void setRfCode(String rfCode) {
		this.rfCode = rfCode;
	}

	public float getAmountPerBill() {
		return amountPerBill;
	}

	public void setAmountPerBill(float amountPerBill) {
		this.amountPerBill = amountPerBill;
	}

	public Integer getRemainingIssues() {
		return remainingIssues;
	}

	public void setRemainingIssues(Integer remainingIssues) {
		this.remainingIssues = remainingIssues;
	}

	public Integer getIssueFrequency() {
		return issueFrequency;
	}

	public void setIssueFrequency(Integer issueFrequency) {
		this.issueFrequency = issueFrequency;
	}
	
	

}
