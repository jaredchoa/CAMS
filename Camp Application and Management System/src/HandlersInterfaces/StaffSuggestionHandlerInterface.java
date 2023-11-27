package HandlersInterfaces;

import java.util.Scanner;

import controller.manager.SuggestionManager;
import model.Staff;

public interface StaffSuggestionHandlerInterface {
	public void ApproveSuggestion(Scanner scanner, Staff staff, SuggestionManager SuggestionData);
}
