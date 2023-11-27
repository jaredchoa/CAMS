package model;

import common.model.*;
import java.time.*;
import java.util.ArrayList;

/**
 * class stores information about a camp
 */

public class Camp {
	
	/**
	 * used for camp unique ID
	 */
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
	
	/**
     * Constructs a new camp with the provided information, used to generate from csv
     *
     * @param campID         unique id for camp.
     * @param campName       camp name
     * @param faculty        faculty camp is under
     * @param location       camp location
     * @param description    camp description
     * @param campStartDate  start date of the camp.
     * @param campEndDate    end date of the camp.
     * @param regCloseDate   registration close date for the camp.
     * @param totalSlots     total number of slots available
     * @param committeeSlots maximum number of committee slots available
     * @param visibility     camp visibility status
     * @param staff          staff member that created the camp
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
	/**
     * Constructs a new camp with the provided information, used during application runtime
     *
     * @param campName       camp name
     * @param faculty        faculty camp is under
     * @param location       camp location
     * @param description    camp description
     * @param campStartDate  start date of the camp.
     * @param campEndDate    end date of the camp.
     * @param regCloseDate   registration close date for the camp.
     * @param totalSlots     total number of slots available
     * @param committeeSlots maximum number of committee slots available
     * @param visibility     camp visibility status
     * @param staff          staff member that created the camp
     */
	
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
		
	/**
	 * @return staff member
	 */
	public Staff getStaffInCharge() {
		return StaffInCharge;
	}

	/**
	 * @param staffInCharge staff member
	 */
	public void setStaffInCharge(Staff staffInCharge) {
		StaffInCharge = staffInCharge;
	}

	/**
	 * @return camp id
	 */
	public String getCampID() {
		return campID;
	}


	/**
	 * @return camp name
	 */
	public String getCampName() {
		return campName;
	}


	/**
	 * @param campName camp name
	 */
	public void setCampName(String campName) {
		this.campName = campName;
	}


	/**
	 * @return camp start date
	 */
	public LocalDate getCampStartDate() {
		return campStartDate;
	}


	/**
	 * @param campStartDate camp start date
	 */
	public void setCampStartDate(LocalDate campStartDate) {
		this.campStartDate = campStartDate;
	}


	/**
	 * @return camp end date
	 */
	public LocalDate getCampEndDate() {
		return campEndDate;
	}


	/**
	 * @param campEndDate camp end date
	 */
	public void setCampEndDate(LocalDate campEndDate) {
		this.campEndDate = campEndDate; 	
	}


	/**
	 * @return registration closing date
	 */
	public LocalDate getRegCloseDate() {
		return regCloseDate;
	}


	/**
	 * @param regCloseDate registration closing date
	 */
	public void setRegCloseDate(LocalDate regCloseDate) {
		this.regCloseDate = regCloseDate;
	}


	/**
	 * @return camp faculty
	 */
	public Faculty getCampFaculty() {
		return campFaculty;
	}


	/**
	 * @param campFaculty camp faculty
	 */
	public void setCampFaculty(Faculty campFaculty) {
		this.campFaculty = campFaculty;
	}


	/**
	 * @return location
	 */
	public String getLocation() {
		return location;
	}


	/**
	 * @param location location
	 */
	public void setLocation(String location) {
		this.location = location;
	}


	/**
	 * @return total slots
	 */
	public int getTotalSlots() {
		return totalSlots;
	}


