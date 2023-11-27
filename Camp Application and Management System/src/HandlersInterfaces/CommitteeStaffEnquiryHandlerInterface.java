package HandlersInterfaces;

import java.util.Scanner;

import controller.manager.EnquiryManager;
import model.User;

public interface CommitteeStaffEnquiryHandlerInterface {
	public void ReplyEnquiry(Scanner scanner, EnquiryManager EnquiryData, User user);
}
