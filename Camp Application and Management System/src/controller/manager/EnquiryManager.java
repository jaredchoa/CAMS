package controller.manager;

import java.util.*;

import application.mainApp;
import model.*;
import common.model.*;
import controller.*;


/** 
 * class to manage all the Enquiries
 */ 
public class EnquiryManager {
	
	public Map<String, Enquiry> enquiries;
	
	
	/** 
	 * @return Map<String, Enquiry> list of all the enquiry data
	 */
	public Map<String, Enquiry> getEnquiryData() {
		return enquiries;
	}	
	public EnquiryManager(){
		enquiries = new HashMap<>();
	}
	
	
	/** 
	 * @param enquiry enquiry object
	 */
	public void addEnquiry(Enquiry enquiry) {
		enquiries.put(enquiry.getEnquiryID(), enquiry);
	}
	
	
	/** 
	 * @param enquiry enquiry object
	 * @param sender user object
	 * @param camp camp ID
	 * @return Enquiry object
	 */
	public Enquiry addEnquiry(String enquiry, User sender, String camp) {
		Enquiry newEnquiry = new Enquiry(enquiry, sender, camp);
		enquiries.put(newEnquiry.getEnquiryID(), newEnquiry);
		
		return newEnquiry;
	}
	
	
	/** 
	 * @param enquiryID enquiry ID
	 * @return Enquiry object
	 */
	public Enquiry getEnquiryByID(String enquiryID){
		return enquiries.get(enquiryID);
	}
	
	
	/** 
	 * @param student student object
	 * @param campID camp ID
	 * @return int count of enquiries
	 */
	public int ViewByStudent(Student student, String campID) {
		int count = 0;
		for (Enquiry enquiry: student.getStudentEnquiries()) {
			if (campID == null) {
				enquiry.printEnquiry();
				count++;
			}
			else if (campID.equals(enquiry.getCampID())) {
				enquiry.printEnquiry();
				count++;
			}
			else {
				continue;
			}
		}
		return count;
	}
	
	/** 
	 * @param committeeCamp camp object
	 */
	public void ViewByCommittee( Camp committeeCamp) {		
		for (Enquiry enquiry: committeeCamp.getEnquiriesList() ) {
				enquiry.printEnquiry();
			}
	}
	
	
	/** 
	 * @param staffCamp camp object
	 * @return int count of enquiries
	 */
	public int ViewByStaff(Camp staffCamp) {
		int count = 0;
		for (Enquiry enquiry: staffCamp.getEnquiriesList()) {
			count++;
			enquiry.printEnquiry();
		}
		return count;
	}

	
	/** 
	 * @return Map<String, Enquiry> list of all the enquiries
	 */
	public Map<String, Enquiry> getEnquiries() {
		return enquiries;
	}

	
	/** 
	 * @param enquiries enquiry object
	 */
	public void setEnquiries(Map<String, Enquiry> enquiries) {
		this.enquiries = enquiries;
	}

}

