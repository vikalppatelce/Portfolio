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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import demo.vicshady.portfolio.service.DataController;
import demo.vicshady.portfolio.sql.Preferences;

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
	
	public boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
