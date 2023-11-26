package model;

import java.util.*;
import common.model.*;
import controller.CriteriaCheck;

public class Student extends User{
	
	//for campcommittee
	private String CommitteeCampID = null; // Stores the ID of the camp the student is a camp committe member of
	private int CommitteePoints = -1;	
	private ArrayList<Suggestion> CommitteeSuggestions = null; 	
	//Can only be committee in 1 Camp	

	//for student
	private ArrayList<Camp> RegisteredCamps;
	private ROLE role; 		//Stores role of student in each camp
	private ArrayList<Camp> WithdrawnCamps; //Stores list of camps student has withdrawn from
	private ArrayList<Enquiry> StudentEnquiries;
	private StudentOrStaff UserType = StudentOrStaff.Student;

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

	public void addWithdrawnCamps(Camp camp) {
		WithdrawnCamps.add(camp);
	}

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
	
	public void removeRegisteredCamp(Camp camp) {
		this.RegisteredCamps.remove(camp);
	}

	public void setCommitteeCampID(String CampID) {
		CommitteePoints = 0;
		if(CommitteeCampID == "null"){
			CommitteeCampID = null;
		}
		this.CommitteeCampID = CampID;
	}

	public String getCommitteeCampID() {
		return CommitteeCampID;
	}
	
	public void removeCommitteeCampID() {
		this.CommitteeCampID = null;
	}

	public StudentOrStaff getUserType() {
		return UserType;
	}

	public ArrayList<Enquiry> getStudentEnquiries() {
		return StudentEnquiries;
	}

	public void addStudentEnquiries(Enquiry enquiry) {
		ArrayList<Enquiry> enquiryList = this.getStudentEnquiries();
		enquiryList.add(enquiry);
		this.setStudentEnquiries(enquiryList);
	}
	
	public void setStudentEnquiries(ArrayList<Enquiry> studentEnquiries) {
		StudentEnquiries = studentEnquiries;
	}

	public void removeStudentEnquries(Enquiry enquiry) {
		this.StudentEnquiries.remove(enquiry);
	}

	public int getPoints() {
		return CommitteePoints;
	}

	public int addPoints()
	{
		if (CommitteePoints != -1)
		CommitteePoints++;
		return CommitteePoints;
	}

	public ArrayList<Suggestion> getCommitteeSuggestions() {
		return CommitteeSuggestions;
	}

	public void addCommitteeSuggestions(Suggestion suggestion) {
		CommitteeSuggestions.add(suggestion);
    }
	
	public void removeCommitteeSuggestions(Suggestion suggestion) {
		CommitteeSuggestions.remove(suggestion);
	}
	
	public void withdraw(Camp camp) {
		this.removeRegisteredCamp(camp);
		this.addWithdrawnCamps(camp);
		System.out.println("Successfully withdrawn!");
	}

}