	/**
	 * @param totalSlots total slots
	 */
	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}


	/**
	 * @return committee slots
	 */
	public int getCommitteeSlots() {
		return committeeSlots;
	}


	/**
	 * @param committeeSlots committee slots
	 */
	public void setCommitteeSlots(int committeeSlots) {
		this.committeeSlots = committeeSlots;
	}


	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description description
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return visibility
	 */
	public boolean getVisibility() {
		return visibility;
	}


	/**
	 * @param visibility visibility
	 */
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}


	/**
	 * @return list of registered student
	 */
	public ArrayList<Student> getStudentList() {
		if(studentList==null) {
			studentList = new ArrayList<>();
		}
		return studentList;
	}

	/**
	 * @param student student
	 */
	public void addStudentList(Student student) {
		ArrayList<Student> studentList = getStudentList();
		studentList.add(student);
		int count = this.getAttendeeCount();
		count++;
		this.setAttendeeCount(count);
		setStudentList(studentList);
	}

	/**
	 * @param studentList student list
	 */
	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}

	/**
	 * @return list of enquiry
	 */
	public ArrayList<Enquiry> getEnquiryList() {
		return enquiryList;
	}

	/**
	 * @param enquiryList list of enquiry
	 */
	public void setEnquiryList(ArrayList<Enquiry> enquiryList) {
		this.enquiryList = enquiryList;
	}

	/**
	 * @param committeeList list of committee
	 */
	public void setCommitteeList(ArrayList<Student> committeeList) {
		this.committeeList = committeeList;
	}

	/**
	 * @param suggestionList suggestion list
	 */
	public void setSuggestionList(ArrayList<Suggestion> suggestionList) {
		this.suggestionList = suggestionList;
	}

	/**
	 * @return committee list
	 */
	public ArrayList<Student> getCommitteeList() {
		return committeeList;
	}


	/**
	 * @param committee commitee member
	 */
	public void setCommitteeList(Student committee) {
		this.committeeList.add(committee);
	}


	/**
	 * @return list of enquiry
	 */
	public ArrayList<Enquiry> getEnquiriesList() {
		return enquiryList;
	}


	/**
	 * @param enquiry enquiry
	 */
	public void addEnquiriesList(Enquiry enquiry) {
		ArrayList<Enquiry> enquiryList = this.getEnquiryList();
		enquiryList.add(enquiry);
		this.setEnquiryList(enquiryList);
	}

	/**
	 * @return suggestion list
	 */
	public ArrayList<Suggestion> getSuggestionList() {
		return suggestionList;
	}


	/**
	 * @param suggestion suggestion
	 */
	public void setSuggestionList(Suggestion suggestion) {
		this.suggestionList.add(suggestion);
	}


	/**
	 * @return attendee count
	 */
	public int getAttendeeCount() {
		return attendeeCount;
	}


	/**
	 * @param attendeeCount attendee count
	 */
	public void setAttendeeCount(int attendeeCount) {
		this.attendeeCount = attendeeCount;
	}


	/**
	 * @return committee count
	 */
	public int getCommitteeCount() {
		return committeeCount;
	}


	/**
	 * @param committeeCount committee count
	 */
	public void setCommitteeCount(int committeeCount) {
		this.committeeCount = committeeCount;
	}
	
	/**
	 * print camp information
	 */
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
	
	/**
	 * @param student student to be removed
	 */
	public void removeStudent(Student student) {
		ArrayList<Student> studentList = getStudentList();
		studentList.remove(student);
		int count = this.getAttendeeCount();
		count--;
		this.setAttendeeCount(count);
		setStudentList(studentList);
	}
	
	/**
	 * @param enquiry enquiry to be removed
	 */
	public void removeEnquiry(Enquiry enquiry) {
		this.enquiryList.remove(enquiry);
	}

	/**
	 * @return is it deleted?
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted deleted?
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	/**
	 * method to delete students
	 */
	public void deleteStudents() {
		for(Student student : this.studentList) {
			student.removeRegisteredCamp(this);
		}
		
		this.studentList.clear();
	}
	
	/**
	 * method to delete committee
	 */
	public void deleteCommittees() {
		for(Student committee : this.committeeList) {
			committee.removeCommitteeCampID();
		}
		
		this.committeeList.clear();
	}
	

	/**
	 * method to delete camp
	 */
	public void deleteCamp() {
		this.deleteStudents();
		this.deleteCommittees();
//		this.deleteEnquiries();
//		this.deleteSuggestions();
	}
}

