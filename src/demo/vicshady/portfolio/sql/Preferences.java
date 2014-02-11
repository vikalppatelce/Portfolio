/* HISTORY
 * CATEGORY			 :- PREFERENCES
 * DEVELOPER		 :- VIKALP PATEL
 * AIM      		 :- 
 * DESCRITION 		 :- 
 * 
 * S - START E- END  C- COMMENTED  U -EDITED A -ADDED
 * --------------------------------------------------------------------------------------------------------------------
 * INDEX       DEVELOPER		DATE			FUNCTION		DESCRIPTION
 * --------------------------------------------------------------------------------------------------------------------
 * --------------------------------------------------------------------------------------------------------------------
 */

package demo.vicshady.portfolio.sql;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import demo.vicshady.portfolio.dto.UserLoginDTO;

public class Preferences {

	SharedPreferences sharedPreferences;
	Editor editor;
	
	public Preferences(Context context) {
		sharedPreferences = context.getSharedPreferences("Cache", Context.MODE_PRIVATE);
	}
	
	public void setUserLoginDTO(UserLoginDTO userLoginDTO)
	{
		editor = sharedPreferences.edit();
		editor.putString("userId", userLoginDTO.getSign_id());
		editor.putString("name", userLoginDTO.getUserName());
		editor.commit();
	}
	
	public UserLoginDTO getUserLoginDTO()
	{
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		userLoginDTO.setSign_id(sharedPreferences.getString("userId", null));
		userLoginDTO.setUserName(sharedPreferences.getString("name", null));
		return userLoginDTO;
	}
	
	public boolean getFirstTime()
	{
		boolean flag = sharedPreferences.getBoolean("isFirstTime", true);
		return flag;
	}
	
	public void setFirstTime(boolean flag)
	{
		editor = sharedPreferences.edit();
		editor.putBoolean("isFirstTime", flag);
		editor.commit();
	}
	
	public void setIsLOVInserted(boolean bool)
	{
		editor = sharedPreferences.edit();
		editor.putBoolean("isLOVInserted", bool);
		editor.commit();
	}
	
	public boolean getIsLOVInserted()
	{
		boolean bool = sharedPreferences.getBoolean("isLOVInserted", false);
		return bool;
	}
	public void setDeviceId(String id)
	{
		editor = sharedPreferences.edit();
		editor.putString("deviceId", id);
		editor.commit();
	}
	public String getDeviceId()
	{
		String deviceId = sharedPreferences.getString("deviceId", "Device Id Not Found");
				return deviceId;
	}
	public void setIMEINo(String id)
	{
		editor = sharedPreferences.edit();
		editor.putString("iMEINo", id);
		editor.commit();
	}
	public String getIMEINo()
	{
		String phoneId = sharedPreferences.getString("iMEINo", "IMEI Not Found");
		return phoneId;
	}
}
