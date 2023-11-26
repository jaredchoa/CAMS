package fileIO;
import model.*;
import java.util.*;
import application.*;
import common.model.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class writeReport {
	public static void generateCommitteeReport(Camp camp, Student student, Scanner scanner, int choice){

		ArrayList<Student> attendees = new ArrayList<Student>();
		ArrayList<Student> committeeMembers = new ArrayList<Student>();
		ArrayList<Student> allStudents = new ArrayList<Student>();
		String committeeID = student.getCommitteeCampID();
		attendees = camp.getStudentList();
		allStudents = attendees;
		committeeMembers = camp.getCommitteeList();
		// need exception handling
		switch (choice) {
		case 1:
			try{
				FileWriter writechoiceone = new FileWriter("Camp Application and Management System/CommitteeReport(allstudents).txt");	
				System.out.println("These are the all the attendees:");
				writechoiceone.write("These are the all the attendees:\n");
				for (int i = 0; i < allStudents.size(); i++) {
					System.out.println((i + 1) + ")" + allStudents.get(i).getUserName());
					writechoiceone.write((i + 1) + ")" + allStudents.get(i).getUserName());
					if (allStudents.get(i).getCommitteeCampID() == null) {
						System.out.print(" Role: Attendee\n");
						writechoiceone.write(" Role: Attendee\n");
						continue;
					}
					else if (allStudents.get(i).getCommitteeCampID().equals(committeeID)) {
						System.out.print(" Role: Committee Member\n");
						writechoiceone.write(" Role: Committee Member\n");
					}
				}
				writechoiceone.close();
			}
				catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			
			break;
		case 2:
		try{
			FileWriter writechoicetwo = new FileWriter("Camp Application and Management System/CommitteeReport(committeemembers).txt");
				System.out.println("These are the Committee Members:");
				writechoicetwo.write("These are the Committee Members:\n");
				for (int i = 0; i < committeeMembers.size(); i++) {
					System.out.println((i + 1) + ")" + committeeMembers.get(i).getUserName() + "Role: Committee Member");
					writechoicetwo.write((i + 1) + ")" + committeeMembers.get(i).getUserName() + "Role: Committee Member");
				}
				writechoicetwo.close();
			}
				catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			break;
		case 3:
		try{
			FileWriter writechoicethree = new FileWriter("Camp Application and Management System/CommitteeReport(noncommitteemembers).txt");

				System.out.println("These are the non-Committee Member attendees:");
				writechoicethree.write("These are the non-Committee Member attendees:\n");
				for (int i = 0; i < attendees.size(); i++) { // Filter out to just the students excluding committee members
					for (int j = 0; j < committeeMembers.size(); j++) {
						if (attendees.get(i).getUserID().equals(committeeMembers.get(j).getUserID())) {
							attendees.remove(i);
						}
					}
				}
				for (int i = 0; i < attendees.size(); i++) {
					System.out.println((i + 1) + ")" + attendees.get(i).getUserName() + "\n Role: Attendee");
					writechoicethree.write((i + 1) + ")" + attendees.get(i).getUserName() + "\n Role: Attendee");
				}
				writechoicethree.close();
			}
				catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			break;
		case 4:
			return;
		}

		}
	


	public static void generateStaffReport(Staff staff, Scanner scanner, int choice){

		ArrayList<Student> attendees = new ArrayList<Student>();
		ArrayList<Student> committeeMembers = new ArrayList<Student>();
		ArrayList<Student> allStudents = new ArrayList<Student>();
		Camp reportcamp = staff.getCreatedCamp();
		String firstChoice;

		attendees = reportcamp.getStudentList();
		allStudents = attendees;
		committeeMembers = reportcamp.getCommitteeList();
		if(choice==1){//all students	
			try{
				FileWriter writechoiceone = new FileWriter("Camp Application and Management System/StaffReport_allstudents.txt");	
				System.out.println("These are all the attendees:");	
				writechoiceone.write("These are all the attendees:\n");
				for (int i = 0; i < allStudents.size(); i++) {
					writechoiceone.write((i + 1) + ")" + allStudents.get(i).getUserName());
					System.out.println((i + 1) + ")" + allStudents.get(i).getUserName());
					if (allStudents.get(i).getCommitteeCampID() == null) {
						System.out.print(" Role: Attendee\n");
						writechoiceone.write(" Role: Attendee\n");
						continue;
					}
					else {
						System.out.print(" Role: Committee Member\n");
						writechoiceone.write(" Role: Committee Member\n");
					}
				}
				writechoiceone.close();

			}
			catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
		else if(choice == 2){
			try{
				FileWriter writechoicetwo = new FileWriter("Camp Application and Management System/StaffReport(CommitteeMembers).txt");	
				System.out.println("These are the Committee Members:");
				writechoicetwo.write("These are the Committee Members:\n");
				for (int i = 0; i < committeeMembers.size(); i++) {
					System.out.println((i + 1) + ")" + committeeMembers.get(i).getUserName() + "Role: Committee Member");
					writechoicetwo.write((i + 1) + ")" + committeeMembers.get(i).getUserName() + "Role: Committee Member");
				}
				writechoicetwo.close();
			}
			catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}	
		}
		else if (choice == 3){
			try{
			FileWriter writechoicethree = new FileWriter("Camp Application and Management System/StaffReport(CommitteeMembers).txt");
			System.out.println("These are the non-Committee Member attendees:");
			writechoicethree.write("These are the non-Committee Member attendees:\n");
				for (int i = 0; i < attendees.size(); i++) { // Filter out to just the students excluding committee members
					for (int j = 0; j < committeeMembers.size(); j++) {
						if (attendees.get(i).getUserID().equals(committeeMembers.get(j).getUserID())) {
							attendees.remove(i);
						}
					}
				}
				for (int i = 0; i < attendees.size(); i++) {
					System.out.println((i + 1) + ")" + attendees.get(i).getUserName() + "\n Role: Attendee");
					writechoicethree.write((i + 1) + ")" + attendees.get(i).getUserName() + "\n Role: Attendee");
				}
				writechoicethree.close();
			}
			catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
	}
}
