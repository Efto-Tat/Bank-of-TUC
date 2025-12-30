package dataAccessObjects;
import java.util.HashMap;
import backend.AccountOwner;
import managers.UserManager;


public class UserDAO extends DAO{
	
	public UserDAO() {
		this.fileName = "users";
	}
	
	public HashMap<String,AccountOwner> getUsers(){
		return UserManager.getUserManager().getUsers();
	}
}
