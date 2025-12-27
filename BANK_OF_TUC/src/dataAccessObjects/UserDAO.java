package dataAccessObjects;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
