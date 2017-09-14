package za.co.momentum.tabletennis.resources.representations;

public class AuthRepresentation {
	
	private String reason;
	private boolean isValid;
	
	public String getReason() {
		return reason;
	}
	
	public AuthRepresentation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthRepresentation(String reason, boolean isValid) {
		super();
		this.reason = reason;
		this.isValid = isValid;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}
