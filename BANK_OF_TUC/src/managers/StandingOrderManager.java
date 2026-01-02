package managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import backend.BillStandingOrder;
import backend.ClientAccount;
import backend.TransferStandingOrder;
import builders.StandingOrderDirector;
import builders.TransferStandingOrderBuilder;
import dataAccessObjects.BillStandingOrderDAO;
import dataAccessObjects.TransferStandingOrderDAO;
import factories.DAOFactory;

public class StandingOrderManager {
	private static StandingOrderManager instance;
	private TransferStandingOrderDAO transferStandingOrderDao;
	private BillStandingOrderDAO billStandingOrderDao;
	private HashMap<String,TransferStandingOrder> transferStandingOrders;
	private HashMap<String,BillStandingOrder> billStandingOrders;
	
	public StandingOrderManager() {
		transferStandingOrderDao = (TransferStandingOrderDAO) DAOFactory.createDAO("transferStandingOrders");
		billStandingOrderDao = (BillStandingOrderDAO) DAOFactory.createDAO("billStandingOrders");
		transferStandingOrders = new HashMap<String,TransferStandingOrder>();
		billStandingOrders = new HashMap<String,BillStandingOrder>();
	}
	
	public static StandingOrderManager getStandingOrderManager() {
		if(instance == null)
			instance = new StandingOrderManager();
		return instance;
	}
	
	public void loadStandingOrders() {
		loadBillStandingOrders();
		loadTransferStandingOrders();
	}
	
	public void loadTransferStandingOrders() {
		try {
			TransferStandingOrderBuilder builder = new TransferStandingOrderBuilder();
			StandingOrderDirector director = new StandingOrderDirector();
			List<List<String>> standingOrderList = readTransferStandingOrders();
			for(List<String> standingOrderInfo : standingOrderList) {
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
	
	public void loadBillStandingOrders() { //CHANGE FOR BILLS
		try {
			TransferStandingOrderBuilder builder = new TransferStandingOrderBuilder();
			StandingOrderDirector director = new StandingOrderDirector();
			List<List<String>> standingOrderList = readTransferStandingOrders();
			for(List<String> standingOrderInfo : standingOrderList) {
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
	
	public List<List<String>> readTransferStandingOrders() throws FileNotFoundException, IOException{
		return transferStandingOrderDao.loadInfo();
	}
	
	public List<List<String>> readBillStandingOrders() throws FileNotFoundException, IOException{
		return billStandingOrderDao.loadInfo();
	}
}
