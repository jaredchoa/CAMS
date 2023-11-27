package controller;

import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
/** 
 * class to check whether a student is ELLIGIBLE to join a camp
 */ 
public class CriteriaCheck {		
/** 
 * @param camp camp object
 * @param UserStudent student object
 * @return boolean whether student can register or not
 */
//To check whether a student is ELLIGIBLE to join a camp
	
	public boolean CanStudentRegister(Camp camp, Student UserStudent) {
		int total = camp.getTotalSlots();
		boolean SlotsAvailable = SlotsAvailable(camp.getAttendeeCount(), total);
		boolean IsBeforeRegistrationDate = IsBeforeRegistrationDate(LocalDate.now(), camp.getRegCloseDate());
		boolean NoClashWithOtherCamp = NoClashWithOtherCamp(camp.getCampStartDate(), camp.getCampEndDate(), UserStudent);
		boolean HasNotWithdrawn = HasNotWithdrawn(UserStudent.getWithdrawnCamps(), camp);
		if(SlotsAvailable && IsBeforeRegistrationDate && NoClashWithOtherCamp && HasNotWithdrawn)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/** 
	 * @param members number of members
	 * @param totalSlots total slots
	 * @return boolean whether slots are available or not
	 */
	public boolean SlotsAvailable(int members, int totalSlots) {
		if(members < totalSlots) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/** 
	 * @param CurrentDate current date
	 * @param RegistrationClosingDate registration closing date
	 * @return boolean whether before registration date or not
	 */
	public boolean IsBeforeRegistrationDate(LocalDate CurrentDate, LocalDate RegistrationClosingDate) {
		if(CurrentDate.isBefore(RegistrationClosingDate))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/** 
	 * @param CampStartDate camp start date
	 * @param CampEndDate camp end date
	 * @param UserStudent student object
	 * @return boolean whether clash with other camp or not
	 */
	public boolean NoClashWithOtherCamp(LocalDate CampStartDate, LocalDate CampEndDate, Student UserStudent) {
		//to check clash
		ArrayList<Camp> RegisteredCamps = UserStudent.getRegisteredCamps();
		for(int i=0; i<RegisteredCamps.size(); i++)
		{
			Camp camp = RegisteredCamps.get(i);
			LocalDate start = camp.getCampStartDate();
			LocalDate end = camp.getCampEndDate();
			//Assumption: End date is AFTER Start date
			boolean condition1 = CampStartDate.isAfter(start) && CampStartDate.isBefore(end);
			boolean condition2 = CampStartDate.isBefore(start) && CampEndDate.isAfter(end);
			boolean condition3 = CampStartDate.isAfter(start) && CampEndDate.isBefore(end);
			boolean condition4 = CampEndDate.isAfter(start) && CampEndDate.isBefore(end);
			
			if(condition1 || condition2 || condition3 || condition4)		//condition for clash
			{
				return false;			//no need to check further
			}
		}
		return true;		//no clash with any courses that have already been registered
	}
	
	
	/** 
	 * @param WithdrawnCamps withdrawn camps
	 * @param camp camp object
	 * @return boolean whether student has withdrawn or not
	 */
	public boolean HasNotWithdrawn(ArrayList<Camp> WithdrawnCamps, Camp camp)
	{
		if(WithdrawnCamps.contains(camp)==false)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
