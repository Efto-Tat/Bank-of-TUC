package managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import backend.Bill;
import builders.BillBuilder;
import builders.ConcreteBillBuilder;
import dataAccessObjects.BillDAO;
import factories.DAOFactory;

public class BillManager {
	private static BillManager instance;
	private BillBuilder billBuilder;
	private BillDAO billDao;
	private HashMap<String,Bill> bills;
	

	public BillManager() {
		this.billDao = (BillDAO) DAOFactory.createDAOFactory("bills");
		this.billBuilder = new ConcreteBillBuilder();
		this.bills = new HashMap<String, Bill>();
	}
	
	public static BillManager getBillManager() {
		if(instance == null) {
			instance = new BillManager();
			return instance;
		}
		else
			return instance;
	}
	
	public void loadBills() {
		try {
			List<List<String>> billList = readBills();
			int rows = billList.size();
			int index=rows-1;
			while(index>=0) {
				addBill(billList.get(index));
				index--;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<List<String>> readBills() throws FileNotFoundException, IOException{
		return billDao.loadInfo();
	}
	
	
	public void updateDB() {
		billDao.updateDatabase(bills, UserManager.getUserManager().getUsers());
	}
}
