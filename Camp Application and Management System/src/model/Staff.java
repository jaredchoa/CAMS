package model;
import java.util.*;
import common.model.*;
import model.*;

/** 
 * class stores information for staff
 */
public class Staff extends User {
	
	private Camp createdCamp;
	private StudentOrStaff UserType = StudentOrStaff.Staff;
	
	/** 
	 * @param UserID staff member's ID
	 * @param UserName staff member's name
	 * @param UserFaculty staff member's faculty
	 * @param password staff member's password
	 */
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
	
	/** 
	 * @param Camp camp object
	 */
	public Camp getCreatedCamp() {
		return createdCamp;
	}

	/** 
	 * @param Camp camp object
	 */
	public void setCreatedCamp(Camp createdCamp) {
		this.createdCamp = createdCamp;
	}
	
	/** 
	 * @param return campID
	 */
	public String getCampID() {
		return this.getCreatedCamp().getCampID();
	}

	/** 
	 * @param return UserType
	 */
	public StudentOrStaff getUserType() {
		return UserType;
	}
		
}
