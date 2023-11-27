package controller.manager;
import java.util.HashMap;
import java.util.Map;

import application.mainApp;
import common.model.Faculty;
import model.*;

/** 
 * class to manage all the Students
 */ 
public class StudentManager {
	
    private Map<String, Student> students;
    

    public StudentManager() {
        students = new HashMap<>();
    }

    
    /** 
     * @param userID userID
     * @return Student object
     */
    public Student getStudent(String userID) {
        return students.get(userID);
    }

    
    /** 
     * @param userID userID
     * @param userName userName
     * @param facultyName faculty object
     * @param password password
     * @param committeeCampID committee camp ID
     */
    public void addStudent(String userID, String userName, Faculty facultyName, String password, String committeeCampID) {
    	Student student = new Student(userID,userName, facultyName, password, committeeCampID);
        students.put(student.getUserID(), student);
    }

	
    /** 
     * @return Map<String, Student> list of all the student data
     */
    public Map<String, Student> getStudentData() {
		return students;
	}
	
}
