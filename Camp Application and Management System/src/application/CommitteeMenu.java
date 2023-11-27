package application;

import java.util.Scanner;

import Handlers.CommitteeHandler;
import Handlers.CommitteeStaffEnquiryHandler;
import HandlersInterfaces.*;
import applicationInterfaces.*;
import common.util.ReadChecker;
import fileIO.writeReport;
import model.*;

import java.util.*;

	/** 
	 * class provides the menu for the committee member
	 */ 
public class CommitteeMenu implements StudentMenuInterface{
	public static CommitteeHandlerInterface committeeHandler = new CommitteeHandler();
	public static CommitteeStaffEnquiryHandlerInterface committeeStaffEnquiry = new CommitteeStaffEnquiryHandler();
	
	/** 
	 * @param student student object
	 */ 
	public void displayMenu(Student student) {

		boolean logout = false;

		Scanner scanner = new Scanner(System.in);

		ReadChecker ReadChecker = new ReadChecker();

		while (!logout) {

			boolean isValidInput = false;

			int choice = 0;

			System.out.println("\nWelcome to CAMs - Committee Member");

			System.out.println("1. View Details of Camp"); // submit suggestions?

			System.out.println("2. View Suggestions"); // submit, edit, delete

			System.out.println("3. View Enquiries"); // reply

			System.out.println("4. Generate List of Attending Students"); // filter (csv or txt format)

			System.out.println("5. Logout");

			System.out.print("Please choose an option: ");

			// Exception Handling

			while (!isValidInput) {

				try {

					choice = scanner.nextInt();

					scanner.nextLine(); // Consume the newline

					isValidInput = true;

				} catch (Exception e) {

					scanner.nextLine();

					break;

				}

			}

			switch (choice) {

			case 1:

				viewDetailsofCamp(student);

				break;

			case 2:

				viewSuggestions(student, scanner);

				break;

			case 3:

				viewEnquiries(student, scanner);

				break;

			case 4:

				generateListofAttendingStudents(student, scanner);

				break;

			case 5:

				logout = true;

				break;

			default:

				System.out.println("Invalid choice. Please try again.");

			}

		}

	}

	
	/** 
	 * @param student Student object
	 */
	private static void viewDetailsofCamp(Student student) {

		String campcommID = student.getCommitteeCampID(); // dont need exception because if theres no commcampID user
															// wont even be able to get to this page

		mainApp.campData.getCamp(campcommID).printCamp();

	}

	
	/** 
	 * @param student Student object
	 * @param scanner Scanner object
	 */
	 
	private static void viewSuggestions(Student student, Scanner scanner) {

		ArrayList<Suggestion> suggestionList = student.getCommitteeSuggestions();

		printSuggestions(suggestionList);

		int subchoice = 0;

		while (subchoice != 4) {

			System.out.println("1. Submit Suggestion");

			System.out.println("2. Edit Suggestion");

			System.out.println("3. Delete Suggestion");

			System.out.println("4. Back");

			// need exception handling

			subchoice = ReadChecker.checkInt();

			switch (subchoice) {

			case 1:
				committeeHandler.SubmitSuggestion(scanner, student, mainApp.suggestionData, mainApp.campData);
				printSuggestions(suggestionList);

				break;

			case 2:
				committeeHandler.EditSuggestion(scanner, student, mainApp.suggestionData);
				printSuggestions(suggestionList);

				break;

			case 3:
				committeeHandler.DeleteSuggestion(scanner, student, mainApp.suggestionData, mainApp.campData);
				printSuggestions(suggestionList);

				break;

			case 4:

				break;

			default:

				System.out.println("Invalid choice. Please try again.");

			}

		}

	}

	
	/** 
	 * @param SuggestionList ArrayList of Suggestion objects
	 */
	private static void printSuggestions(ArrayList<Suggestion> SuggestionList) {

		if (SuggestionList.isEmpty()) {

			System.out.println("\nThere are no suggestions currently.");

		}

		else {

			System.out.println("These are your suggestions:");

			System.out.println("Suggestion - Suggestion ID");

			for (int i = 0; i < SuggestionList.size(); i++) {

				System.out.println((i + 1) + ") " + SuggestionList.get(i).getSuggestion() + " - "
						+ SuggestionList.get(i).getSuggestionID());

				if (SuggestionList.get(i).getApprovedBy() != null)

					System.out.println("Status: Approved");

				else

					System.out.println("Status: Pending");

			}

			System.out.println("\n");

		}

	}

	
	/** 
	 * @param student Student object
	 * @param scanner Scanner object
	 */
	private static void viewEnquiries(Student student, Scanner scanner) {

		String campcommID = student.getCommitteeCampID();

		Camp committeecamp = mainApp.campData.getCamp(campcommID);

		if (committeecamp.getEnquiriesList().isEmpty()) {

			System.out.println("There are no enquiries currently.");

			return;

		}

		else {

			System.out.println("These are the enquiries for your camp:");

			mainApp.enquiryData.ViewByCommittee(committeecamp);

			int subchoice = 0;

			while (subchoice != 2) {

				System.out.println("1. Reply Enquiry");

				System.out.println("2. Back");

				// need exception handling

				subchoice = ReadChecker.checkInt();

				switch (subchoice) {

				case 1:
					committeeStaffEnquiry.ReplyEnquiry(scanner, mainApp.enquiryData);
					break;

				case 2:
					return;

				default:

					System.out.println("Invalid choice. Please try again.");

				}

			}

		}

	}
	/** 
	 * @param student Student object
	 * @param scanner Scanner object
	 */
	public static void generateListofAttendingStudents(Student student, Scanner scanner) { // still need to add in the
																							// write file part

		System.out.println("Do you want to generate a list of:");

		System.out.println("1. All Attendees");

		System.out.println("2. Camp Committee only");

		System.out.println("3. Non-Committee Attendees");

		System.out.println("4. Back");
		int choice = ReadChecker.checkInt();
		Camp camp = mainApp.campData.getCamp(student.getCommitteeCampID());

		writeReport.generateCommitteeReport(camp, student, scanner, choice);

	}

}