package Handlers;

import java.util.Scanner;

import application.mainApp;
import controller.manager.EnquiryManager;
import model.Enquiry;

/**
 * enquiry handler
 */
public class CommitteeStaffEnquiryHandler {

	
	/** 
	 * @param scanner scanner object
	 * @param EnquiryData  EnquiryManager object
	 */
	public static void ReplyEnquiry(Scanner scanner, EnquiryManager EnquiryData) {
		System.out.print("Input the ID of enquiry you wish to reply: ");

		String enquiryIDSubmit = scanner.nextLine();

		Enquiry enquiry = EnquiryData.getEnquiryByID(enquiryIDSubmit); // if ID is invalid return

		if (enquiry == null) {

			System.out.println("Invalid ID, Enquiry doesn't exist.");

		} else {
			System.out.print("Enter your reply: ");

			String enquirySubmit = scanner.nextLine();

			enquiry.setReply(enquirySubmit);

			enquiry.printEnquiry();

			System.out.println("The enquiry with an ID of " + enquiry.getEnquiryID()
					+ "has been successfully replied to.");
		}
	}

}
