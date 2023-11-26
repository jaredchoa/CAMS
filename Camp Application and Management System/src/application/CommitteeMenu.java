package application;

import java.util.Scanner;

import common.util.ReadChecker;

import model.*;

import java.util.*;

public class CommitteeMenu {

	protected static void displayMenu(Student student) {

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

	private static void viewDetailsofCamp(Student student) {

		String campcommID = student.getCommitteeCampID(); // dont need exception because if theres no commcampID user
															// wont even be able to get to this page

		mainApp.campData.getCamp(campcommID).printCamp();

	}

	private static void viewSuggestions(Student student, Scanner scanner) {

		String campcommID = student.getCommitteeCampID();

		Camp committeecamp = mainApp.campData.getCamp(campcommID);

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

				System.out.print("Enter your suggestion: ");

				String suggestionSubmit = scanner.nextLine();

				Suggestion suggestion1 = mainApp.suggestionData.addSuggestion(suggestionSubmit, student, campcommID);

				student.addCommitteeSuggestions(suggestion1);

				committeecamp.setSuggestionList(suggestion1);

				System.out.println("\nYour suggestion (ID: " + suggestion1.getSuggestionID()
						+ ") has been successfully submitted.\n");

				printSuggestions(suggestionList);

				break;

			case 2:

				System.out.print("Input the ID of suggestion you wish to edit: ");

				String suggestionIDSubmit = scanner.nextLine();

				Suggestion suggestion2 = mainApp.suggestionData.getSuggestionByID(suggestionIDSubmit);

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

				printSuggestions(suggestionList);

				break;

			case 3:

				System.out.print("Input the ID of suggestion you wish to delete: ");

				String suggestionIDSubmit3 = scanner.nextLine();

				Suggestion suggestion3 = mainApp.suggestionData.getSuggestionByID(suggestionIDSubmit3);

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

				printSuggestions(suggestionList);

				break;

			case 4:

				break;

			default:

				System.out.println("Invalid choice. Please try again.");

			}

		}

	}

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

					System.out.print("Input the ID of enquiry you wish to reply: ");

					String enquiryIDSubmit = scanner.nextLine();

					Enquiry enquiry = mainApp.enquiryData.getEnquiryByID(enquiryIDSubmit); // if ID is invalid return

					if (enquiry == null) {

						System.out.println("Invalid ID, Enquiry doesn't exist.");

					}
					else {
						System.out.print("Enter your reply: ");

						String enquirySubmit = scanner.nextLine();

						enquiry.setReply(enquirySubmit);

						enquiry.printEnquiry();

						System.out.println("The enquiry with an ID of " + enquiry.getEnquiryID()
								+ "has been successfully replied to.");

						break;
					}


				case 2:

					return;

				default:

					System.out.println("Invalid choice. Please try again.");

				}

			}

		}

	}

	public static void generateListofAttendingStudents(Student student, Scanner scanner) { // still need to add in the
																							// write file part

		System.out.println("Do you want to generate a list of:");

		System.out.println("1. All Attendees");

		System.out.println("2. Camp Committee only");

		System.out.println("3. Non-Committee Attendees");

		System.out.println("4. Back");

		ArrayList<Student> attendees = new ArrayList<Student>();

		ArrayList<Student> committeeMembers = new ArrayList<Student>();

		ArrayList<Student> allStudents = new ArrayList<Student>();

		String committeeID = student.getCommitteeCampID();

		Camp attendeeCamp = mainApp.campData.getCamp(committeeID);

		attendees = attendeeCamp.getStudentList();

		allStudents = attendees;

		Camp committeecamp = mainApp.campData.getCamp(committeeID);

		committeeMembers = committeecamp.getCommitteeList();

		// need exception handling

		int choice = ReadChecker.checkInt();

		switch (choice) {

		case 1:

			if (allStudents.isEmpty()) {

				System.out.println("Noone is attending this camp.");

				return;

			}

			System.out.println("These are the all the attendees:");

			for (int i = 0; i < allStudents.size(); i++) {

				System.out.println((i + 1) + ")" + allStudents.get(i).getUserName());

				if (allStudents.get(i).getCommitteeCampID() == null) {

					System.out.print(" Role: Attendee\n");

					continue;

				}

				else if (allStudents.get(i).getCommitteeCampID().equals(committeeID)) {

					System.out.print(" Role: Committee Member\n");

				}

			}

			break;

		case 2:

			if (committeeMembers.isEmpty()) {

				System.out.println("There are no Committee Members for this camp.");

				return;

			}

			System.out.println("These are the Committee Members:");

			for (int i = 0; i < committeeMembers.size(); i++) {

				System.out.println((i + 1) + ")" + committeeMembers.get(i).getUserName() + "Role: Committee Member");

			}

			break;

		case 3:

			if (attendees.isEmpty()) {

				System.out.println("There are no normal attendees for this camp.");

				return;

			}

			System.out.println("These are the non-Committee Member attendees:");

			for (int i = 0; i < attendees.size(); i++) { // Filter out to just the students excluding committee members

				for (int j = 0; j < committeeMembers.size(); j++) {

					if (attendees.get(i).getUserID().equals(committeeMembers.get(j).getUserID())) {

						attendees.remove(i);

					}

				}

			}

			for (int i = 0; i < attendees.size(); i++) {

				System.out.println((i + 1) + ")" + attendees.get(i).getUserName() + "\n Role: Attendee");

			}

			break;

		case 4:

			return;

		}

		// try {

		// FileWriter myWriter = new FileWriter("filename.txt");

		// myWriter.write("Files in Java might be tricky, but it is fun enough!");

		// myWriter.close();

		// System.out.println("Successfully wrote to the file.");

		// } catch (IOException e) {

		// System.out.println("An error occurred.");

		// e.printStackTrace();

		// }

	}

}