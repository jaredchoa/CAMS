package controller.manager;

import java.util.Scanner;

import application.mainApp;
import model.Camp;
import model.Enquiry;
import model.Student;

public class StudentEnquiryHandler {

	public static void SubmitEnquiry(Scanner scanner, Student student, CampManager CampData, EnquiryManager EnquiryData) {
		System.out.print("Input ID of camp you wish to submit enquiry of: ");

		String campIDSubmit = scanner.nextLine();

		Camp campSubmit = CampData.getCamp(campIDSubmit);

		if (!student.getRegisteredCamps().contains(campSubmit)) {

			System.out.println("You have not registered for this camp. Please try again.");

		}

		else {

			System.out.print("Input your enquiry: ");

			String enquirySubmit = scanner.nextLine();

			Enquiry newEnquiry = EnquiryData.addEnquiry(enquirySubmit, student, campIDSubmit); // add to
																										// enquiryData

			student.addStudentEnquiries(newEnquiry); // add to student's list

			CampData.getCamp(campIDSubmit).addEnquiriesList(newEnquiry); // add to camp's list

			System.out.println(
					"The enquiry has been successfully submitted with an ID of " + newEnquiry.getEnquiryID());

		}

	}
	
	public static void EditEnquiry(Scanner scanner, Student student, CampManager CampData, EnquiryManager EnquiryData) {
		System.out.print("Input ID of camp you wish to edit enquiry of: ");

		String campIDEdit = scanner.nextLine();

		Camp campEdit = CampData.getCamp(campIDEdit);

		if (!student.getRegisteredCamps().contains(campEdit)) {

			System.out.println("You have not registered for this camp. Please try again.");


		}

		else {

			// display enquiries corresponding to the above camp & students

			if (EnquiryData.ViewByStudent(student, campIDEdit) == 0) {

				System.out.println("You have not submitted any enquiries for this camp.");


			}
			else {

				System.out.print("\nInput ID of enquiry you wish to edit: ");

				String enquiryIDEdit = scanner.nextLine();
				Enquiry enquiryEdit = EnquiryData.getEnquiryByID(enquiryIDEdit);
				
				if(enquiryEdit!=null) {
					System.out.print("\nInput edited enquiry: ");

					String enquiryEdited = scanner.nextLine();

					EnquiryData.getEnquiryByID(enquiryIDEdit).setEnquiry(enquiryEdited);	
				}	
				else {
					System.out.println("Enquiry not found");
				}
			}

		}
	}

}
