package common.util;
import java.io.*;
import java.util.*;
import model.*;

public class GenerateReport {

	private Camp camp;
	
	public GenerateReport(Camp camp) {
		this.camp = camp;
	}

	public Camp getCamp() {
		return camp;
	}

	public void setCamp(Camp camp) {
		this.camp = camp;
	}
	
	public void GenerateStudentReport() {			
		Scanner in = new Scanner(System.in);
		int choice;
		System.out.println("Enter the format of report: \n(1)Committee Members First, (2)Attendees First");
		choice = in.nextInt();
		if(choice==1) {
			ReportFormat1();			//Default 
		}
		else if(choice==2) {
			ReportFormat2();
		}
	}
	
	public void ReportFormat1(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(camp.getCampInfo().getCampName()+".txt"));
			String line;
			while((line = reader.readLine())!=null) {
				System.out.println(line);
			}
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ReportFormat2() {
		ArrayList<String> attendees = new ArrayList<>();
		ArrayList<String> committees = new ArrayList<>();
		ArrayList<String> rest = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(camp.getCampInfo().getCampName()+".txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts (marker and name)
                String[] parts = line.split(" ", 2);
                String marker = parts[0];

                // Check the marker and add the name to the appropriate list
                if (marker.equals("Attendee")) {
                    attendees.add(line);
                } else if (marker.equals("Committee")) {
                    committees.add(line);
                }
                else {
                	rest.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for(String restOf: rest) {
        	System.out.println(restOf);
        }
        
        for (String committee : committees) {
            System.out.println(committee);
        }
        
        for (String attendee : attendees) {
            System.out.println(attendee);
        }
	}
}
