package demo.vicshady.portfolio.ui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import demo.vicshady.portfolio.R;
import demo.vicshady.portfolio.app.AppConstants;
import demo.vicshady.portfolio.app.Portfolio;
import demo.vicshady.portfolio.service.ServiceHandler;

public class HomeActivity  extends SherlockFragmentActivity{

	Button btnsend,btnabout,btnportfolio,btnplay,btnplace;
	TextView developed;
	JSONArray aboutus;
	
	Typeface stylefont;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
		stylefont = Typeface.createFromAsset(getAssets(), AppConstants.fontStyle);
		
		fontActionBar(null);
		
		btnsend = (Button)findViewById(R.id.sendphotograph);
		btnabout = (Button)findViewById(R.id.about);
		btnportfolio = (Button)findViewById(R.id.portfolio);
		btnplay = (Button) findViewById(R.id.play);
		btnplace = (Button)findViewById(R.id.order);
		developed = (TextView)findViewById(R.id.developed);
		developed.setTypeface(stylefont);
		
		btnsend.setTypeface(stylefont);
		btnabout.setTypeface(stylefont);
		btnportfolio.setTypeface(stylefont);
		btnplay.setTypeface(stylefont);
		btnplace.setTypeface(stylefont);
		
		if(isNetworkAvailable())
		{
			new GetMailCredentials().execute();	
		}
		else
		{
			Toast.makeText(this, "Please check your internet connection and open application once again", Toast.LENGTH_SHORT).show();
		}

		
	}
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
	}
	public void onFooter(View v)
	{
		Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.krishnatechno.co.in/"));
		startActivity(browserIntent);
	}


	public void fontActionBar(String str)
	{
		try {
			int titleId = getResources().getIdentifier("action_bar_title",
					"id", "android");
			TextView yourTextView = (TextView) findViewById(titleId);
			yourTextView.setTypeface(stylefont);
		} catch (Exception e) {
			Log.e("ActionBar Style", e.toString());
		}
	}
	public void onSendPhoto(View v)
	{
		Intent i = new Intent(HomeActivity.this, SendPhotoActivity.class);
		startActivity(i);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}
	public void onAbout(View v)
	{
		Intent i = new Intent(HomeActivity.this,AboutActivity.class);
		startActivity(i);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}
	public void onPortfolio(View v)
	{
		Intent i = new Intent(HomeActivity.this,PortfolioActivity.class);
//		Intent i = new Intent(HomeActivity.this,ImageDetailActivity.class);
		startActivity(i);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}
	public void onPlay(View v)
	{
		Intent i = new Intent(HomeActivity.this,PlayWithMeActivity.class);
		startActivity(i);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}
	
	
	public void onPlaceOrder(View v)
	{
		Intent i = new Intent(HomeActivity.this,PlaceOrderActivity.class);
		startActivity(i);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case R.id.more: 
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	private class GetMailCredentials extends AsyncTask<Void, Void, Void> {
		 
    	Context context;
    	String jsonStr=null;
    	String username,password;

		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
 
            // Making a request to url and getting response
            jsonStr = sh.makeServiceCall(AppConstants.URLS.MAIL_URL, ServiceHandler.GET);
 
            Log.d("Response: ", "> " + jsonStr);
 
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    aboutus = jsonObj.getJSONArray("email");
                    
                    // looping through All Contacts
                    for (int i = 0; i < aboutus.length(); i++) {
                        JSONObject c = aboutus.getJSONObject(i);
                         
                        username = c.getString("username");
                        password = c.getString("password");
                        
                        Portfolio.getPreferences().setUser(username);
                        Portfolio.getPreferences().setPassword(password);
                    }
                    Log.e("Mail Credentials", username +":"+password);
 
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
            /**
             * Updating parsed JSON data into ListView
             * */
        }
 
    }

}
