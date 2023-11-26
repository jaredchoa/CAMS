package controller;

import model.*;
import java.util.*;


public class CommitteePerformanceReport {

	private Camp camp;
	
	public CommitteePerformanceReport(Camp camp) {
		this.camp = camp;
	}
	
	public void GenerateReport()
	{
		ArrayList<Student> CommitteeMembers = camp.getCommitteeList();
		for(int i=0; i<CommitteeMembers.size(); i++) {
			Student CommMember = CommitteeMembers.get(i);
			int points = CommMember.getPoints();
			String UserID = CommMember.getUserID();
			System.out.println("Committee Member " +UserID+ " has " +points+ "points");
		}
	}

}
