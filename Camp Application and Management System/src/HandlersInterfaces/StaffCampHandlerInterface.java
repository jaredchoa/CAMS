package HandlersInterfaces;

import java.util.Scanner;

import controller.manager.CampManager;
import model.Staff;

public interface StaffCampHandlerInterface {
	public void CreateCamp(Scanner scanner, Staff staff, CampManager CampData);
	public void EditCamp(Scanner scanner, Staff staff);
	public void DeleteCamp(Scanner scanner, Staff staff, CampManager CampData);
}
