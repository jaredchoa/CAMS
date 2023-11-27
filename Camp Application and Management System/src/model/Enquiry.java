package model;

/**
 * Class that stores information about enquiry
 */
public class Enquiry {
	public static int ENQUIRYCOUNTER = 1;
	
	private String Enquiry;
	private String EnquiryID;
	private String Reply;
	private User Sender;
	private String CampID;
	
	
	
	/**
	 * @param enquiryID unique identifier for enquiry
	 * @param Enquiry enquiry details
	 * @param reply reply details
	 * @param sender student that send enquiry
	 * @param camp camp associated with enquiry
	 */
	public Enquiry(String enquiryID, String Enquiry, String reply, User sender, String camp) {
		
		this.Enquiry = Enquiry;
		this.EnquiryID = enquiryID;
		this.Reply = reply;
		this.Sender = sender;
		this.CampID = camp;
		
		ENQUIRYCOUNTER++;
	};
	
	
	/**
	 * @param Enquiry enquiry details
	 * @param sender student that send enquiry
	 * @param camp camp associated with enquiry
	 */
	public Enquiry(String Enquiry, User sender, String camp) {
		this.Enquiry = Enquiry;
		this.EnquiryID = "E" + ENQUIRYCOUNTER;
		this.Reply = null;
		this.Sender = sender;
		this.CampID = camp;
		
		ENQUIRYCOUNTER++;
	}

	/**
	 * @return enquiry
	 */
	public String getEnquiry() {
		return Enquiry;
	}

	/**
	 * @param enquiry enquiry
	 */
	public void setEnquiry(String enquiry) {
		Enquiry = enquiry;
	}

	/**
	 * @return enquiry ID
	 */
	public String getEnquiryID() {
		return EnquiryID;
	}

	/**
	 * @param enquiryID enquiry ID
	 */
	public void setEnquiryID(String enquiryID) {
		EnquiryID = enquiryID;
	}

	/**
	 * @return reply
	 */
	public String getReply() {
		return Reply;
	}

	/**
	 * @param reply reply
	 */
	public void setReply(String reply) {
		Reply = reply;
	}
	
	/**
	 * @param sender sender
	 */
	public void setSender(User sender) {
		Sender = sender;
	}

	/**
	 * @return sender
	 */
	public User getSender() {
		return Sender;
	}
	
	/**
	 * @param camp camp
	 */
	public void setCampID(String camp) {
		CampID = camp;
	}

	/**
	 * @return camp
	 */
	public String getCampID() {
		return CampID;
	}
	
	/**
	 * print enquiry details
	 */
	public void printEnquiry() {
		System.out.println(
				"\nEnquiryID: " + this.getEnquiryID()  + "\n" +
				"Enquiry:" + this.getEnquiry() + "\n" +
				"Reply:" + this.getReply() + "\n" +"\n"
				);
	}
	
}
