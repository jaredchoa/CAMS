package model;

import common.model.*;
import java.time.*;
import java.util.ArrayList;

public class Camp {
	
	public static int CAMPCOUNTER = 1;
	
	private String campID;
	
	private String campName;
	
	private Faculty campFaculty;
	private String location; 			//change to enum later??
	private String description;

	// Dates
	private LocalDate campStartDate;
	private LocalDate campEndDate;
	private LocalDate regCloseDate;
	
	private int totalSlots;
	private int committeeSlots;			// max 10
	private Staff StaffInCharge; 		//directly linked to creating staff member (no need to take extra input from staff)
	//private String staffID;
	
	private boolean visibility;
	
	// Relationships
	private ArrayList<Student> studentList;
	private ArrayList<Student> committeeList;		//not array: So can change length when TotaalSlots edited by staff
	private ArrayList<Enquiry> enquiryList;
	private ArrayList<Suggestion> suggestionList;				//stores all suggestions in particular camp
	private int attendeeCount;
	private int committeeCount;	
	
	private boolean isDeleted;
	
	/*
	 * visibility
	 * 	0, 1
	 */
	public Camp(
				String campID,
				String campName,
				Faculty faculty,
				String location,
				String description,
				LocalDate campStartDate,
				LocalDate campEndDate,
				LocalDate regCloseDate,
				int totalSlots,
				int committeeSlots,
				boolean visibility,
				Staff staff) {
		this.campID = campID;
		this.campName = campName;
		this.campStartDate = campStartDate;
		this.campEndDate = campEndDate;
		this.regCloseDate = regCloseDate;
		this.campFaculty = faculty;
		this.location = location;
		this.totalSlots = totalSlots;
		this.committeeSlots = committeeSlots;
		this.description = description;
		this.visibility = visibility;
		this.isDeleted = false;
		this.StaffInCharge = staff;
		this.enquiryList = new ArrayList<Enquiry>();
		this.studentList = new ArrayList<Student>();
		this.committeeList = new ArrayList<Student>();
		this.suggestionList = new ArrayList<Suggestion>();
		
		CAMPCOUNTER++;
	}
	
	public Camp(String campName,
			Faculty faculty,
			String location,
			String description,
			LocalDate campStartDate,
			LocalDate campEndDate,
			LocalDate regCloseDate,
			int totalSlots,
			int committeeSlots,
			boolean visibility,
			Staff staff) {
	this.campID = "C" + CAMPCOUNTER;
	this.campName = campName;
	this.campStartDate = campStartDate;
	this.campEndDate = campEndDate;
	this.regCloseDate = regCloseDate;
	this.campFaculty = faculty;
	this.location = location;
	this.totalSlots = totalSlots;
	this.committeeSlots = committeeSlots;
	this.description = description;
	this.visibility = visibility;
	this.StaffInCharge = staff;
	this.isDeleted = false;
	this.enquiryList = new ArrayList<Enquiry>();
	
	CAMPCOUNTER++;
}
		
	public Staff getStaffInCharge() {
		return StaffInCharge;
	}

	public void setStaffInCharge(Staff staffInCharge) {
		StaffInCharge = staffInCharge;
	}

	public String getCampID() {
		return campID;
	}


	public String getCampName() {
		return campName;
	}


	public void setCampName(String campName) {
		this.campName = campName;
	}


	public LocalDate getCampStartDate() {
		return campStartDate;
	}


	public void setCampStartDate(LocalDate campStartDate) {
		this.campStartDate = campStartDate;
	}


	public LocalDate getCampEndDate() {
		return campEndDate;
	}


	public void setCampEndDate(LocalDate campEndDate) {
		this.campEndDate = campEndDate; 	
	}


	public LocalDate getRegCloseDate() {
		return regCloseDate;
	}


	public void setRegCloseDate(LocalDate regCloseDate) {
		this.regCloseDate = regCloseDate;
	}


	public Faculty getCampFaculty() {
		return campFaculty;
	}


	public void setCampFaculty(Faculty campFaculty) {
		this.campFaculty = campFaculty;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public int getTotalSlots() {
		return totalSlots;
	}


	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}


	public int getCommitteeSlots() {
		return committeeSlots;
	}


