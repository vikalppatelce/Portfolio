package demo.vicshady.portfolio.dto;

public class UploadDataResponseDTO {

	String data;
	String data_details;

	public UploadDataResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public UploadDataResponseDTO(String data, String data_details) {
		super();
		this.data = data;
		this.data_details = data_details;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDataDetails() {
		return data_details;
	}

	public void setDataDetails(String data_details) {
		this.data_details = data_details;
	}
}
