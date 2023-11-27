package fileIO;

import java.io.BufferedReader;
import java.lang.Integer;

import java.time.*;
import java.time.format.*;
import java.util.Map;
import java.util.HashMap;


import application.mainApp;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import application.*;
import common.model.Faculty;
import controller.manager.*;
import model.*;

/**
 * class provide methods to read/write camp data from csv
 */
public class CampIO {
	/**
	 * Delimiter used in CSV file
	 */
	public static final String DELIMITER = ",";
	
	/**
	 * @param campData all camp data
	 * @param staffData all staff data
	 * @param path file path to read camp data from csv
	 */

	public static void readCSV(CampManager campData, StaffManager staffData, String path) {
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
				String campID = csvData[0].trim();
				String name = csvData[1].trim();
				Faculty faculty = Faculty.valueOf(csvData[2].trim());
				String location = csvData[3].trim();
				String desc = csvData[4].trim();

				LocalDate campStartDate = LocalDate.parse(csvData[5].trim());
				LocalDate campEndDate = LocalDate.parse(csvData[6].trim());
				LocalDate regCloseDate = LocalDate.parse(csvData[7].trim());
				
				int totalSlots = Integer.parseInt(csvData[8].trim());
				int committeeSlots = Integer.parseInt(csvData[9].trim());
				
				int visibility = Integer.parseInt(csvData[10].trim());
				boolean isvisible = (visibility==1);
				String staffID = csvData[11].trim();
				
				Camp newCamp = new Camp(campID, name, faculty, location, desc, campStartDate, campEndDate, regCloseDate, totalSlots, committeeSlots, isvisible, staffData.getStaff(staffID));
				campData.addNewCamp(newCamp);
				staffData.getStaff(staffID).setCreatedCamp(newCamp);
			}
					
		}catch(IOException ioe) {ioe.printStackTrace();}
	}
	
	/**
	 * @param campData all camp data
	 * @param staffData all staff data
	 * @param path file path to write camp data to csv
	 */
	public static void writeCSV(CampManager campData, StaffManager staffData, String path) {
		try {
			File csvFile = new File(path);
			FileWriter fw  = new FileWriter(csvFile);
			fw.write("ID,Name,Faculty,Location,description,campStartDate,campEndDate,regCloseDate,totalSlots,committeeSlots,visibility,staffID\n");
			System.out.println("size: " + campData.getcampData().size());
			for (int i=0; i<campData.getcampData().size(); i++) {
				StringBuilder line = new StringBuilder();

				String id = new String("C" + (i+1));
				Camp camp = campData.getCamp(id);
				
				line.append(camp.getCampID() + ",");
				line.append(camp.getCampName() + ",");
				line.append(camp.getCampFaculty() + ",");
				line.append(camp.getLocation() + ",");
				line.append(camp.getDescription() + ",");
				line.append(camp.getCampStartDate().toString() + ",");
				line.append(camp.getCampEndDate().toString() + ",");
				line.append(camp.getRegCloseDate().toString() + ",");
				line.append(camp.getTotalSlots() + ",");
				line.append(camp.getCommitteeSlots() + ",");
				line.append(camp.getVisibility()?("1"):("0"));
				line.append(",");
				line.append(camp.getStaffInCharge().getUserID());
				line.append("\n");
				
				System.out.println(line.toString());
				fw.write(line.toString());
			}
		   fw.close();
		   
		}catch (Exception e) {
		    e.printStackTrace();
	    }		
	}

}
