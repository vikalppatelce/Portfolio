package demo.vicshady.portfolio.dto;

public class UserLoginDTO {

	boolean status;
	String userName;
	String sign_id;
	public UserLoginDTO(boolean status, String userName, String signId) {
		this.status = status;
		this.userName = userName;
		sign_id = signId;
	}
	
	public UserLoginDTO() {
		// TODO Auto-generated constructor stub
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSign_id() {
		return sign_id;
	}
	public void setSign_id(String signId) {
		sign_id = signId;
	}
}
