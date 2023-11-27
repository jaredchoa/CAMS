package Handlers;

import java.util.Scanner;

import HandlersInterfaces.CommitteeStaffEnquiryHandlerInterface;
import controller.manager.EnquiryManager;
import model.Enquiry;
import model.Student;
import model.User;

/**
 * enquiry handler
 */
public class CommitteeStaffEnquiryHandler implements CommitteeStaffEnquiryHandlerInterface{

	
	/** 
	 * @param scanner scanner object
	 * @param EnquiryData  EnquiryManager object
	 */
	public void ReplyEnquiry(Scanner scanner, EnquiryManager EnquiryData, User user) {
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
			if(user instanceof Student) {
				Student commMemb = (Student) user;
				commMemb.addPoints();
			}
		}
	}

}
