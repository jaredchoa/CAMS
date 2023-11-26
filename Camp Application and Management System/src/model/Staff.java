package model;
import java.util.*;
import common.model.*;
import model.*;

public class Staff extends User {
	
	private Camp createdCamp;
	private StudentOrStaff UserType = StudentOrStaff.Staff;
	
	public Staff(String UserID, String UserName, Faculty UserFaculty, String password) {
		super(UserID, UserName, UserFaculty, password);
		this.createdCamp = null;
	}
	
	public void viewCreatedCamp() {
		if(createdCamp != null) {
			createdCamp.printCamp();
		}
		else {
			System.out.println("You have yet to create any camp!");
		}
	}
	
	public Camp getCreatedCamp() {
		return createdCamp;
	}

	public void setCreatedCamp(Camp createdCamp) {
		this.createdCamp = createdCamp;
	}
	
	public String getCampID() {
		return this.getCreatedCamp().getCampID();
	}

	public StudentOrStaff getUserType() {
		return UserType;
	}
		
}