	public void setCommitteeSlots(int committeeSlots) {
		this.committeeSlots = committeeSlots;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean getVisibility() {
		return visibility;
	}


	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}


	public ArrayList<Student> getStudentList() {
		if(studentList==null) {
			studentList = new ArrayList<>();
		}
		return studentList;
	}

	public void addStudentList(Student student) {
		ArrayList<Student> studentList = getStudentList();
		studentList.add(student);
		int count = this.getAttendeeCount();
		count++;
		this.setAttendeeCount(count);
		setStudentList(studentList);
	}

	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}

	public ArrayList<Enquiry> getEnquiryList() {
		return enquiryList;
	}

	public void setEnquiryList(ArrayList<Enquiry> enquiryList) {
		this.enquiryList = enquiryList;
	}

	public void setCommitteeList(ArrayList<Student> committeeList) {
		this.committeeList = committeeList;
	}

	public void setSuggestionList(ArrayList<Suggestion> suggestionList) {
		this.suggestionList = suggestionList;
	}

	public ArrayList<Student> getCommitteeList() {
		return committeeList;
	}


	public void setCommitteeList(Student committee) {
		this.committeeList.add(committee);
	}


	public ArrayList<Enquiry> getEnquiriesList() {
		return enquiryList;
	}


	public void addEnquiriesList(Enquiry enquiry) {
		ArrayList<Enquiry> enquiryList = this.getEnquiryList();
		enquiryList.add(enquiry);
		this.setEnquiryList(enquiryList);
	}

	public ArrayList<Suggestion> getSuggestionList() {
		return suggestionList;
	}


	public void setSuggestionList(Suggestion suggestion) {
		this.suggestionList.add(suggestion);
	}


	public int getAttendeeCount() {
		return attendeeCount;
	}


	public void setAttendeeCount(int attendeeCount) {
		this.attendeeCount = attendeeCount;
	}


	public int getCommitteeCount() {
		return committeeCount;
	}


	public void setCommitteeCount(int committeeCount) {
		this.committeeCount = committeeCount;
	}
	
	public void printCamp() {
		System.out.println("+------------------------------------------------------------+");
		System.out.println(
				"CampID: " + this.getCampID()  + "\n" +
				"Camp Name:" + this.getCampName() + "\n" +
				"Faculty: " + this.getCampFaculty() + "\n" +
				//"Staff:" + this.getStaffID() + "\n" +
				"Camp Start and End date: " + this.getCampStartDate() + " ----> " + this.getCampEndDate() + "\n" +
				"Details: " + this.getDescription() + "\n" +
				"Total slots: " + this.getTotalSlots() + "\n" +
				"Slots Remaining: " + (this.getTotalSlots()-this.getAttendeeCount()) + "\n" +
				"Comittee slots: " + this.getCommitteeSlots() + "\n" +
				"Visibility: " + this.getVisibility()
				);
		System.out.println("+------------------------------------------------------------+\n");

	}
	
	public void removeStudent(Student student) {
		ArrayList<Student> studentList = getStudentList();
		studentList.remove(student);
		int count = this.getAttendeeCount();
		count--;
		this.setAttendeeCount(count);
		setStudentList(studentList);
	}
	
	public void removeEnquiry(Enquiry enquiry) {
		this.enquiryList.remove(enquiry);
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public void deleteStudents() {
		for(Student student : this.studentList) {
			student.removeRegisteredCamp(this);
		}
		
		this.studentList.clear();
	}
	
	public void deleteCommittees() {
		for(Student committee : this.committeeList) {
			committee.removeCommitteeCampID();
		}
		
		this.committeeList.clear();
	}
	
//	public void deleteEnquiries() {
//		for(Enquiry enquiry : this.enquiryList) {
//			enquiry.removeEnquiries();
//		}
//		
//		this.enquiryList.clear();
//	}
//	
//	public void deleteSuggestions() {
//		for(Suggestion suggestion : this.suggestionList) {
//			suggestion.removeSuggestions();
//		}
//		
//		this.suggestionList.clear();
//	}
	
	public void deleteCamp() {
		this.deleteStudents();
		this.deleteCommittees();
//		this.deleteEnquiries();
//		this.deleteSuggestions();
	}
}

