package HandlersInterfaces;

import java.util.Scanner;

import controller.manager.EnquiryManager;
import model.User;

/**
 * committee and staff enquiry handler
 */
public interface CommitteeStaffEnquiryHandlerInterface {
	/**
	 * @param scanner scanner objet
	 * @param EnquiryData all enquiry data
	 * @param user user instance
	 */
	public void ReplyEnquiry(Scanner scanner, EnquiryManager EnquiryData, User user);
}
