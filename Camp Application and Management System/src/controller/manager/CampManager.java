package controller.manager;

import java.util.Map;
import java.util.HashMap;
import model.*;


import common.model.*;
/** 
 * class to manage all the camps
 */ 
public class CampManager {
	
	private Map<String, Camp> campData;
	
	/**
	 * camp manager constructor
	 */
	public CampManager() {
		
		 // constructor for hashmap to store our Camps
		this.campData = new HashMap<>(); 
	}
	
	

	public Map<String, Camp> getcampData() {
		return campData;
	}
	
	
	/** 
	 * @param CampID camp ID
	 * @return Camp object
	 */
	public Camp getCamp(String CampID) {
		return campData.get(CampID);
	}
	
	
	/** 
	 * @param camp camp object
	 */
	// do we want to add an already created camp object here
	// or create a new camp object right inside this function?
	public void addNewCamp(Camp camp) {
		campData.put(camp.getCampID(), camp);
		// we need to add the part where we assign camp to the staff too
	}
	
	
	/** 
	 * @param campID camp ID
	 */
	public void removeCamp(String campID) {
		campData.remove(campID);
	}
	
	
	/** 
	 * @param user user object
	 * @param viewStudent boolean to check if user is student
	 */
	public void printCampData(User user, boolean viewStudent) {
		// can get more options for user to choose how much details they would like
		
		if (user instanceof Student) {		//if user is Student
			
			if (viewStudent) {		//true - view only student's camps
				for (Camp camp: ((Student)user).getRegisteredCamps()) {
					camp.printCamp();
				}
			}
			
			else {
				for (Map.Entry<String, Camp> pair : campData.entrySet()) {
					Camp camp = pair.getValue();
					
					if ( (user.getUserFaculty() == camp.getCampFaculty() || user.getUserFaculty() == Faculty.ALL) && (camp.getVisibility())) {
						camp.printCamp();
					}
				}
			}
		}
		
		else if (user instanceof Staff) { //print all camps
			for (Map.Entry<String, Camp> pair : campData.entrySet()) {
				Camp camp = pair.getValue();
				camp.printCamp();
			}
		}
		
		else return;
		
		
//		for (Map.Entry<String, Camp> pair : campData.entrySet()) {
//			Camp camp = pair.getValue();
//
//			System.out.println(
//					"CampID: " + camp.getCampID()  + "\n" +
//					"Camp Name:" + camp.getCampName() + "\n" +
//					"staffID:" + camp.getStaffID() + "\n" +
//					"Camp Start and End date: " + camp.getCampStartDate() + "-" + camp.getCampEndDate() + "\n" +
//					"Details: " + camp.getDescription() + "\n"
//					);
//		}
	}
	




//	// camp updates
//	public static void printCampData(Map<String, Camp> campData) {			//?
//		
//		// can get more options for user to choose how much details they would like
//		
//		for (Map.Entry<String, Camp> pair : campData.entrySet()) {
//			Camp camp = pair.getValue();
//
//			System.out.println(
//					"CampID: " + camp.getCampID()  + "\n" +
//					"Camp Name:" + camp.getCampName() + "\n" +
//					"staffID:" + camp.getStaffID() + "\n" +
//					"Camp Start and End date: " + camp.getCampStartDate() + "-" + camp.getCampEndDate() + "\n" +
//					"Details: " + camp.getDescription() + "\n"
//					);
//		}
//	}

	public Map<String, Camp> facultyFilter (Faculty faculty) {
		
		Map<String, Camp> campFiltered = new HashMap<>();
		
		for (Map.Entry<String, Camp> pair : campData.entrySet()) {
			Camp camp = pair.getValue();
			if (camp.getCampFaculty().equals(faculty))
				campFiltered.put(camp.getCampID(), camp);
		}
		
		return campFiltered;
	}
	
	
	
		
		
	
	
	
	
	
	
	
	
}
