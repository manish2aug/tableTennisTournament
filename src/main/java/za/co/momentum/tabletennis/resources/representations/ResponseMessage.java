package za.co.momentum.tabletennis.resources.representations;

public class ResponseMessage {

	private String status;
	private String detail;

	public String getStatus() {
		return status;
	}

	public ResponseMessage(String status, String detail) {
		super();
		this.status = status;
		this.detail = detail;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void getSuccessResponse() {
		this.setStatus("Success");
	}

	public void getErrorResponse() {
		this.setStatus("Error");
	}
}
