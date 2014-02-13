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
 * 10002       VIKALP PATEL    13/02/2014      PREFERENCES      INITIALIZING SUMMARY OF PREFERENCES
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
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockPreferenceActivity;

import demo.vicshady.portfolio.R;
import demo.vicshady.portfolio.ui.utils.AboutDialog;
import demo.vicshady.portfolio.ui.utils.ChangeLogDialog;

public class PrefsActivity extends SherlockPreferenceActivity implements OnSharedPreferenceChangeListener 
{
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		PreferenceManager prefMgr = getPreferenceManager();
		addPreferencesFromResource(R.xml.settings);
		//SA 10002
		for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            initSummary(getPreferenceScreen().getPreference(i));
        }//EA 10002
		
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
					copyDatabase(); AboutDialog aboutDialog = new AboutDialog(PrefsActivity.this);
					aboutDialog.show();
					return false;
				}
			});
		}
		
		Preference about = prefMgr.findPreference("prefAbout");
		if(about!=null)
		{
			about.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
				
				@Override
				public boolean onPreferenceClick(Preference preference) {
					// TODO Auto-generated method stub
				//	showDialog(DEV);
					AboutDialog aboutDialog = new AboutDialog(PrefsActivity.this);
					aboutDialog.show();
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
		updatePrefSummary(findPreference(key));
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
	//SA 10002
	private void initSummary(Preference p) {
        if (p instanceof PreferenceCategory) {
            PreferenceCategory pCat = (PreferenceCategory) p;
            for (int i = 0; i < pCat.getPreferenceCount(); i++) {
                initSummary(pCat.getPreference(i));
            }
        } else {
            updatePrefSummary(p);
        }
    }
	
	private void updatePrefSummary(Preference p) {
        if (p instanceof EditTextPreference) {
            EditTextPreference editTextPref = (EditTextPreference) p;
            if(editTextPref.getKey().equalsIgnoreCase("prefPass"))
            {
            	p.setSummary("Will Keep it safe");
            }
            else
            {
            	Spannable summary = new SpannableString (editTextPref.getText().toString());
				summary.setSpan( new ForegroundColorSpan( Color.RED ), 0, summary.length(), 0 );
				p.setSummary(isEmailValid(editTextPref.getText().toString())? editTextPref.getText() : summary);
            }
        }
	}
	//EA 10002
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
					//SA 10002
					Spannable summary = new SpannableString (userPreference.getEditText().getText().toString());
					summary.setSpan( new ForegroundColorSpan( Color.RED ), 0, summary.length(), 0 );
					
					userPreference.setSummary(summary);//EA 10002
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
					//SA 10002
					Spannable summary = new SpannableString (sentPreference.getEditText().getText().toString());
					summary.setSpan( new ForegroundColorSpan( Color.RED ), 0, summary.length(), 0 );
					
					sentPreference.setSummary(summary);//EA 10002
					Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
				}
			}
		}
	}
}
