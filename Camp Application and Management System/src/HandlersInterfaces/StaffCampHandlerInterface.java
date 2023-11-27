package HandlersInterfaces;

import java.util.Scanner;

import controller.manager.CampManager;
import model.Staff;

/**
 * interface for camp handler
 */
public interface StaffCampHandlerInterface {
	/**
	 * @param scanner scanner object
	 * @param staff staff user
	 * @param CampData campmanager that stores all camp data
	 */
	public void CreateCamp(Scanner scanner, Staff staff, CampManager CampData);
	/**
	 * @param scanner scanner object
	 * @param staff staff user
	 */
	public void EditCamp(Scanner scanner, Staff staff);
	/**
	 * @param scanner scanner object
	 * @param staff staff user
	 * @param CampData campmanger that stores all camp data
	 */
	public void DeleteCamp(Scanner scanner, Staff staff, CampManager CampData);
}
