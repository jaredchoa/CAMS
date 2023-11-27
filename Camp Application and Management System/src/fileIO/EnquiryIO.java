package fileIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import controller.manager.*;

import model.*;

/**
 * class provide methods to read/write enquiry data from csv
 */
public class EnquiryIO {
	/**
	 * Delimiter used in CSV file
	 */
	
	public static final String DELIMITER = ",";

	
	/**
	 * @param enquiryData all enquiry data
	 * @param campData all camp data
	 * @param studentData all student data
	 * @param staffData all staff data
	 * @param path file path to read enquiry data from csv
	 */
	public static void readCSV(EnquiryManager enquiryData, CampManager campData, StudentManager studentData, StaffManager staffData, String path) {
		try {
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			File csvFile = new File(path);
			FileReader fr = new FileReader(csvFile);
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			String[] csvData;
			br.readLine();
			while ((line = br.readLine()) != null) {
				
				csvData = line.split(DELIMITER);
				
				String enquiryID = csvData[0].trim();
				String enquiry = csvData[1].trim();
				String reply = csvData[2].trim();
				if (reply.equals(null)) reply = null;
				
				String senderID = csvData[3].trim();
				String campID = csvData[4].trim();
				
				Enquiry newEnquiry = new Enquiry(enquiryID, enquiry,reply, studentData.getStudent(senderID), campID);
				enquiryData.addEnquiry(newEnquiry);
				studentData.getStudent(senderID).addStudentEnquiries(newEnquiry);
				campData.getCamp(campID).addEnquiriesList(newEnquiry);
			}
					
		}catch(IOException ioe) {ioe.printStackTrace();}
	}
	
	/**
	 * @param enquiryData all enquiry data
	 * @param path file path to write enquiry data to csv
	 */
	public static void writeCSV(EnquiryManager enquiryData, String path) {
		try {
			File csvFile = new File(path);
			FileWriter fw  = new FileWriter(csvFile);
			
			fw.write("EnquiryID,Enquiry,Reply,Sender,CampID\n");
			//System.out.println("size: " + campData.getcampData().size());
			
			for (int i=0; i<enquiryData.getEnquiryData().size(); i++) {
				StringBuilder line = new StringBuilder();

				String id = new String("E" + (i+1));
				Enquiry enquiry = enquiryData.getEnquiryByID(id);
				
				line.append(enquiry.getEnquiryID() + ",");
				line.append(enquiry.getEnquiry()+ ",");
				line.append(enquiry.getReply()+",");
				line.append(enquiry.getSender().getUserID()+",");
				line.append(enquiry.getCampID()+",");
				line.append("\n");
				
				//System.out.println(line.toString());
				fw.write(line.toString());
			}
		   fw.close();
		   
		}catch (Exception e) {
		    e.printStackTrace();
	    }		
	}

}
