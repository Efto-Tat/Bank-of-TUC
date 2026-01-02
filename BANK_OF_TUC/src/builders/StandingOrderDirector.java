package builders;

import java.util.List;

import backend.TransferStandingOrder;

public class StandingOrderDirector {
	
	public StandingOrderDirector() {
		
	}
	
	public TransferStandingOrder createTransferStandingOrderFromFile(TransferStandingOrderBuilder builder, List<String> standingOrderInfo) {
		builder.setOrderName(standingOrderInfo.get(0))
			.setDescription(standingOrderInfo.get(1))
			.setPaymentAmount(Float.parseFloat(standingOrderInfo.get(2)))
			.setStartDate(standingOrderInfo.get(3))
			.setEndDate(standingOrderInfo.get(4))
			.setPaymentFee(Float.parseFloat(standingOrderInfo.get(5)))
			.setSenderIBAN(standingOrderInfo.get(6))
			.setReceiverIBAN(standingOrderInfo.get(7))
			.setPerMonths(Integer.parseInt(standingOrderInfo.get(8)))
			.setDayOfPayment(Integer.parseInt(standingOrderInfo.get(9)));
		
		return builder.build();
	}
	
	
}
