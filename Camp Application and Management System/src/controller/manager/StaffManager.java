package controller.manager;
import java.util.HashMap;

import java.util.Map;
import common.model.*;
import model.*;
/** 
 * class to manage all the Staff
 */ 
public class StaffManager {
	
    private Map<String, Staff> staff;

    /**
     * constructor for staffmanager
     */
    public StaffManager() {
        staff = new HashMap<>();
    }

    
	/** 
	 * @param userID userID
	 * @return Staff object
	 */
	public Staff getStaff(String userID) {
        return staff.get(userID);
    }

    
	/** 
	 * @param name name of staff
	 * @return String staff ID
	 */
	public String getStaffID(String name) {
    	String output = "Staff not found!";
    	for(Map.Entry<String, Staff> set:staff.entrySet()) {
    		Staff staff = set.getValue();
    		if(staff.getUserName().equals(name)) {
    			output = staff.getUserID();
    			break;
    		}
    	}
    	return output;
    }

    
	/** 
	 * @param userID userID
	 * @param userName userName
	 * @param facultyName faculty object
	 * @param password password
	 */
	public void addStaff(String userID, String userName, Faculty facultyName, String password) {
    	Staff newstaff = new Staff(userID, userName, facultyName, password);
        staff.put(newstaff.getUserID(), newstaff);
    }
    
	
	/**
	 * @return all staff data
	 */
	public Map<String, Staff> getStaffData() {
		return staff;
	}

}