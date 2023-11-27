package Handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import application.mainApp;
import common.model.Faculty;
import common.util.ReadChecker;
import controller.manager.CampManager;
import model.Camp;
import model.Staff;

/** 
 * class to handle staff members
 */
public class StaffCampHandler {

	/** 
	 * @param scanner scanner object
	 * @param staff staff object
	 * @param CampData CampManager object
	 */
	public static void CreateCamp(Scanner scanner, Staff staff, CampManager CampData) {
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
		CampData.addNewCamp(newCamp);
		staff.setCreatedCamp(newCamp);
		System.out.println("Camp has been successfully created!");
		CampData.printCampData(staff, true);
	}
	
	/** 
	 * @param scanner scanner object
	 * @param staff staff object
	 */
	public static void EditCamp(Scanner scanner, Staff staff) {
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
	
	/**
	 * Delete the camp that the staff has created
	 * @param scanner scanner object
	 * @param staff staff object
	 * @param CampData camp manager object
	 */
	public static void DeleteCamp(Scanner scanner, Staff staff, CampManager CampData) {
		System.out.println("You are deleting the camp you have created.\n"
				+ "Do you want to continue? [Y/n]");
		String confirm = scanner.nextLine();
		if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
			CampData.removeCamp(staff.getCampID());
			staff.getCreatedCamp().deleteCamp();
			staff.setCreatedCamp(null);
		}
		else {
			System.out.println("Operation has been cancelled.");
		}
	}

}
