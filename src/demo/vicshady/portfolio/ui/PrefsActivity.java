/* HISTORY
 * CATEGORY 		:- PREFERENCES
 * DEVELOPER		:- VIKALP PATEL
 * AIM      		:- EXPENSES
 * DESCRIPTION 		:- SETTINGS OF APPLICATION
 * 
 * S - START E- END  C- COMMENTED  U -EDITED A -ADDED
 * --------------------------------------------------------------------------------------------------------------------
 * INDEX       DEVELOPER		DATE			FUNCTION		DESCRIPTION
 * --------------------------------------------------------------------------------------------------------------------
 * 10001       VIKALP PATEL    10/02/2014       				FIXED PREFERENCE ON CHANGE LISTENER
 * --------------------------------------------------------------------------------------------------------------------
 */

package demo.vicshady.portfolio.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.os.Environment;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockPreferenceActivity;

import demo.vicshady.portfolio.R;
import demo.vicshady.portfolio.ui.utils.ChangeLogDialog;

public class PrefsActivity extends SherlockPreferenceActivity implements OnSharedPreferenceChangeListener 
{
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		PreferenceManager prefMgr = getPreferenceManager();
		addPreferencesFromResource(R.xml.settings);
		Preference release = prefMgr.findPreference("prefRelease");
		if(release!=null)
		{
			release.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference preference) {
					// TODO Auto-generated method stub
					ChangeLogDialog changeLogDialog = new ChangeLogDialog(PrefsActivity.this);
					changeLogDialog.show();
					return false;
				}
			});
		}
		
		Preference dev = prefMgr.findPreference("prefDev");
		if(dev!=null)
		{
			dev.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
				
				@Override
				public boolean onPreferenceClick(Preference preference) {
					// TODO Auto-generated method stub
				//	showDialog(DEV);
					copyDatabase();
					return false;
				}
			});
		}
	}
	
	// SA 10001
	@Override
	protected void onResume() {
		super.onResume();
		// Set up a listener whenever a key changes
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}
	@Override
	protected void onPause() {
		super.onPause();
		// Unregister the listener whenever a key changes
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}
	// EA 10001
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		updatePreference(key);
	}
	public void copyDatabase()
	{
		try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "/data/data/" + getPackageName() + "/databases/PortfolioDB";
                String backupDBPath = "PortfolioDB_Dev.db";
                File currentDB = new File(currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                    Toast.makeText(getApplicationContext(), "Database Transfered!", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
        	Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

	}

	public static boolean isEmailValid(String nComingEmail) 
	{
	    boolean isValid = false;

	    String mExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	    CharSequence mInputEmail = nComingEmail;

	    Pattern mPattern = Pattern.compile(mExpression, Pattern.CASE_INSENSITIVE);
	    Matcher mMatcher = mPattern.matcher(mInputEmail);
	    if (mMatcher.matches()) 
	    {
	        isValid = true;
	    }
	    return isValid;
	}
	
	private void updatePreference(String key) {
		if (key.equals("prefUser")) {
			Preference preference = findPreference(key);
			if (preference instanceof EditTextPreference) {
				EditTextPreference userPreference = (EditTextPreference) preference;
				if (userPreference.getEditText().length() > 0 && isEmailValid(userPreference.getEditText().getText().toString())) {
					userPreference.setSummary(userPreference.getEditText().getText().toString());
				} 
				else
				{
					userPreference.setSummary(userPreference.getEditText().getText().toString());
					Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
				}
			}
		}
		
		if (key.equals("prefPass")) {
			Preference preference = findPreference(key);
			if (preference instanceof EditTextPreference) {
				EditTextPreference passPreference = (EditTextPreference) preference;
				if (passPreference.getEditText().length() > 0) {
					passPreference.setSummary("Will keep it safe");
				} 
			}
		}
		
		if (key.equals("prefSent")) {
			Preference preference = findPreference(key);
			if (preference instanceof EditTextPreference) {
				EditTextPreference sentPreference = (EditTextPreference) preference;
				if (sentPreference.getEditText().length() > 0 && isEmailValid(sentPreference.getEditText().getText().toString())) {
					sentPreference.setSummary("Mail will sent to:"+sentPreference.getEditText().getText().toString());
				} 
				else
				{
					sentPreference.setSummary(sentPreference.getEditText().getText().toString());
					Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
				}
			}
		}
	}
}
