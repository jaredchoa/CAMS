package Handlers;

import java.util.Scanner;

import HandlersInterfaces.StaffSuggestionHandlerInterface;
import common.model.SuggestionStatus;
import controller.manager.SuggestionManager;
import model.Staff;
import model.Student;
import model.Suggestion;

/** 
 * class to handle staff suggestions
 */
public class StaffSuggestionHandler implements StaffSuggestionHandlerInterface {

	/** 
	 * @param scanner scanner object
	 * @param staff staff object
	 * @param SuggestionData SuggestionManager object
	 */
	public void ApproveSuggestion(Scanner scanner, Staff staff, SuggestionManager SuggestionData) {
		System.out.println("Input ID of suggestion you wish to approve: ");
		String suggestionID = scanner.nextLine();
		Suggestion suggestionApprove = SuggestionData.getSuggestionByID(suggestionID);
		if(suggestionApprove.getCreatedBy() instanceof Student) {
			Student createdBy = (Student) suggestionApprove.getCreatedBy();
			createdBy.addPoints();
		}
		if(staff.getCreatedCamp().getSuggestionList().contains(suggestionApprove)==false) {
			System.out.println("Invalid Suggestion ID");
		}
		else {
			suggestionApprove.setApprovedBy(staff);
			suggestionApprove.setStatus(SuggestionStatus.APPROVED);
		}
	}

}
