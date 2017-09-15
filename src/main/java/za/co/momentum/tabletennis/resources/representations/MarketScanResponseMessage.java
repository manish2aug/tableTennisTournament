package za.co.momentum.tabletennis.resources.representations;

public class MarketScanResponseMessage {
	
	private String status;
	private String detail;
	
	public String getStatus() {
		return status;
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
	
	public void getSuccessResponse(){
		this.setStatus("Success");
	}

	public void getErrorResponse(){
		this.setStatus("Error");
	}
}
