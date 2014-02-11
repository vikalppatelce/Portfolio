package demo.vicshady.portfolio.dto;

import java.util.ArrayList;

public class DataDTO {

	String id;
	String name;
	String contact;
	String address;
	String attachment;
	String status;
	ArrayList<DataDetailsDTO> paths;
	
	public DataDTO() {

	}

	public DataDTO(String id,String name,String contact,String address,
			String attachment,String status) {
		super();
		this.id=id;
		this.name=name;
		this.contact=contact;
		this.address=address;
		this.attachment=attachment;
		this.status=status;
	}
	public ArrayList<DataDetailsDTO> getPaths() {
		return paths;
	}
	public void setPaths(ArrayList<DataDetailsDTO> paths) {
		this.paths = paths;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
