package application;

import java.util.Map;
import java.util.Scanner;

// import managers
import model.*;
import controller.*;
import controller.manager.*;
import fileIO.*;
import common.util.*;
import HandlersInterfaces.*;
import applicationInterfaces.CommitteeMenuInterface;
import applicationInterfaces.StaffMenuInterface;
/**
 * Private constructor for the mainApp class. Initializes data managers and reads data from CSV files.
 */

public class mainApp {
	//singleton session?
	private static mainApp session;
	//Attributes to store loaded date (all managers)
	protected static StudentManager studentData;
	protected static StaffManager staffData;
	protected static CampManager campData;
	protected static EnquiryManager enquiryData;
	protected static SuggestionManager suggestionData;

	//Constructor
	private mainApp() {
		//Data
		studentData = new StudentManager();
		staffData = new StaffManager();
		campData = new CampManager();
		suggestionData = new SuggestionManager();
		enquiryData = new EnquiryManager();
		
		//read Data
		StudentReader.readCSV(studentData, "data/studentList.csv");
		StaffIO.readCSV(staffData, "data/staffList.csv");
		CampIO.readCSV(campData, staffData, "data/campList.csv");
		EnquiryIO.readCSV(enquiryData, campData, studentData, staffData, "data/EnquiryList.csv");
		SuggestionIO.readCSV(suggestionData, campData, studentData, staffData, "data/SuggestionList.csv");
		
		//assingment
		campAssignment.readCSV(campData, studentData, "data/campAssign.csv");
		
//		for (Map.Entry<String, Student> pair : studentData.getStudentData().entrySet()) {
//			Student student = pair.getValue();
//			System.out.println("name: " + student.getUserName());
//		}
//		for (Map.Entry<String, Camp> pair : campData.getcampData().entrySet()) {
//			Camp camp = pair.getValue();
//			System.out.println("name: " + camp.getCampName());
//		}
		//create new for managers with no need to load data (enquiry)
	}
    /**
     * Main function to be run when starting the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
	public static void main(String[] args) {
		mainApp app = mainApp.getInstance();
		app.run();
	}
	
    /**
     * Gets the instance of the mainApp class, following the singleton pattern.
     *
     * @return The mainApp instance.
     */

	public static mainApp getInstance() {
		if (session == null)
		{
			session = new mainApp();
		}
		return session;
	}

	private void run() {
		boolean exit = false;
		Scanner scanner = new Scanner(System.in);

		while (!exit) {
			//boolean isValidInput = false;
			System.out.println("\n+------------------------------------------------------------+");
			System.out.println("Welcome to Camp Application and Management System (CAMs)");
			System.out.println("1. Login");
			System.out.println("2. Exit");
			System.out.println("+------------------------------------------------------------+");
			System.out.print("Please choose an option: ");


			int choice=0;
			choice=ReadChecker.checkInt();
//			while (!isValidInput) {
//				try {
//					choice = scanner.nextInt();
//					scanner.nextLine(); // Consume the newline
//					isValidInput = true;
//				} catch(Exception e) {
//					scanner.nextLine();
//					break;
//				}
//			}            

			switch (choice) {
			case 1:
				login(scanner, new CommitteeMenu(), new StaffMenu());
				break;
			case 2:
				exit = true;
				StudentReader.writeCSV(studentData, "data/studentList.csv");
				StaffIO.writeCSV(staffData, "data/staffList.csv");
				CampIO.writeCSV(campData, staffData, "data/campList.csv");
				campAssignment.writeCSV(campData, studentData, "data/campAssign.csv");
				EnquiryIO.writeCSV(enquiryData, "data/WriteList.csv");
				SuggestionIO.writeCSV(suggestionData, "data/SuggestionList.csv");
				
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
		scanner.close();
	}
	
	private void login(Scanner scanner, CommitteeMenuInterface committeeMenu, StaffMenuInterface staffMenu) {
		System.out.print("Enter your userID (CASE-SENSITIVE): ");
		String userID = scanner.nextLine();

		System.out.print("Enter your password: ");
		String password = scanner.nextLine();
		
		if (studentData.getStudent(userID) != null)
		{
			Student currentUser = studentData.getStudent(userID);
			if(currentUser.authenticate(password))
			{
				System.out.println("Login Successful! :D");
				if(currentUser.getCommitteeCampID() != null)
				{
					System.out.println("Login as: ");
					System.out.println("1. Committee Member");
					System.out.println("2. Student");
					int selection = ReadChecker.checkInt();
					//scanner.nextLine();

					if (selection == 1)
					{
						System.out.println("You have selected to view as Committee Member!");
						committeeMenu.displayMenu(currentUser);
						//display committeeMemberMenu(currentUser)
					}
					else
					{
						System.out.println("You have selected to view as Student!");
						StudentMenu.displayMenu(currentUser);
						//display studentMenu(currentUser)
					}
				}
				else
				{
					System.out.println("Welcome " + currentUser.getUserName());		//add their name or what
					//display studentMenu(currentUser)
					StudentMenu.displayMenu(currentUser);
				}
			}
			else System.out.println("Invalid password.");
		}

		else if (staffData.getStaff(userID) != null)
		{			
			Staff currentUser = staffData.getStaff(userID);
			if(currentUser.authenticate(password))
			{
				System.out.println("Login Successful! :D");

				System.out.println("Welcome " + currentUser.getUserName());
				//display staffMenu(currentUser)
				staffMenu.displayMenu(currentUser);
			}
			else System.out.println("Invalid password.");
		}

		else
		{
			System.out.println("Invalid userID.");
		}
	}

}