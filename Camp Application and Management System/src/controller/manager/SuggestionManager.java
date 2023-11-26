package controller.manager;

import java.util.*;
import model.*;

public class SuggestionManager {
	
	public static Map<String, Suggestion> suggestions;
	
	public SuggestionManager(){
		suggestions = new HashMap<>();
	}
	public void addSuggestion(Suggestion suggestion) {
		suggestions.put(suggestion.getSuggestionID(), suggestion);	}
	
	public Suggestion addSuggestion(String Suggestion, User createdBy, String camp) {
		Suggestion newSuggestion = new Suggestion(Suggestion, createdBy, camp);
		suggestions.put(newSuggestion.getSuggestionID(), newSuggestion);
		
		return newSuggestion;
	}
	
	public Suggestion getSuggestionByID(String SuggestionID){
		return suggestions.get(SuggestionID);
	}
	
	public Map<String, Suggestion> getSuggData() {
		return suggestions;
	}
	
	
//	public int ViewByStudent(Student student, String campID) {
//		return;
//	}
	public int ViewByStaff(Camp staffCamp) {
		int count = 0;
		for (Suggestion suggestion: staffCamp.getSuggestionList()) {
			count++;
			suggestion.printSuggestionStaff();
		}
		return count;
	}

	public void deleteSuggestion(String removeSuggestion, User createdby, String campcommID) {
		Suggestion removesuggestion = new Suggestion(removeSuggestion, createdby, campcommID);
		suggestions.remove(removesuggestion.getSuggestionID(), removeSuggestion);
	}
	
	
//	public void ViewByStudent(Student student, String campID) {
//		for (Suggestion Suggestion: student.getStudentEnquiries()) {
//			if (campID == null) {
//				Suggestion.printSuggestion();
//			}
//			else if (campID == Suggestion.getCampID()) {
//				Suggestion.printSuggestion();
//			}
//			else continue;
//		}
//	}
	
//	public void ViewAllSuggestion() {							
//		for (int i=0; i<AllSuggestionList.size(); i++) {		//traverse through the All suggestions
//			Suggestion suggest =AllSuggestionList.get(i);
//            System.out.println("Suggestion: " +suggest.getSuggestion() +" Created By: " +suggest.getCreatedBy()+ " with ID: " +suggest.getSuggestionID());
//        }
//	}
//	
//	public void ViewAllCampSuggestion(Camp camp) {							
//		ArrayList<Suggestion> CampSuggestion = camp.getCampSuggestions();				//All suggestions for PARTICULAR CAMP
//		for (int i=0; i<CampSuggestion.size(); i++) {		//traverse through the All suggestions
//			Suggestion suggest = CampSuggestion.get(i);
//            System.out.println("Suggestion: " +suggest.getSuggestion() +" Created By: " +suggest.getCreatedBy());
//        }
//	}
	
	// public Suggestion getSuggestionByID(String SuggID) {
	// 	Suggestion output=null;
    // 	for(int i=0; i<AllSuggestionList.size(); i++) {
    // 		if(AllSuggestionList.get(i).getSuggestionID().equals(SuggID)) {
    // 			output = AllSuggestionList.get(i);
    // 			break;
    // 		}
    // 	}
    //     return output;
	// }
	
}
