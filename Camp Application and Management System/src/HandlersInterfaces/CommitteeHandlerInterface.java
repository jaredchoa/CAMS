package HandlersInterfaces;

import java.util.Scanner;

import controller.manager.CampManager;
import controller.manager.SuggestionManager;
import model.Student;

public interface CommitteeHandlerInterface {
	public void SubmitSuggestion(Scanner scanner, Student student, SuggestionManager SuggestionData,
			CampManager CampData);
	public void EditSuggestion(Scanner scanner, Student student, SuggestionManager SuggestionData);
	public void DeleteSuggestion(Scanner scanner, Student student, SuggestionManager SuggestionData,
			CampManager CampData);
}
