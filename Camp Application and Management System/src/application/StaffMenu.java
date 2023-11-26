package application;

import java.util.Scanner;
import model.*;
import common.util.*;
import common.model.*;
import java.time.*;
import java.time.format.*;
import fileIO.*;


public class StaffMenu {
	protected static void displayMenu(Staff staff) {
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
			System.out.println("6. Logout");
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
				logout = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void generateReport(Staff staff, Scanner scanner) {
		int choice = 0;
		System.out.println("Please choose an option:");
		System.out.println("1. Generate all students");
		System.out.println("2. Generate all committee members");
		System.out.println("3. Generate all non-committee members");
		choice = ReadChecker.checkInt();
		writeReport.generateStaffReport(staff, scanner, choice);
	}


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
					createCamp(staff, scanner);
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
				editOwnCamp(staff, scanner);
				break;
				case 3:
				System.out.println("You are deleting the camp you have created.\n"
						+ "Do you want to continue? [Y/n]");
				confirm = scanner.nextLine();
				if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
					mainApp.campData.removeCamp(staff.getCampID());
					staff.getCreatedCamp().deleteCamp();
					staff.setCreatedCamp(null);
				}
				else {
					System.out.println("Operation has been cancelled.");
				}
				break;
			case 4:
				break;
			default:
				System.out.println("Please enter a valid option:");

			}
		}
		
	}
	
	public static void editOwnCamp(Staff staff, Scanner scanner) {
		int subchoice = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  

		while(subchoice!=8) {
			System.out.println("What would u like to edit:\n"
					+ "1. Name\n"
					+ "2. Faculty\n"
					+ "3. Location\n"
					+ "4. Description\n"
					+ "5. Camp Dates\n"
					+ "6. Visibility\n"
					+ "7. Total Slots\n"
					+ "8. Save and return");
				subchoice = ReadChecker.checkInt();
				
			switch(subchoice) {
				
				case 1:
					System.out.print("Enter new name for camp:");
					String newName = scanner.nextLine();
					staff.getCreatedCamp().setCampName(newName);
					break;
				case 2:
					System.out.println("Open to Faculty or whole of NTU?\n"
							+ "1. Your Faculty - " + staff.getUserFaculty()
							+ "\n2. Whole of NTU");
					
					boolean valid = false;
					Faculty faculty = null;
					while(!valid) {
						valid = true;
						int choose = ReadChecker.checkInt();
						switch(choose) {
							case 1:
								faculty = staff.getUserFaculty();
								break;
							case 2:
								faculty = Faculty.ALL;
								break;
							default:
								System.out.println("Invalid Response.");
								valid=false;
								break;
						}
					}
					staff.getCreatedCamp().setCampFaculty(faculty);
					
					break;
				case 3:
					System.out.println("Enter new location for camp: ");
					String newLoc = scanner.nextLine();
					staff.getCreatedCamp().setLocation(newLoc);
					break;
				case 4:
					System.out.println("Enter new description for camp: ");
					String newDisc = scanner.nextLine();
					staff.getCreatedCamp().setDescription(newDisc);
					break;
				case 5:
					//start date
					System.out.println("Enter new Camp Start Date for camp: ");
					valid = false;
					LocalDate startDate = null;
					while(!valid) {
						System.out.println("Enter new Camp Start Date (dd/MM/yyyy):");
						String campStartDate = scanner.nextLine();
						try {
							startDate = LocalDate.parse(campStartDate, formatter);  
							valid = true;
						}catch (Exception e){
							System.out.println("Invalid date, please try again.");
							valid = false;
						}
					}
					staff.getCreatedCamp().setCampStartDate(startDate);

					//end date
					valid = false;
					LocalDate endDate = null;
					while(!valid) {
						System.out.println("Enter new Camp End Date (dd/MM/yyyy):");
						String campEndDate = scanner.nextLine();
						try {
							endDate = LocalDate.parse(campEndDate, formatter);  
							valid = true;
						}catch (Exception e){
							System.out.println("Invalid date, please try again.");
							valid = false;
						}		
					}
					staff.getCreatedCamp().setCampEndDate(endDate);
					// reg close
					valid = false;
					LocalDate regCloseDate = null;
					while(!valid) {
						System.out.println("Camp Reg Close Date (dd/MM/yyyy):");
						String campRegCloseDate = scanner.nextLine();
						
						try {
							regCloseDate = LocalDate.parse(campRegCloseDate, formatter);  
							valid = true;
						}catch (Exception e){
							System.out.println("Invalid date, please try again.");
							valid = false;
						}
					}
					staff.getCreatedCamp().setRegCloseDate(regCloseDate);
					
				case 6:
					String confirm;
					if (staff.getCreatedCamp().getVisibility()) {
						System.out.print("Toggle Visibility off? [Y/n]");
						confirm = scanner.nextLine();
						if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
							staff.getCreatedCamp().setVisibility(false);
						}
					}
					else {
						System.out.print("Toggle Visibility on? [Y/n]");
						confirm = scanner.nextLine();
						if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
							staff.getCreatedCamp().setVisibility(true);
						}
					}
					break;
				
				case 7:
					System.out.println("Enter new number of slots: ");
					int slots = ReadChecker.checkInt();
					if (slots < (staff.getCreatedCamp().getAttendeeCount()
							+ staff.getCreatedCamp().getCommitteeCount())) {
						System.out
								.println(
										"There are already "
												+ (staff.getCreatedCamp().getAttendeeCount()
														+ staff.getCreatedCamp().getCommitteeCount())
												+ " members. Invalid");
					} else {
						staff.getCreatedCamp().setTotalSlots(slots);
					}
					break;
				case 8:
					System.out.println("Changes has been saved!");
					break;
				default:
					System.out.println("Please enter a valid option:");
					break;
					
					
			}
		}
		
	}
	
	private static void createCamp(Staff staff, Scanner scanner) {
		if (staff.getCreatedCamp() != null) {
			System.out.println("You have already created a camp.");
			return;
		}
		boolean valid = false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		
		System.out.println("Please input the following details:");
		// name
		System.out.println("Camp Name:");
		String campName = scanner.nextLine();
		// desc
		System.out.println("Camp Description:");
		String campDescription = scanner.nextLine();
		// startDate
		valid = false;
		LocalDate startDate = null;
		while(!valid) {
			System.out.println("Camp Start Date (dd/MM/yyyy):");
			String campStartDate = scanner.nextLine();
			try {
				startDate = LocalDate.parse(campStartDate, formatter);  
				valid = true;
			}catch (Exception e){
				System.out.println("Invalid date, please try again.");
				valid = false;
			}		
		}
		// endDate
		valid = false;
		LocalDate endDate = null;
		while(!valid) {
			System.out.println("Camp End Date (dd/MM/yyyy):");
			String campEndDate = scanner.nextLine();
			try {
				endDate = LocalDate.parse(campEndDate, formatter);  
				valid = true;
			}catch (Exception e){
				System.out.println("Invalid date, please try again.");
				valid = false;
			}		
		}
		// regCloseDate
		valid = false;
		LocalDate regCloseDate = null;
		while(!valid) {
			System.out.println("Camp Reg Close Date (dd/MM/yyyy):");
			String campRegCloseDate = scanner.nextLine();
			
			try {
				regCloseDate = LocalDate.parse(campRegCloseDate, formatter);  
				valid = true;
			}catch (Exception e){
				System.out.println("Invalid date, please try again.");
				valid = false;
			}	
		}			
		// faculty
		System.out.println("Open to Faculty or whole of NTU?\n"
				+ "1. Your Faculty - " + staff.getUserFaculty()
				+ "\n2. Whole of NTU");
		
		valid = false;
		Faculty faculty = null;
		while(!valid) {
			valid = true;
			int choose = ReadChecker.checkInt();
			switch(choose) {
				case 1:
					faculty = staff.getUserFaculty();
					break;
				case 2:
					faculty = Faculty.valueOf("ALL");
					break;
				default:
					System.out.println("Invalid Response.");
					valid=false;
					break;
			}
		}
		
//		valid = false;
//		Faculty faculty = null;
//		while(!valid) {
//			System.out.println("Camp Faculty:");
//			try {
//				String campFaculty = scanner.nextLine();
//				faculty = Faculty.valueOf(campFaculty);
//				valid = true;
//			}catch (Exception e) {
//				System.out.println("Invalid faculty, please try again.");
//				valid = false;
//			}
//		}
		// location
		System.out.println("Camp Location:");
		String campLocation = scanner.nextLine();
		
		// total slots
		System.out.println("Total Slots:");
		int totalSlots  = ReadChecker.checkInt();
		
		
		//committee slots
		System.out.println("Committee Slots:");
		
		int committeeSlots = ReadChecker.verifyInt(1, 10);
		
		// create camp
		Camp newCamp = new Camp(campName, faculty, campLocation, campDescription, startDate, endDate, regCloseDate, totalSlots, committeeSlots, false, staff);
		mainApp.campData.addNewCamp(newCamp);
		staff.setCreatedCamp(newCamp);
		System.out.println("Camp has been successfully created!");
		mainApp.campData.printCampData(staff, true);
	}

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
				System.out.println("Input ID of suggestion you wish to approve: ");
				String suggestionID = scanner.nextLine();
				Suggestion suggestionApprove = mainApp.suggestionData.getSuggestionByID(suggestionID);
				if(staffCamp.getSuggestionList().contains(suggestionApprove)==false) {
					System.out.println("Invalid Suggestion ID");
				}
				else {
					suggestionApprove.setApprovedBy(staff);
					suggestionApprove.setStatus(SuggestionStatus.APPROVED);
				}
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
				System.out.print("\nInput ID of enquiry you wish to reply to: ");
				String enquiryID = in.nextLine();
				
				Enquiry replyEnquiry = mainApp.enquiryData.getEnquiryByID(enquiryID);
				replyEnquiry.printEnquiry();
				
				System.out.print("Reply: ");
				String reply = in.nextLine();
				
				replyEnquiry.setReply(reply);
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
