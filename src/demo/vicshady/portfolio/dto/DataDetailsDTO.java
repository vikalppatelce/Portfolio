package demo.vicshady.portfolio.dto;

public class DataDetailsDTO {
	String id;
	String dataId;
	String url;
	String name;
	String status;

	public DataDetailsDTO(String id, String dataId, String url, String name,
			String status) {
		super();
		this.id = id;
		this.dataId = dataId;
		this.url = url;
		this.name = name;
		this.status = status;
	}
	public DataDetailsDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
