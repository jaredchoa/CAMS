package controller.manager;

import java.util.*;

import application.mainApp;
import model.*;
import common.model.*;
import controller.*;



public class EnquiryManager {
	
	public Map<String, Enquiry> enquiries;
	
	public Map<String, Enquiry> getEnquiryData() {
		return enquiries;
	}	
	public EnquiryManager(){
		enquiries = new HashMap<>();
	}
	
	public void addEnquiry(Enquiry enquiry) {
		enquiries.put(enquiry.getEnquiryID(), enquiry);
	}
	
	public Enquiry addEnquiry(String enquiry, User sender, String camp) {
		Enquiry newEnquiry = new Enquiry(enquiry, sender, camp);
		enquiries.put(newEnquiry.getEnquiryID(), newEnquiry);
		
		return newEnquiry;
	}
	
	public Enquiry getEnquiryByID(String enquiryID){
		return enquiries.get(enquiryID);
	}
	
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
	public void ViewByCommittee( Camp committeeCamp) {		
		for (Enquiry enquiry: committeeCamp.getEnquiriesList() ) {
				enquiry.printEnquiry();
			}
	}
	
	public int ViewByStaff(Camp staffCamp) {
		int count = 0;
		for (Enquiry enquiry: staffCamp.getEnquiriesList()) {
			count++;
			enquiry.printEnquiry();
		}
		return count;
	}

	public Map<String, Enquiry> getEnquiries() {
		return enquiries;
	}

	public void setEnquiries(Map<String, Enquiry> enquiries) {
		this.enquiries = enquiries;
	}

}

