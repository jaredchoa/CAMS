package Handlers;

import java.util.ArrayList;
import java.util.Scanner;

import application.mainApp;
import controller.manager.CampManager;
import controller.manager.SuggestionManager;
import model.Camp;
import model.Student;
import model.Suggestion;

/** 
 * class to handle committee members
 */ 
public class CommitteeHandler  {

	/** 
	 * @param scanner scanner object
	 * @param student student object
	 * @param SuggestionData SuggestionManager object
	 * @param CampData CampManager object
	 */
	public static void EditSuggestion(Scanner scanner, Student student, SuggestionManager SuggestionData) {
		// TODO Auto-generated method stub
		System.out.print("Input the ID of suggestion you wish to edit: ");

		String suggestionIDSubmit = scanner.nextLine();

		Suggestion suggestion2 = SuggestionData.getSuggestionByID(suggestionIDSubmit);

		if (suggestion2 == null) {

			System.out.println("Suggestion doesn't exist");

			return;

		}

		if (suggestion2.getApprovedBy() != null) {

			System.out.println("You cannot edit this suggestion as it has been approved.");

			return;

		}

		else if (suggestion2.getApprovedBy() == null) {

			System.out.print("Enter your new suggestion: ");

			String suggestionSubmit2 = scanner.nextLine();

			suggestion2.setSuggestion(suggestionSubmit2);

			System.out.println("Your suggestion has been successfully edited.");

		}
	}

	/** 
	 * @param scanner scanner object
	 * @param student student object
	 * @param SuggestionData SuggestionManager object
	 * @param CampData CampManager object
	 */
	public static void DeleteSuggestion(Scanner scanner, Student student, SuggestionManager SuggestionData,
			CampManager CampData) {
		// TODO Auto-generated method stub
		String campcommID = student.getCommitteeCampID();

		Camp committeecamp = CampData.getCamp(campcommID);
		System.out.print("Input the ID of suggestion you wish to delete: ");

		String suggestionIDSubmit3 = scanner.nextLine();

		Suggestion suggestion3 = SuggestionData.getSuggestionByID(suggestionIDSubmit3);

		if (suggestion3 == null) {

			System.out.println("Suggestion doesn't exist");

			return;

		}

		if (suggestion3.getApprovedBy() != null) {

			System.out.println("You cannot edit this suggestion as it has been approved.");

			return;

		}

		else {

			ArrayList<Suggestion> suggestionlist = committeecamp.getSuggestionList();

			suggestionlist.remove(suggestion3);

			committeecamp.setSuggestionList(suggestionlist);

			student.removeCommitteeSuggestions(suggestion3);

			System.out.println("Your suggestion has been successfully deleted.");

		}
	
	}

	/** 
	 * @param scanner scanner object
	 * @param student student object
	 * @param SuggestionData SuggestionManager object
	 * @param CampData CampManager object
	 */
	public static void SubmitSuggestion(Scanner scanner, Student student, SuggestionManager SuggestionData,
			CampManager CampData) {
		// TODO Auto-generated method stub
		String campcommID = student.getCommitteeCampID();

		Camp committeecamp = CampData.getCamp(campcommID);
		System.out.print("Enter your suggestion: ");

		String suggestionSubmit = scanner.nextLine();

		Suggestion suggestion1 = SuggestionData.addSuggestion(suggestionSubmit, student, student.getCommitteeCampID());

		student.addCommitteeSuggestions(suggestion1);

		committeecamp.setSuggestionList(suggestion1);

		System.out.println("\nYour suggestion (ID: " + suggestion1.getSuggestionID()
				+ ") has been successfully submitted.\n");
	}

	

}
