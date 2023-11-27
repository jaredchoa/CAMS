package controller;

import model.*;
import java.util.*;

/** 
 * class to generate committee performance report
 */ 
public class CommitteePerformanceReport {
	

	/**
	 * generate report
	 * 
	 * @param camp camp
	 */
	public static void GenerateReport(Camp camp)
	{
		ArrayList<Student> CommitteeMembers = camp.getCommitteeList();
		if(CommitteeMembers.size()==0) {
			System.out.println("No Committee Members!");
		}
		else {
			for(int i=0; i<CommitteeMembers.size(); i++) {
				Student CommMember = CommitteeMembers.get(i);
				int points = CommMember.getPoints();
				String UserID = CommMember.getUserID();
				System.out.println("Committee Member " +UserID+ " has " +points+ " points");
			}
		}
	}

}
