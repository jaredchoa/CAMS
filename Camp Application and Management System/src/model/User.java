package model;
import java.util.Scanner;
import java.util.regex.*;

import common.model.Faculty;
import common.util.ReadChecker;

public abstract class User {
	private String UserID;
	private String password;
	private Faculty UserFaculty;
	private String UserName;
	
	public User(String UserID,String UserName, Faculty UserFaculty, String password) {
		// TODO Auto-generated constructor stub
		this.UserName = UserName;
		this.UserID = UserID;
		this.UserFaculty = UserFaculty;
		this.password = password; 		//default password

	}

	public String getUserID() {		//no setter as USERID CANT BE CHANGED
		return UserID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {		//to change Password
		this.password = password;
	}

	public Faculty getUserFaculty() {
		return UserFaculty;
	}

	public String getUserName() {
		return UserName;
	}
	
	private boolean checkPassword(String newPass) {
		boolean acceptable = true;
		if (newPass.length()<10) {
			System.out.println("Your password must be at least 10 characters long.");
			acceptable = false;
		}
		if (!newPass.matches(".*[A-Z].*")) {
			System.out.println("Your password must have at least 1 uppercase character.");
			acceptable = false;
		}
		if (!newPass.matches(".*[a-z].*")) {
			System.out.println("Your password must have at least 1 lowercase character.");
			acceptable = false;
		}
		if (!newPass.matches(".*\\d.*")) {
			System.out.println("Your password must have at least 1 number.");
			acceptable = false;
		}
		return acceptable;
	}
	
	public void changePassword() {
		Scanner sc = new Scanner(System.in);
		String newPass;
		System.out.println("Please change your password. Your password should include:\n"
				+ "1. number\n"
				+ "2. upper and lower case characters\n"
				+ "3. at least 10 characters long");
		do {
			System.out.print("Enter your new password: ");
			newPass = sc.nextLine();

		}while(!checkPassword(newPass));
		
		this.setPassword(newPass);
		
		System.out.println("Password successfully changed!");
				
	}
	
    public boolean authenticate(String password) {
    	if(this.getPassword().equals(password)) {
    		if (this.getPassword().equals("password")) {
    			changePassword();
    		}
    		return true;
    	}
    	else 
    		return false;
    }
}
