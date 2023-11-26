package controller.manager;
import java.util.HashMap;

import java.util.Map;
import common.model.*;
import model.*;

public class StaffManager {
	
    private Map<String, Staff> staff;

    public StaffManager() {
        staff = new HashMap<>();
    }

    public Staff getStaff(String userID) {
        return staff.get(userID);
    }

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

    public void addStaff(String userID, String userName, Faculty facultyName, String password) {
    	Staff newstaff = new Staff(userID, userName, facultyName, password);
        staff.put(newstaff.getUserID(), newstaff);
    }
    
	public Map<String, Staff> getStaffData() {
		return staff;
	}

}