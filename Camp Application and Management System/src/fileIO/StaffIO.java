package fileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import common.model.Faculty;
import controller.manager.StaffManager;
import model.*;

/**
 * class provide methods to read/write staff data from csv
 */
public class StaffIO {
	/**
	 * Delimiter used in CSV file
	 */
	public static final String DELIMITER = ",";

	/**
	 * @param staffData all staff data
	 * @param path file path to read staff data from csv
	 */
	public static void readCSV(StaffManager staffData, String path) {
		try {
			File csvFile = new File(path);
			FileReader fr = new FileReader(csvFile);
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			String[] csvData;
			br.readLine();
			while ((line = br.readLine()) != null) {
				csvData = line.split(DELIMITER);
				
				String name = csvData[0].trim();
				String email = csvData[1].trim();
				Faculty faculty = Faculty.valueOf(csvData[2].trim());
				String userID = email.split("@")[0];
				String password = csvData[3].trim();
		
				staffData.addStaff(userID, name, faculty, password);
				
				}
			
		}catch(IOException ioe) {ioe.printStackTrace();}
	}
	
	/**
	 * @param StaffData all staff data
	 * @param path file path to write staff data to csv
	 */
	public static void writeCSV(StaffManager StaffData, String path) {
		try {
			File csvFile = new File(path);
			FileWriter fw  = new FileWriter(csvFile);
			fw.write("Name,Email,Faculty,Password,CommitteeCampID\n");
			
			for (Map.Entry<String, Staff> pair : StaffData.getStaffData().entrySet()) {
				StringBuilder line = new StringBuilder();
				Staff Staff = pair.getValue();
				line.append(Staff.getUserName()+",");
				line.append(Staff.getUserID()+"@e.ntu.edu.sg,");
				line.append(Staff.getUserFaculty().toString()+",");
				line.append(Staff.getPassword()+"\n");
				fw.write(line.toString());

			}
		   fw.close();
		   
		}catch (Exception e) {
		    e.printStackTrace();
	    }		
	}

}
