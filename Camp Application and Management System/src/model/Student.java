package model;

import java.util.*;
import common.model.*;
import controller.CriteriaCheck;

/** 
 * class stores information for Student and committee member
 */
public class Student extends User{
	
	//for campcommittee
	private String CommitteeCampID = null; // Stores the ID of the camp the student is a camp committe member of
	private int CommitteePoints = -1;	//-1 for students; 1 added to make it 0 for committee
	private ArrayList<Suggestion> CommitteeSuggestions = null; 	
	//Can only be committee in 1 Camp	

	//for student
	private ArrayList<Camp> RegisteredCamps;
	private ROLE role; 		//Stores role of student in each camp
	private ArrayList<Camp> WithdrawnCamps; //Stores list of camps student has withdrawn from
	private ArrayList<Enquiry> StudentEnquiries;
	private StudentOrStaff UserType = StudentOrStaff.Student;

	/**
	 * @param UserID student's ID
	 * @param UserName student's name
	 * @param UserFaculty student's faculty
	 * @param password student's password
	 * @param committeeCampID student's committee camp ID
	 */
	public Student(String UserID, String UserName, Faculty UserFaculty, String password, String committeeCampID) {
		super(UserID, UserName, UserFaculty, password);
		this.StudentEnquiries = new ArrayList<Enquiry>();
		this.RegisteredCamps = new ArrayList<Camp>();
		this.WithdrawnCamps = new ArrayList<Camp>();
		this.CommitteeSuggestions = new ArrayList<Suggestion>();
		this.CommitteeCampID = committeeCampID;
	}
	
	
	

	public ArrayList<Camp> getWithdrawnCamps() {
		return WithdrawnCamps;
	}

	
	/** 
	 * @param camp camp object
	 */
	public void addWithdrawnCamps(Camp camp) {
		WithdrawnCamps.add(camp);
	}

	
	/** 
	 * @return ROLE role of student in each camp
	 */
	public ROLE getRole() {
		if  (CommitteeCampID == null){
			role = ROLE.Committee;
		}
		else{
			role = ROLE.Attendee;
		}		
		return role;
	}

	

	public ArrayList<Camp> getRegisteredCamps() {
		return RegisteredCamps;
	}

	
	/** 
	 * @param camp camp object
	 * @return boolean true if student can register for camp, false otherwise
	 */
	public boolean addRegisteredCamps(Camp camp) {
		CriteriaCheck x = new CriteriaCheck();
		boolean canRegister = x.CanStudentRegister(camp, this);
		if(canRegister) {
			RegisteredCamps.add(camp);
			System.out.println("Successfully registered!");	
			return true;
		}
		else {
			System.out.println("Can not register! Sorry!");
			return false;
		}
	}
	
	
	/** 
	 * @param camp camp object
	 */
	public void removeRegisteredCamp(Camp camp) {
		this.RegisteredCamps.remove(camp);
	}

	
	/** 
	 * @param CampID camp ID
	 */
	public void setCommitteeCampID(String CampID) {
		CommitteePoints = 0;
		if(CommitteeCampID == "null"){
			CommitteeCampID = null;
		}
		this.CommitteeCampID = CampID;
	}

	
	/** 
	 * @return String camp ID
	 */
	public String getCommitteeCampID() {
		return CommitteeCampID;
	}
	
	/**
	 * unassign commitee memebr
	 */
	public void removeCommitteeCampID() {
		this.CommitteeCampID = null;
	}

	
	/** 
	 * @return StudentOrStaff student or staff
	 */
	public StudentOrStaff getUserType() {
		return UserType;
	}

	

	public ArrayList<Enquiry> getStudentEnquiries() {
		return StudentEnquiries;
	}

	
	/** 
	 * @param enquiry enquiry object
	 */
	public void addStudentEnquiries(Enquiry enquiry) {
		ArrayList<Enquiry> enquiryList = this.getStudentEnquiries();
		enquiryList.add(enquiry);
		this.setStudentEnquiries(enquiryList);
	}
	
	
	/** 
	 * @param studentEnquiries student enquiries
	 */
	public void setStudentEnquiries(ArrayList<Enquiry> studentEnquiries) {
		StudentEnquiries = studentEnquiries;
	}

	
	/** 
	 * @param enquiry enquiry object
	 */
	public void removeStudentEnquries(Enquiry enquiry) {
		this.StudentEnquiries.remove(enquiry);
	}

	
	/** 
	 * @return int committee points
	 */
	public int getPoints() {
		return CommitteePoints;
	}

	
	/** 
	 * @return int committee points
	 */
	public int addPoints()
	{
		if (CommitteePoints != -1)
		CommitteePoints++;
		return CommitteePoints;
	}
	
	public int removePoints()
	{
		if (CommitteePoints > 0)
		CommitteePoints--;
		return CommitteePoints;
	}

	

	public ArrayList<Suggestion> getCommitteeSuggestions() {
		return CommitteeSuggestions;
	}

	
	/** 
	 * @param suggestion suggestion object
	 */
	public void addCommitteeSuggestions(Suggestion suggestion) {
		CommitteeSuggestions.add(suggestion);
    }
	
	
	/** 
	 * @param suggestion suggestion object
	 */
	public void removeCommitteeSuggestions(Suggestion suggestion) {
		CommitteeSuggestions.remove(suggestion);
	}
	
	
	/** 
	 * @param camp camp object
	 */
	public void withdraw(Camp camp) {
		this.removeRegisteredCamp(camp);
		this.addWithdrawnCamps(camp);
		System.out.println("Successfully withdrawn!");
	}

}
