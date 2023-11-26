package model;

public class Enquiry {
	public static int ENQUIRYCOUNTER = 1;
	
	private String Enquiry;
	private String EnquiryID;
	private String Reply;
	private User Sender;
	private String CampID;
	
	
	
	public Enquiry(String enquiryID, String Enquiry, String reply, User sender, String camp) {
		
		this.Enquiry = Enquiry;
		this.EnquiryID = enquiryID;
		this.Reply = reply;
		this.Sender = sender;
		this.CampID = camp;
		
		ENQUIRYCOUNTER++;
	};
	
	
	public Enquiry(String Enquiry, User sender, String camp) {
		this.Enquiry = Enquiry;
		this.EnquiryID = "E" + ENQUIRYCOUNTER;
		this.Reply = null;
		this.Sender = sender;
		this.CampID = camp;
		
		ENQUIRYCOUNTER++;
	}

	public String getEnquiry() {
		return Enquiry;
	}

	public void setEnquiry(String enquiry) {
		Enquiry = enquiry;
	}

	public String getEnquiryID() {
		return EnquiryID;
	}

	public void setEnquiryID(String enquiryID) {
		EnquiryID = enquiryID;
	}

	public String getReply() {
		return Reply;
	}

	public void setReply(String reply) {
		Reply = reply;
	}
	
	public void setSender(User sender) {
		Sender = sender;
	}

	public User getSender() {
		return Sender;
	}
	
	public void setCampID(String camp) {
		CampID = camp;
	}

	public String getCampID() {
		return CampID;
	}
	
	public void printEnquiry() {
		System.out.println(
				"\nEnquiryID: " + this.getEnquiryID()  + "\n" +
				"Enquiry:" + this.getEnquiry() + "\n" +
				"Reply:" + this.getReply() + "\n" +"\n"
				);
	}
	
}
