package managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import backend.BillStandingOrder;
import backend.ClientAccount;
import backend.TransferStandingOrder;
import backend.calendar.CalendarObserver;
import backend.calendar.ConcreteCalendar;
import builders.BillStandingOrderBuilder;
import builders.StandingOrderDirector;
import builders.TransferStandingOrderBuilder;
import dataAccessObjects.BillStandingOrderDAO;
import dataAccessObjects.TransferStandingOrderDAO;
import factories.DAOFactory;

public class StandingOrderManager implements CalendarObserver{
	private static StandingOrderManager instance;
	private TransferStandingOrderDAO transferStandingOrderDao;
	private BillStandingOrderDAO billStandingOrderDao;
	private HashMap<String,TransferStandingOrder> transferStandingOrders;
	private HashMap<String,BillStandingOrder> billStandingOrders;
	private ConcreteCalendar calendar;
	
	public StandingOrderManager() {
		transferStandingOrderDao = (TransferStandingOrderDAO) DAOFactory.createDAO("transferStandingOrders");
		billStandingOrderDao = (BillStandingOrderDAO) DAOFactory.createDAO("billStandingOrders");
		transferStandingOrders = new HashMap<String,TransferStandingOrder>();
		billStandingOrders = new HashMap<String,BillStandingOrder>();
		calendar = ConcreteCalendar.getCalendar();
		calendar.register(this);
	}
	
	public static StandingOrderManager getStandingOrderManager() {
		if(instance == null)
			instance = new StandingOrderManager();
		return instance;
	}
	
	public void initStandingOrders() {
		loadStandingOrders();
	}
	
	public void loadStandingOrders() {
		loadBillStandingOrders();
		loadTransferStandingOrders();
	}
	
	public void loadTransferStandingOrders() {
		try {
			List<List<String>> standingOrderList = readTransferStandingOrders();
			for(List<String> standingOrderInfo : standingOrderList) {
				TransferStandingOrderBuilder builder = new TransferStandingOrderBuilder();
				StandingOrderDirector director = new StandingOrderDirector();
				TransferStandingOrder loadedOrder = director.createTransferStandingOrderFromFile(builder, standingOrderInfo);
				transferStandingOrders.put(loadedOrder.getOrderName(), loadedOrder);
				ClientAccount sender = (ClientAccount) AccountManager.getAccountManager().getAccounts().get(loadedOrder.getSenderIBAN());
				sender.getStandingOrders().add(loadedOrder);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadBillStandingOrders() {
		try {
			List<List<String>> standingOrderList = readBillStandingOrders();
			for(List<String> standingOrderInfo : standingOrderList) {
				BillStandingOrderBuilder builder = new BillStandingOrderBuilder();
				StandingOrderDirector director = new StandingOrderDirector();
				BillStandingOrder loadedOrder = director.createBillStandingOrderFromFile(builder, standingOrderInfo);
				billStandingOrders.put(loadedOrder.getOrderName(), loadedOrder);
				ClientAccount sender = (ClientAccount) AccountManager.getAccountManager().getAccounts().get(loadedOrder.getSenderIBAN());
				sender.getStandingOrders().add(loadedOrder);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<List<String>> readTransferStandingOrders() throws FileNotFoundException, IOException{
		return transferStandingOrderDao.loadInfo();
	}
	
	public List<List<String>> readBillStandingOrders() throws FileNotFoundException, IOException{
		return billStandingOrderDao.loadInfo();
	}
	
	public void updateDB() {
		transferStandingOrderDao.updateDatabase(transferStandingOrders);
		billStandingOrderDao.updateDatabase(billStandingOrders);
	}
	
	
	
	
	public void checkTransferStandingOrders() {
		Collection<TransferStandingOrder> allTransferOrders = transferStandingOrders.values();
		for(TransferStandingOrder curTransferOrder : allTransferOrders) {
			if(curTransferOrder.getNextPaymentDate().equals(calendar.getCurDate().get(Calendar.YEAR)+"-"+(calendar.getCurDate().get(Calendar.MONTH)+1)+"-"+calendar.getCurDate().get(Calendar.DATE))){
				//EXECUTE TRANSFER
				curTransferOrder.calculateNextPaymentDate();
			}
			if(curTransferOrder.getEndDate().equals(calendar.getCurDate().get(Calendar.YEAR)+"-"+(calendar.getCurDate().get(Calendar.MONTH)+1)+"-"+calendar.getCurDate().get(Calendar.DATE))){
				ClientAccount owner = (ClientAccount) AccountManager.getAccountManager().getAccounts().get(curTransferOrder.getSenderIBAN());
				owner.getStandingOrders().remove(curTransferOrder);
				transferStandingOrders.remove(curTransferOrder.getOrderName());
			}
		}
	}
	
	public void checkBillStandingOrders() {
		Collection<BillStandingOrder> allBillOrders = billStandingOrders.values();
		for(BillStandingOrder curBillOrder : allBillOrders) {
			if(curBillOrder.getMaxAmount() <= BillManager.getBillManager().getBills().get(curBillOrder.getRfCode()).getTotalAmountOwed()){
				//EXECUTE BILL PAYMENTS UNTIL THE IF STATEMENT IS FALSE
			}
			if(curBillOrder.getEndDate().equals(calendar.getCurDate().get(Calendar.YEAR)+"-"+(calendar.getCurDate().get(Calendar.MONTH)+1)+"-"+calendar.getCurDate().get(Calendar.DATE))){
				ClientAccount owner = (ClientAccount) AccountManager.getAccountManager().getAccounts().get(curBillOrder.getSenderIBAN());
				owner.getStandingOrders().remove(curBillOrder);
				billStandingOrders.remove(curBillOrder.getOrderName());
			}
		}
	}

	@Override
	public void onTimePass() {
		checkTransferStandingOrders();
		checkBillStandingOrders();
	}
}
