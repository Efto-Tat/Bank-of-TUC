package backend;

import java.awt.desktop.PrintFilesEvent;

import managers.UserManager;


public class Start {
	
	public static final String delimiter = ",";

	public Start() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		UserManager userManager = UserManager.getUserManager();
		userManager.loadUsers();
	}
	

}
