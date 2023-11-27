package HandlersInterfaces;

import java.util.Scanner;

import controller.manager.CampManager;
import controller.manager.SuggestionManager;
import model.Student;

/**
 * interface for committee handler
 */
public interface CommitteeHandlerInterface {
	/**
	 * @param scanner scanner object
	 * @param student student submitting suggeston
	 * @param SuggestionData all suggestion data
	 * @param CampData all suggestion input
	 */
	public void SubmitSuggestion(Scanner scanner, Student student, SuggestionManager SuggestionData,
			CampManager CampData);
	/**
	 * @param scanner scanner object
	 * @param student student editting suggeston
	 * @param SuggestionData all suggestion data
	 */
	public void EditSuggestion(Scanner scanner, Student student, SuggestionManager SuggestionData);
	/**
	 * @param scanner scanner object
	 * @param student student
	 * @param SuggestionData all suggestion data
	 * @param CampData all camp data
	 */
	public void DeleteSuggestion(Scanner scanner, Student student, SuggestionManager SuggestionData,
			CampManager CampData);
}
