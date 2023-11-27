package model;

import java.util.Map;

import common.model.SuggestionStatus;

/** 
 * class stores information for suggestion
 */
public class Suggestion {
	/**
	 * used for suggestion unique ID
	 */
	public static int SUGGESTIONCOUNTER = 1;
	private String SuggestionID;
	private String Suggestion;
	private User CreatedBy;
	private Staff approvedBy;
	private SuggestionStatus status;
	private String campID;
	
	/**
	 * @param suggID suggestion ID
	 * @param Suggestion suggestion
	 * @param CreatedBy user who created the suggestion
	 * @param campID camp ID
	 * @param approvedBy staff who approved the suggestion
	 */
	public Suggestion(String suggID, String Suggestion, User CreatedBy, String campID, Staff approvedBy) {
		this.SuggestionID = suggID;
		this.Suggestion = Suggestion;
		this.CreatedBy = CreatedBy;
		this.campID = campID;
		this.approvedBy = approvedBy;
		this.status = SuggestionStatus.REJECTED;
		SUGGESTIONCOUNTER++;
	}

	/**
	 * @param Suggestion suggestion
	 * @param CreatedBy user who created the suggestion
	 * @param campID camp ID
	 */
	public Suggestion(String Suggestion, User CreatedBy, String campID) {
		this.SuggestionID = "S" + SUGGESTIONCOUNTER;
		this.Suggestion = Suggestion;
		this.CreatedBy = CreatedBy;
		this.campID = campID;
		this.approvedBy = null;
		this.status = SuggestionStatus.REJECTED;
		SUGGESTIONCOUNTER++;
	}
	
	
	/** 
	 * @return String Suggestion
	 */
	public String getSuggestion() {
		return Suggestion;
	}

	
	/** 
	 * @param suggestion Suggestion
	 */
	public void setSuggestion(String suggestion) {
		Suggestion = suggestion;
	}

	
	/** 
	 * @return User object
	 */
	public User getCreatedBy() {
		return CreatedBy;
	}



//	public void setCreatedBy(stduent createdBy) {
//		CreatedBy = createdBy;
//	}
/** 
 * @return String SuggestionID
 */
	public String getSuggestionID() {
		return SuggestionID;
	}

	
	/** 
	 * @param suggestionID SuggestionID
	 */
	public void setSuggestionID(String suggestionID) {
		SuggestionID = suggestionID;
	}

	
	/** 
	 * @return Staff object
	 */
	public Staff getApprovedBy() {
		return approvedBy;
	}

	
	/** 
	 * @param approvedBy Staff object
	 */
	public void setApprovedBy(Staff approvedBy) {
		this.approvedBy = approvedBy;
	}

	
	/** 
	 * @return String campID
	 */
	public String getCampID() {
		return campID;
	}

	
	/** 
	 * @param campID campID
	 */
	public void setCampID(String campID) {
		this.campID = campID;
	}

	
	/** 
	 * @return SuggestionStatus object
	 */
	public SuggestionStatus getStatus() {
		return status;
	}

	
	/** 
	 * @param status SuggestionStatus object
	 */
	public void setStatus(SuggestionStatus status) {
		this.status = status;
	}

	
	/** 
	 * @param createdBy User object
	 */
	public void setCreatedBy(User createdBy) {
		CreatedBy = createdBy;
	}

	/**
	 * print suggestion for staff view
	 */
	public void printSuggestionStaff() {
		System.out.println("\nSuggestionID: " + this.getSuggestionID() + "\nSuggestion: " + this.getSuggestion()
				+ "\nCreated by: " + this.getCreatedBy().getUserID());
	}

	/**
	 * print suggestion for student view
	 */
	public void printSuggestionStudent() {
		System.out.println("\nSuggestionID: " + this.getSuggestionID() + "\nSuggestion: " + this.getSuggestion());
		if (this.status!=SuggestionStatus.APPROVED) {
			System.out.println("Approved by: " + this.getApprovedBy().getUserName());
		} else {
			System.out.println("Suggestion has not been approved yet");
		}
	}

}
