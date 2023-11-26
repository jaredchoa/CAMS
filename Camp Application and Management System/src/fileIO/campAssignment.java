package fileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.attribute.AclEntry;

import controller.manager.CampManager;
import controller.manager.StudentManager;
import model.*;

public class campAssignment {
	

	public static final String DELIMITER = ",";


	public static void readCSV(CampManager campData, StudentManager studentData, String path) {
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
			String studentId = csvData[0].trim();
			String campId = csvData[1].trim();
			
			Camp camp = campData.getCamp(campId);
			Student student = studentData.getStudent(studentId);
			
			student.addRegisteredCamps(camp);
			camp.addStudentList(student);
		}
		
			
		}catch(IOException ioe) {ioe.printStackTrace();}
		
	}
	
	public static void writeCSV(CampManager campData, StudentManager studentData, String path) {
		try {
			File csvFile = new File(path);
			FileWriter fw  = new FileWriter(csvFile);
			fw.write("studentID,campID\n");
			
			System.out.println("size: " + campData.getcampData().size());
			for (int i=0; i<campData.getcampData().size(); i++) {
				StringBuilder line = new StringBuilder();
				String id = new String("C" + (i+1));
				Camp camp = campData.getCamp(id);

				for (Student student : camp.getStudentList()) {
					line.append(student.getUserID() + ",");
					line.append(camp.getCampID() + "\n");
				}
				System.out.println(line.toString());
				fw.write(line.toString());
			}
			fw.close();
		
		}catch (Exception e) {
		    e.printStackTrace();
	    }		
	}
}
		