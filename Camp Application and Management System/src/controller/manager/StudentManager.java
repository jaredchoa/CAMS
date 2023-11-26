package controller.manager;
import java.util.HashMap;
import java.util.Map;

import application.mainApp;
import common.model.Faculty;
import model.*;


public class StudentManager {
	
    private Map<String, Student> students;
    

    public StudentManager() {
        students = new HashMap<>();
    }

    public Student getStudent(String userID) {
        return students.get(userID);
    }

    public void addStudent(String userID, String userName, Faculty facultyName, String password, String committeeCampID) {
    	Student student = new Student(userID,userName, facultyName, password, committeeCampID);
        students.put(student.getUserID(), student);
    }

	public Map<String, Student> getStudentData() {
		return students;
	}
	
}
