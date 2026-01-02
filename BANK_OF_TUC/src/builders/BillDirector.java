package builders;

import java.util.List;

import backend.Bill;
import backend.BillPayment;
import backend.BillRequest;
import backend.InteractionStatus;

public class BillDirector {

	public BillDirector() {
		// TODO Auto-generated constructor stub
	}
	
//	public BillRequest createRequestFromString(BillRequestBuilder builder, List<String> billInfo) {
//		builder.setSenderIBAN(billInfo.get(0))
//			.setRfCode(billInfo.get(1))
//			.setRecipientIBAN(billInfo.get(2))
//			.setAmountPerBill(Float.parseFloat(billInfo.get(3)));
//		if(!billInfo.get(4).equals("")) {
//			builder.setPerMonths(Integer.parseInt(billInfo.get(4)))
//				.setDayOfIssue(Integer.parseInt(billInfo.get(5)))
//				.setNumOfIssues(Integer.parseInt(billInfo.get(7)));
//		}
//		else
//			builder.setNumOfIssues(1);
//		
//		return builder.build();
//	}
	
	public Bill createBillFromFile(BillBuilder builder, List<String> billInfo) {
		builder.setSenderIBAN(billInfo.get(0))
			.setRfCode(billInfo.get(1))
			.setReceiverIBAN(billInfo.get(2))
			.setAmountPerBill(Float.parseFloat(billInfo.get(3)))
			.setRemainingIssues(Integer.parseInt(billInfo.get(7)))
			.setDayOfIssue(Integer.parseInt(billInfo.get(5)));
		if(Integer.parseInt(billInfo.get(7))!=1) {
			builder.setIssueFrequency(Integer.parseInt(billInfo.get(4)))
			.setTotalAmount(Float.parseFloat(billInfo.get(6)));
		}
		return builder.build();
	}
	
	public BillPayment createPaymentFromFile(BillPaymentBuilder builder, List<String> paymentInfo) {
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
	
	public BillPayment createPayment(BillPaymentBuilder builder, Bill bill, long ID) {
		builder.setSenderIBAN(bill.getSenderIBAN())
			.setRfCode(bill.getRfCode())
			.setStatus(InteractionStatus.PENDING)
			.setbillID(Long.toString(ID))
			.setReceiverIBAN(bill.getReceiverIBAN())
			.setAmount(bill.getAmountPerBill())
			.setDateIssued("DATE ISSUED") //ADD CALENDAR STUFF!!!
			.setDueDate("DUE DATE");
		return builder.build();
	}
	
	public Bill createBillFromRequest(BillBuilder builder, BillRequest request, long rfCode) {
		builder.setSenderIBAN(request.getSenderIBAN())
			.setRfCode("RF"+rfCode)
			.setReceiverIBAN(request.getRecipientIBAN())
			.setAmountPerBill(request.getAmountPerBill())
			.setRemainingIssues(request.getNumOfIssues());
		
		if(request.getNumOfIssues() == 1) {
			builder.setIssueFrequency(0)
				.setDayOfIssue(request.getDayOfIssue())
				.setRemainingIssues(request.getNumOfIssues());
			return builder.build();
		}
			
		
		builder.setIssueFrequency(request.getPerMonths())
			.setDayOfIssue(request.getDayOfIssue());
		return builder.build();
	}

}
