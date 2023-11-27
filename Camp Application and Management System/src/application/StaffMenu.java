package application;

import java.util.Scanner;

import Handlers.*;
import HandlersInterfaces.*;
import applicationInterfaces.StaffMenuInterface;
import model.*;
import common.util.*;
import common.model.*;
import java.time.*;
import java.time.format.*;
import fileIO.*;

/** 
	 * class provides the menu for the Staff
	 */ 
public class StaffMenu implements StaffMenuInterface{
	public static StaffCampHandlerInterface staffCampHandler = new StaffCampHandler();
	public static CommitteeStaffEnquiryHandlerInterface committeeStaffEnquiry = new CommitteeStaffEnquiryHandler();
	public static StaffSuggestionHandlerInterface staffSuggestionHandler = new StaffSuggestionHandler();
	
	/** 
	 * @param staff staff object
	 */
	public void displayMenu(Staff staff) {
		boolean logout = false;
		Scanner scanner = new Scanner(System.in);

		while (!logout) {
//			boolean isValidInput = false;
			int choice = 0;
			System.out.println("\n+------------------------------------------------------------+");
			System.out.println("Welcome to CAMs - Staff");
			System.out.println("1. Access Camps");		//from inside -> edit, create or delete (can toggle)
														//view all or own camps -> if (view own) can generate student report (w/ filter) + performance report of committee
			System.out.println("2. View Enquiries");
			System.out.println("3. View Suggestions");  //View all suggestions -> can accept or reject suggestions
			System.out.println("4. Change password");
			System.out.println("5. Generate staff report");
			System.out.println("6. Generate Performance Report");
			System.out.println("7. Logout");
			System.out.println("+------------------------------------------------------------+");
			System.out.print("Please choose an option: ");
			
			choice = ReadChecker.checkInt();
			
			switch (choice) {
			case 1:
				ViewAllCamps(staff, scanner);
				break;
			case 2:
				if (staff.getCreatedCamp() == null) {
					System.out.println("You have not created a camp!");
				}
				else {
					ViewEnquiries(staff, scanner);
				}
				
				break;
			case 3:
				if (staff.getCreatedCamp() == null) {
					System.out.println("You have not created a camp!");
				}
				else {
					ViewAllSuggestions(staff, scanner);
				}
				break;
			case 4: //change password
				System.out.println("Please enter new password: ");
				staff.changePassword();
				System.out.println("Please relogin.");
				logout = true; // force logout to verify effect
				break;
			case 5:	//generate staff report
				generateReport(staff, scanner);
				break;
			case 6:
				controller.CommitteePerformanceReport.GenerateReport(staff.getCreatedCamp());
				break;
			case 7:
				logout = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	
	/** 
	 * @param staff staff object
	 * @param scanner scanner object
	 */
	private static void generateReport(Staff staff, Scanner scanner) {
		int choice = 0;
		System.out.println("Please choose an option:");
		System.out.println("1. Generate all students");
		System.out.println("2. Generate all committee members");
		System.out.println("3. Generate all non-committee members");
		choice = ReadChecker.checkInt();
		writeReport.generateStaffReport(staff, scanner, choice);
	}


	
	/** 
	 * @param staff staff object
	 * @param scanner scanner object
	 */
	 
	public static void ViewAllCamps(Staff staff, Scanner scanner){
		//mainApp.campData.printCampData(staff, true);		//false being view all camps available to students' faculty
		
		int subchoice = 0;

			while (subchoice != 4) {
				System.out.println("Please choose an option:");
				System.out.println("1. Create Camp");
				System.out.println("2. View all Camps");
				System.out.println("3. Manage your Camp");
				System.out.println("4. Back");
				subchoice = ReadChecker.checkInt();

				switch(subchoice) {
				case 1:
					staffCampHandler.CreateCamp(scanner, staff, mainApp.campData);
					break;
				case 2:
					mainApp.campData.printCampData(staff, true);
					break;
				case 3:
					if (staff.getCreatedCamp() != null)
						manageOwnCamp(staff, scanner);
					else
						System.out.println("You have not created any camp!");
					break;
				case 4:
					break;
				default:
					System.out.println("Invalid choice. Please try again.");

				}
			}
	}
	
	
	/** 
	 * @param staff staff object
	 * @param scanner scanner object
	 */
	 
	private static void manageOwnCamp(Staff staff, Scanner scanner) {		
		int subchoice=0;
		
		String confirm;
		
		while (subchoice != 4) {
			System.out.println("Manage your Camp:\n"
					+ "1. View Camp Details\n"
					+ "2. Edit Camp Details\n"
					+ "3. Delete Camp\n"
					+ "4. Back");
			subchoice = ReadChecker.checkInt();
			switch(subchoice) {
			case 1:
				staff.viewCreatedCamp();
				break;
			case 2:
				staffCampHandler.EditCamp(scanner, staff);
				break;
				case 3:
				staffCampHandler.DeleteCamp(scanner, staff, mainApp.campData);
				break;
			case 4:
				break;
			default:
				System.out.println("Please enter a valid option:");

			}
		}
		
	}

		
	
	/** 
	 * @param staff staff object
	 * @param scanner scanner object
	 */
	 
	public static void ViewAllSuggestions(Staff staff, Scanner scanner) {
		Camp staffCamp = staff.getCreatedCamp();
		if(staffCamp.getSuggestionList()==null) {
			System.out.println("There are no suggestions in this camp!");
			return;
		}
		else {
			System.out.println("Current suggestions: ");
			for(Suggestion suggestion : staffCamp.getSuggestionList()) {
				suggestion.printSuggestionStaff();
			}
		}
		
		int subchoice =0;
		while(subchoice!=2) {
			System.out.println("\nPlease select one of the options below: ");
			System.out.println("1. Approve suggestion");
			System.out.println("2. Back");
			subchoice = ReadChecker.checkInt();
			
			if(subchoice==1) {
				staffSuggestionHandler.ApproveSuggestion(scanner, staff, mainApp.suggestionData);
			}
			else if(subchoice==2) {
				break;
			}
			else {
				System.out.println("Invalid choice, please try again!");
				continue;
			}
		}
	}
	
	
	/** 
	 * @param staff staff object
	 * @param scanner scanner object
	 */
	 
	private static void ViewEnquiries(Staff staff, Scanner scanner) {
		Scanner in = new Scanner(System.in);
		Camp staffCamp = staff.getCreatedCamp();
		if(mainApp.enquiryData.ViewByStaff(staffCamp) == 0) {
			System.out.println("Your camp has no enquiries currently.");
			return;
		}
		int subchoice = 0;
		//exception handling
		while (subchoice != 2) {
			System.out.println("\nPlease select 1 of the option below:");
			System.out.println("1. Reply to an enquiry");
			System.out.println("2. Back");
			
			subchoice = scanner.nextInt();
			if (subchoice == 1) {
				committeeStaffEnquiry.ReplyEnquiry(scanner, mainApp.enquiryData, staff);
			}
			else if (subchoice == 2) {
				break;
			}
			else {
				System.out.println("Invalid choice. Please try again.");
				continue;
			}
		}
		
	}
	
}