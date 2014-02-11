package demo.vicshady.portfolio.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import demo.vicshady.portfolio.app.AppConstants;
import demo.vicshady.portfolio.app.Portfolio;
import demo.vicshady.portfolio.dto.DataDTO;
import demo.vicshady.portfolio.dto.DataDetailsDTO;

public class RequestBuilder {

	public static String getLoginUrl(String username, String password) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(AppConstants.URLS.BASE_URL);
		return stringBuffer.toString();
	}

	public static JSONObject getLoginData(String username, String password) {
		JSONObject stringBuffer = new JSONObject();
		// JSONObject ParentBuffer = new JSONObject();
		try {
			stringBuffer.put("act", "loginvalidate");
			stringBuffer.put("email", username);
			stringBuffer.put("user_password", password);

			// ParentBuffer.put("data", stringBuffer.toString());
		} catch (Exception e) {

		}
		return stringBuffer;// ParentBuffer;
	}
	
	public static JSONObject getServicesData(String imei, JSONObject jsonObject, JSONObject tables)
	{
		JSONObject stringBuffer = new JSONObject();
		
		//JSONObject ParentBuffer = new JSONObject();
		try
		{
			stringBuffer.put("user_id", Portfolio.getPreferences().getUserLoginDTO().getSign_id());
			stringBuffer.put("device_id", imei);
			stringBuffer.put("act", "data_upload");
			stringBuffer.put("tables", tables.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return stringBuffer;//ParentBuffer;
	}
	
	public static JSONArray getData(ArrayList<DataDTO> dataDTOs)
	{
		JSONArray jsonArray = new JSONArray();
		if(dataDTOs != null && dataDTOs.size() > 0)
		{
			for(int i = 0 ; i < dataDTOs.size();i++)
			{
				JSONObject jsonObject = new JSONObject();
				try
				{
					jsonObject.put("data_id", dataDTOs.get(i).getId());
					jsonObject.put("name", dataDTOs.get(i).getName());
					jsonObject.put("contact", dataDTOs.get(i).getContact());
					jsonObject.put("address", dataDTOs.get(i).getAddress());
					jsonObject.put("attachment", dataDTOs.get(i).getAttachment());
					jsonArray.put(jsonObject);
				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return jsonArray;
	}
	
	public static JSONObject getDataDetails(DataDetailsDTO dto)
	{
		try
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("image_id", dto.getId());
			jsonObject.put("data_id", dto.getDataId());
			jsonObject.put("file_name", dto.getName());
			return jsonObject;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
