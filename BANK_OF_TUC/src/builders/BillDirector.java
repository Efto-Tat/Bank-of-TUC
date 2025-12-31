package builders;

import java.util.List;

import backend.Bill;
import backend.BillPayment;
import backend.BillRequest;
import backend.InteractionStatus;
import backend.Transaction;

public class BillDirector {

	public BillDirector() {
		// TODO Auto-generated constructor stub
	}
	
	public BillRequest createRequestFromString(BillRequestBuilder builder, List<String> billInfo) {
		builder.setSenderIBAN(billInfo.get(0))
			.setRfCode(billInfo.get(1))
			.setRecipientIBAN(billInfo.get(2))
			.setAmountPerBill(Float.parseFloat(billInfo.get(3)));
		if(!billInfo.get(4).equals("")) {
			builder.setPerMonths(Integer.parseInt(billInfo.get(4)))
				.setDayOfIssue(Integer.parseInt(billInfo.get(5)))
				.setNumOfIssues(Integer.parseInt(billInfo.get(7)));
		}
		else
			builder.setNumOfIssues(1);
		
		return builder.build();
	}
	
	public BillPayment createPaymentFromString(BillPaymentBuilder builder, List<String> paymentInfo) {
		builder.setSenderIBAN(paymentInfo.get(0))
			.setRfCode(paymentInfo.get(1))
			.setbillID(paymentInfo.get(3))
			.setReceiverIBAN(paymentInfo.get(4))
			.setAmount(Float.parseFloat(paymentInfo.get(5)))
			.setDateIssued(paymentInfo.get(8))
			.setDueDate(paymentInfo.get(9));
		if(paymentInfo.get(2).equals("SUCCESFUL")) {
			builder.setStatus(InteractionStatus.SUCCESFUL)
				.setDate(paymentInfo.get(6))
				.setTime(paymentInfo.get(7));
		}
		else if(paymentInfo.get(2).equals("PENDING")) {
			builder.setStatus(InteractionStatus.PENDING);
		}
		return builder.build();
	}
	
	public BillPayment createPaymentFromRequest(BillPaymentBuilder builder, BillRequest request) {
		builder.setSenderIBAN(request.getSenderIBAN())
			.setRfCode(request.getRfCode())
			.setStatus(InteractionStatus.PENDING)
			.setbillID(null) //ADD BILL ID GENERATION!!!!
			.setReceiverIBAN(request.getRecipientIBAN())
			.setAmount(request.getAmountPerBill())
			.setDateIssued(null) //ADD CALENDAR STUFF!!!!
			.setDueDate(null);
		return builder.build();
	}
	
	public BillPayment createNextPayment(BillPaymentBuilder builder, Bill bill) {
		
	}
	
	public Bill createBillFromRequest(BillBuilder builder, BillRequest request) {
		builder.set
		return null;
	}

}
