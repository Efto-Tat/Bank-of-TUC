package managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import backend.AccountOwner;
import dataAccessObjects.UserDAO;
import factories.AccountOwnerFactory;
import factories.DAOFactory;

public class UserManager{
	private static UserManager instance;
	private UserDAO userDao;
	private HashMap<String,AccountOwner> users;
	
	private UserManager() {
		this.userDao = (UserDAO) DAOFactory.createDAO("users");
		this.users = new HashMap<String, AccountOwner>();
	}
	
	public AccountOwner checkLoginInfo(String username, char[] password) {
		Collection<AccountOwner> allUsers = users.values();
		
		for(AccountOwner curUser : allUsers) {
			if(curUser.getUsername().equals(username) && curUser.getPassword().equals(String.valueOf(password)))
				return curUser;
		}
		return null;
	}
	
	public HashMap<String, AccountOwner> getUsers() {
		return users;
	}

	public static UserManager getUserManager() {
		if(instance == null) {
			instance = new UserManager();
			return instance;
		}
		else
			return instance;
	}
	
	public void loadUsers() {
		try {
			List<List<String>> userList = readUsers();
			int rows = userList.size();
			int index=rows-1;
			while(index>=0) {
				addUser(userList.get(index));
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
	
	public List<List<String>> readUsers() throws FileNotFoundException, IOException {
		return userDao.loadInfo();
	}
	
	public void addUser(List<String> userInfo) {
		AccountOwner newUser = AccountOwnerFactory.createAccountOwner(userInfo);
		users.put(newUser.getAFM(), newUser);
		System.out.println("Added user: "+newUser.getName()+" "+newUser.getAFM());
	}

}
