/* HISTORY
 * CATEGORY 		:- ACTIVITY
 * DEVELOPER		:- VIKALP PATEL
 * AIM			    :- ADD IPD ACTIVITY
 * DESCRIPTION 		:- SAVE IPD
 * 
 * S - START E- END  C- COMMENTED  U -EDITED A -ADDED
 * --------------------------------------------------------------------------------------------------------------------
 * INDEX       DEVELOPER		DATE			FUNCTION		DESCRIPTION
 * --------------------------------------------------------------------------------------------------------------------
 * --------------------------------------------------------------------------------------------------------------------
 */
package demo.vicshady.portfolio.dto;

public class MediaUploadResponse {

	boolean success;
	String data_id;
	String file_name;
	public MediaUploadResponse(boolean success, String dataId, String fileName) {
		this.success = success;
		data_id = dataId;
		file_name = fileName;
	}
	public MediaUploadResponse() {
		// TODO Auto-generated constructor stub
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getdataId() {
		return data_id;
	}
	public void setdataId(String dataId) {
		data_id = dataId;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String fileName) {
		file_name = fileName;
	}	
}
