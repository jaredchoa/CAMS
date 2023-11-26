package model;

import java.util.Map;

import common.model.SuggestionStatus;

public class Suggestion {
	public static int SUGGESTIONCOUNTER = 1;
	private String SuggestionID;
	private String Suggestion;
	private User CreatedBy;
	private Staff approvedBy;
	private SuggestionStatus status;
	private String campID;
	
	public Suggestion(String suggID, String Suggestion, User CreatedBy, String campID, Staff approvedBy) {
		this.SuggestionID = suggID;
		this.Suggestion = Suggestion;
		this.CreatedBy = CreatedBy;
		this.campID = campID;
		this.approvedBy = approvedBy;
		this.status = SuggestionStatus.REJECTED;
		SUGGESTIONCOUNTER++;
	}

	public Suggestion(String Suggestion, User CreatedBy, String campID) {
		this.SuggestionID = "S" + SUGGESTIONCOUNTER;
		this.Suggestion = Suggestion;
		this.CreatedBy = CreatedBy;
		this.campID = campID;
		this.approvedBy = null;
		this.status = SuggestionStatus.REJECTED;
		SUGGESTIONCOUNTER++;
	}
	
	public String getSuggestion() {
		return Suggestion;
	}

	public void setSuggestion(String suggestion) {
		Suggestion = suggestion;
	}

	public User getCreatedBy() {
		return CreatedBy;
	}

//	public void setCreatedBy(stduent createdBy) {
//		CreatedBy = createdBy;
//	}

	public String getSuggestionID() {
		return SuggestionID;
	}

	public void setSuggestionID(String suggestionID) {
		SuggestionID = suggestionID;
	}

	public Staff getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Staff approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getCampID() {
		return campID;
	}

	public void setCampID(String campID) {
		this.campID = campID;
	}

	public SuggestionStatus getStatus() {
		return status;
	}

	public void setStatus(SuggestionStatus status) {
		this.status = status;
	}

	public void setCreatedBy(User createdBy) {
		CreatedBy = createdBy;
	}

	public void printSuggestionStaff() {
		System.out.println("\nSuggestionID: " + this.getSuggestionID() + "\nSuggestion: " + this.getSuggestion()
				+ "\nCreated by: " + this.getCreatedBy().getUserID());
	}

	public void printSuggestionStudent() {
		System.out.println("\nSuggestionID: " + this.getSuggestionID() + "\nSuggestion: " + this.getSuggestion());
		if (this.status!=SuggestionStatus.APPROVED) {
			System.out.println("Approved by: " + this.getApprovedBy().getUserName());
		} else {
			System.out.println("Suggestion has not been approved yet");
		}
	}

}
