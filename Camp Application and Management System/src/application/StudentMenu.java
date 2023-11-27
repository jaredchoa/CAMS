package application;

import java.util.Scanner;

import applicationInterfaces.StudentMenuInterface;
import common.util.ReadChecker;
import controller.manager.StudentEnquiryHandler;
import model.*;

/** 
	 * class provides the menu for the student
	 */ 
public class StudentMenu implements StudentMenuInterface{

	
	/** 
	 * @param student student object
	 */
	public void displayMenu(Student student) {

		boolean logout = false;

		Scanner scanner = new Scanner(System.in);

		while (!logout) {

//          boolean isValidInput = false;

			int choice = 0;

			System.out.println("\nWelcome to CAMs - Students");

			System.out.println("1. View Camps"); // w/ info on remaining slots + nested menu (register as student or
													// camp comm)

			System.out.println("2. View My Camps"); // nested menu (withdraw)

			System.out.println("3. View Enquiries"); // nested menu (submit, edit, delete)

			// submit will bring up camps he is attending

			System.out.println("4. Change password");

			System.out.println("5. Logout");

			System.out.print("Please choose an option: ");

			choice = ReadChecker.checkInt();

			// Exception Handling

//          while (!isValidInput) {

//              try {

//                  choice = scanner.nextInt();

//                  scanner.nextLine(); // Consume the newline

//                  isValidInput = true;

//              } catch(Exception e) {

//                  scanner.nextLine();

//                  break;

//              }

//          }

			switch (choice) {

			case 1:

				ViewAllCamps(student, scanner);

				break;

			case 2:

				if (student.getRegisteredCamps().isEmpty()) {

					System.out.println("You have not registered for any camp!");

				}

				else {

					ViewOwnCamps(student, scanner);

				}

				break;

			case 3:

				ViewEnquiries(student, scanner);

				break;

			case 4: // change password

				System.out.print("Please enter new password: ");

				String newpassword = scanner.nextLine();

				student.setPassword(newpassword);

				System.out.println("Password changed!");

				System.out.println("Please relogin.");

				logout = true; // force logout to verify effect

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
	 * @param student student object
	 * @param scanner scanner object
	 */
	public static void ViewAllCamps(Student student, Scanner scanner) {

		mainApp.campData.printCampData(student, false); // false being view all camps available to students' faculty

		// need exception handling

		int subchoice = 0;

		while (subchoice != 2) {

			System.out.println("Please select 1 of the option below:");

			System.out.println("1. Register for a camp");

			System.out.println("2. Exit");

			subchoice = ReadChecker.checkInt();

			if (subchoice == 1) {

				System.out.println("\nInput ID of camp you wish to register for: ");

				String registerID = scanner.nextLine();

				Camp registerCamp = mainApp.campData.getCamp(registerID);

				if (student.getWithdrawnCamps().contains(registerCamp) ) {

					System.out.println("You have withdrawn from this camp and can no longer register for it.");

				}
				
				if (student.getRegisteredCamps().contains(registerCamp)) {
					System.out.println("You have already registered for this camp!");
				}

				else {

					student.addRegisteredCamps(registerCamp); // can use return int to check if successful

					registerCamp.addStudentList(student);

				}

			}

			else if (subchoice == 2)
				break;

			else {

				System.out.println("Invalid choice. Please try again.");

				continue;

			}

		}

	}

	
	/** 
	 * @param student student object
	 * @param scanner scanner object
	 */
	public static void ViewOwnCamps(Student student, Scanner scanner) {

		mainApp.campData.printCampData(student, true); // true being view student camps only

		int subchoice = 0;

		while (subchoice != 2) {

			System.out.println("Please select 1 of the option below:");

			System.out.println("1. Withdraw from a camp");

			System.out.println("2. Register for a camp's committee");

			System.out.println("3. Exit");

			subchoice = ReadChecker.checkInt();

			if (subchoice == 1) {

				System.out.print("Input ID of camp you wish to withdraw from: ");

				String withdrawID = scanner.nextLine();

				Camp withdrawCamp = mainApp.campData.getCamp(withdrawID);

				student.withdraw(withdrawCamp);

				withdrawCamp.removeStudent(student);

			}

			if (subchoice == 2) {

				if (student.getCommitteeCampID() != null) {

					System.out.println("You are already a committee member for a camp!");

					return;

				}

				if (student.getRegisteredCamps().isEmpty()) {

					System.out.println("You have not registered for any camp!");

					return;

				}

				System.out.print("Input ID of camp you wish to be part of the camp's committee: ");

				String registercommittee = scanner.nextLine();

				Camp campcomm = mainApp.campData.getCamp(registercommittee);

				if (campcomm == null) {

					System.out.println("Camp does not exist!");

					return;

				}

				for (Camp camp : student.getRegisteredCamps()) {

					if (camp.getCampID().equals(registercommittee)) {

						campcomm.setCommitteeList(student);

						student.setCommitteeCampID(campcomm.getCampID());

						System.out.print("You are now a committee member for that camp! ");

						return;

					}

				}

				System.out.println("You have not registered for that camp!");

				return;

			}

			else if (subchoice == 3)
				break;

			else {

				System.out.println("Invalid choice. Please try again.");

				continue;

			}

		}

	}

	
	/** 
	 * @param student student object
	 * @param scanner scanner object
	 */
	public static void ViewEnquiries(Student student, Scanner scanner) {

		if (student.getStudentEnquiries().isEmpty())

			System.out.println("\nNo enquiries found.\n");

		else // use count inside this if return 0 means no enquiries found

			mainApp.enquiryData.ViewByStudent(student, null);

		System.out.println("Please select 1 of the option below:");

		System.out.println("1. Submit an enquiry");

		System.out.println("2. Edit an enquiry");

		System.out.println("3. Delete an enquiry");

		System.out.println("4. Exit");

		// need exception handling

		int choice = scanner.nextInt();

		scanner.nextLine();

		switch (choice) {

		case 1:

			StudentEnquiryHandler.SubmitEnquiry(scanner, student, mainApp.campData, mainApp.enquiryData);
			break;

		case 2:

			// display all camps student registered to

			StudentEnquiryHandler.EditEnquiry(scanner, student, mainApp.campData, mainApp.enquiryData);
			break;

		case 3:

			System.out.print("Input ID of enquiry you wish to delete: ");

			String enquiryIDDelete = scanner.nextLine();

			Enquiry enquiryDelete = mainApp.enquiryData.getEnquiryByID(enquiryIDDelete);

			if (!student.getStudentEnquiries().contains(enquiryDelete)) {

				System.out.println("Invalid choice. Please try again.");

				break;

			}

			else {

				student.removeStudentEnquries(enquiryDelete);

				mainApp.campData.getCamp(enquiryDelete.getCampID()).removeEnquiry(enquiryDelete);

			}

			break;

		case 4:

			return;

		default:

			System.out.println("Invalid choice. Please try again.");

		}

	}
}