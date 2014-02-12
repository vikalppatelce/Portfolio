/* HISTORY
 * CATEGORY 		:- 
 * DEVELOPER		:- VIKALP PATEL
 * AIM			    :- 
 * DESCRIPTION 		:- 
 * 
 * S - START E- END  C- COMMENTED  U -EDITED A -ADDED
 * --------------------------------------------------------------------------------------------------------------------
 * INDEX       DEVELOPER		DATE			FUNCTION		DESCRIPTION
 * --------------------------------------------------------------------------------------------------------------------
 * --------------------------------------------------------------------------------------------------------------------
 */

package demo.vicshady.portfolio.app;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import demo.vicshady.portfolio.service.DataController;
import demo.vicshady.portfolio.sql.DBConstant;
import demo.vicshady.portfolio.sql.Preferences;
import demo.vicshady.portfolio.stacktrace.ExceptionHandler;

public class Portfolio extends Application {
	
	static Portfolio portfolio;
	static DataController dataController;
	static SharedPreferences sharedPreferences;
	static Preferences preferences;
	
	@Override
	public void onCreate() {
		super.onCreate();
		portfolio = this;
		dataController = new DataController();
		preferences = new Preferences(this);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		ExceptionHandler.register(portfolio);
	}
	@Override
	public void onTerminate() {
		super.onTerminate();
	}
	public static Portfolio getApplication()
	{
		return portfolio;
	}

	public static DataController getDataController() {
		return dataController;
	}
	
	public static Preferences getPreferences() {
		return preferences;
	}
	
	public static SharedPreferences getSharedPreferences()
	{
		return sharedPreferences;
	}
	
	public void createDatabase()
	{
		Cursor c = getContentResolver().query(DBConstant.Data_Columns.CONTENT_URI, null, null, null, null);
		if( c!= null)
		{
			c.close();
			c = null;
		}
		c = getContentResolver().query(DBConstant.Data_Details_Columns.CONTENT_URI, null, null, null, null);
		if( c!= null)
		{
			c.close();
			c = null;
		}
	}
	
	public boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
