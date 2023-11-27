package controller.manager;

import java.util.*;
import model.*;
/** 
 * class to manage all the suggestions
 */ 
public class SuggestionManager {
	
	public static Map<String, Suggestion> suggestions;
	
	public SuggestionManager(){
		suggestions = new HashMap<>();
	}
	
	/** 
	 * @param suggestion suggestion 
	 */
	public void addSuggestion(Suggestion suggestion) {
		suggestions.put(suggestion.getSuggestionID(), suggestion);	}
	
	
	/** 
	 * @param Suggestion Suggestion string
	 * @param createdBy	user object
	 * @param camp camp ID
	 * @return Suggestions object
	 */
	public Suggestion addSuggestion(String Suggestion, User createdBy, String camp) {
		Suggestion newSuggestion = new Suggestion(Suggestion, createdBy, camp);
		suggestions.put(newSuggestion.getSuggestionID(), newSuggestion);
		
		return newSuggestion;
	}
	
	
	/** 
	 * @param SuggestionID Suggestion ID
	 * @return Suggestion object
	 */
	public Suggestion getSuggestionByID(String SuggestionID){
		return suggestions.get(SuggestionID);
	}
	
	
	/** 
	 * @return Map<String, Suggestion> list of all the suggestion data
	 */
	public Map<String, Suggestion> getSuggData() {
		return suggestions;
	}
	
	


//	public int ViewByStudent(Student student, String campID) {
//		return;
//	}
/** 
 * @return int number of suggestions
 */
	public int ViewByStaff(Camp staffCamp) {
		int count = 0;
		for (Suggestion suggestion: staffCamp.getSuggestionList()) {
			count++;
			suggestion.printSuggestionStaff();
		}
		return count;
	}

	
	/** 
	 * @param removeSuggestion suggestion string
	 * @param createdby user object
	 * @param campcommID camp ID
	 */
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
