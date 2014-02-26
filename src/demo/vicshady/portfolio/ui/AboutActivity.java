package demo.vicshady.portfolio.ui;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import demo.vicshady.portfolio.R;
import demo.vicshady.portfolio.app.AppConstants;
import demo.vicshady.portfolio.service.ServiceHandler;

public class AboutActivity  extends SherlockFragmentActivity{
	
	ActionBar actionBar;
	ProgressDialog pDialog;
	TextView about;
	Typeface stylefont;
	
	String aboutText;
	JSONArray aboutus;
	TextView developed;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_screen);
		
		stylefont = Typeface.createFromAsset(getAssets(), AppConstants.fontStyle);
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("About");

		fontActionBar(actionBar.getTitle().toString());
		
		actionBar.setIcon(android.R.drawable.ic_menu_info_details);

		if(isNetworkAvailable())
		{
			new GetAbout(this).execute();	
		}
		else
		{
			Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
		}
		about = (TextView)findViewById(R.id.about_text);
		developed = (TextView)findViewById(R.id.developed);
		developed.setTypeface(stylefont);
		about.setTypeface(stylefont);
		
	}
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
	}
	public void fontActionBar(String str)
	{
		try {
			int titleId = getResources().getIdentifier("action_bar_title","id", "android");
			TextView yourTextView = (TextView) findViewById(titleId);
			yourTextView.setText(str);
			yourTextView.setTypeface(stylefont);
		} catch (Exception e) {
			Log.e("ActionBar Style", e.toString());
		}
	}
	
	public void onFooter(View v)
	{
		Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.krishnatechno.co.in/"));
		startActivity(browserIntent);
	}
	
	
	/**
     * Async task class to get json by making HTTP call
     * */
    private class GetAbout extends AsyncTask<Void, Void, Void> {
 
    	Context context;
    	String jsonStr=null;
        public GetAbout(Context context) {
			// TODO Auto-generated constructor stub
        	this.context = context;
		}

		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
 
            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(AppConstants.URLS.ABOUT_URL, ServiceHandler.GET);
 
            Log.d("Response: ", "> " + jsonStr);
 
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    aboutus = jsonObj.getJSONArray("aboutus");
                    
                    // looping through All Contacts
                    for (int i = 0; i < aboutus.length(); i++) {
                        JSONObject c = aboutus.getJSONObject(i);
                         
                        aboutText = c.getString("about_us");
                    }
                    Log.e("about", aboutText);
 
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            Spanned res = Html.fromHtml(aboutText);
            about.setText(res);
            /**
             * Updating parsed JSON data into ListView
             * */
        }
 
    }
	
	
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case android.R.id.home:
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
