package backend;

import java.util.Calendar;

import backend.calendar.ConcreteCalendar;

public class TransferStandingOrder extends StandingOrder{
	private float paymentAmount;
	private int perMonths;
	private int dayOfPayment;
	private String nextPaymentDate;
	
	public TransferStandingOrder() {
		// TODO Auto-generated constructor stub
	}
	
	public void calculateNextPaymentDate() {
		int nextMonthOfIssue = ConcreteCalendar.getCalendar().getCurDate().get(Calendar.MONTH)+1+perMonths;
		this.nextPaymentDate = ConcreteCalendar.getCalendar().getCurDate().get(Calendar.YEAR)+"-"+nextMonthOfIssue+"-"+dayOfPayment;
	}

	public String getNextPaymentDate() {
		return nextPaymentDate;
	}



	public void setNextPaymentDate(String nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
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
