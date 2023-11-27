package fileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import common.model.Faculty;
import common.model.SuggestionStatus;
import controller.manager.*;
import model.Camp;
import model.Suggestion;

public class SuggestionIO {
	
	public static final String DELIMITER = ",";

	
	public static void readCSV(SuggestionManager suggData, CampManager campData, StudentManager studentData, StaffManager staffData, String path) {
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
				
				String suggID = csvData[0].trim();
				String sugg = csvData[1].trim();
				String createdBy = csvData[2].trim();
				String approvedBy = csvData[3].trim();
				SuggestionStatus sttus = SuggestionStatus.valueOf(csvData[4].trim());
				String campID = csvData[5].trim();
				
				Suggestion newSugg = new Suggestion(suggID, sugg,studentData.getStudent(createdBy), campID, staffData.getStaff(approvedBy));
				suggData.addSuggestion(newSugg);
				studentData.getStudent(createdBy).addCommitteeSuggestions(newSugg);
				campData.getCamp(campID).setSuggestionList(newSugg);
			}
					
		}catch(IOException ioe) {ioe.printStackTrace();}
	}
	
	public static void writeCSV(SuggestionManager suggData, String path) {
		try {
			File csvFile = new File(path);
			FileWriter fw  = new FileWriter(csvFile);
			
			fw.write("SuggID,Suggestion,CreatedBy,AprrovedBy,status,CampID\n");
			//System.out.println("size: " + campData.getcampData().size());
			
			for (int i=0; i<suggData.getSuggData().size(); i++) {
				StringBuilder line = new StringBuilder();

				String id = new String("S" + (i+1));
				Suggestion sugg = suggData.getSuggestionByID(id);
				
				line.append(sugg.getSuggestionID() + ",");
				line.append(sugg.getSuggestion()+ ",");
				line.append(sugg.getCreatedBy().getUserID()+",");
				if (sugg.getApprovedBy()!=null) {
					line.append(sugg.getApprovedBy().getUserID()+",");
				}
				else line.append("null,");
				line.append(sugg.getStatus().toString()+",");
				line.append(sugg.getCampID()+",");
				line.append("\n");
				
//				System.out.println(line.toString());
				fw.write(line.toString());
			}
		   fw.close();
		   
		}catch (Exception e) {
		    e.printStackTrace();
	    }		
	}

	
	
}
