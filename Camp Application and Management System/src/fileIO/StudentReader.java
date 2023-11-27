package fileIO;

import java.io.*;
import java.util.Map;

import model.*;
import common.model.*;
import controller.manager.CampManager;
import controller.manager.StaffManager;
import controller.manager.StudentManager;

/**
 * Class provide methods to read/write student data from csv
 */
public class StudentReader {
	
	/**
	 * Delimiter used in CSV file
	 */
	public static final String DELIMITER = ",";

	/**
	 * @param studentData all student data
	 * @param path file path to read student csv data
	 */
	public static void readCSV(StudentManager studentData, String path) {
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
				String committeeCampID = csvData[4].trim();

				if(committeeCampID.equals("null")) {
					committeeCampID = null;
				}
				
				studentData.addStudent(userID, name, faculty, password, committeeCampID);
				
				}
			
		}catch(IOException ioe) {ioe.printStackTrace();}
	}
	
	/**
	 * @param studentData all student data
	 * @param path file path to write student data to csv 
	 */
	public static void writeCSV(StudentManager studentData, String path) {
		try {
			File csvFile = new File(path);
			FileWriter fw  = new FileWriter(csvFile);
			fw.write("Name,Email,Faculty,Password\n");
			
			for (Map.Entry<String, Student> pair : studentData.getStudentData().entrySet()) {
				StringBuilder line = new StringBuilder();
				Student student = pair.getValue();
				line.append(student.getUserName()+",");
				line.append(student.getUserID()+"@e.ntu.edu.sg,");
				line.append(student.getUserFaculty().toString()+",");
				line.append(student.getPassword()+",");
				line.append(student.getCommitteeCampID()+"\n");
				fw.write(line.toString());

			}
		   fw.close();
		   
		}catch (Exception e) {
		    e.printStackTrace();
	    }		
	}

}