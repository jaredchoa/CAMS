package HandlersInterfaces;

import java.util.Scanner;

import controller.manager.SuggestionManager;
import model.Staff;

/**
 * staff suggestion handler interface
 */
public interface StaffSuggestionHandlerInterface {
	/**
	 * @param scanner scanner object
	 * @param staff staff
	 * @param SuggestionData suggestion manager object with all suggestion data
	 */
	public void ApproveSuggestion(Scanner scanner, Staff staff, SuggestionManager SuggestionData);
}
