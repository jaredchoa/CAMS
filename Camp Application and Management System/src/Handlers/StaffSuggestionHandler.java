package Handlers;

import java.util.Scanner;

import application.mainApp;
import common.model.SuggestionStatus;
import controller.manager.SuggestionManager;
import model.Staff;
import model.Suggestion;

public class StaffSuggestionHandler {

	public static void ApproveSuggestion(Scanner scanner, Staff staff, SuggestionManager SuggestionData) {
		System.out.println("Input ID of suggestion you wish to approve: ");
		String suggestionID = scanner.nextLine();
		Suggestion suggestionApprove = SuggestionData.getSuggestionByID(suggestionID);
		if(staff.getCreatedCamp().getSuggestionList().contains(suggestionApprove)==false) {
			System.out.println("Invalid Suggestion ID");
		}
		else {
			suggestionApprove.setApprovedBy(staff);
			suggestionApprove.setStatus(SuggestionStatus.APPROVED);
		}
	}

}
