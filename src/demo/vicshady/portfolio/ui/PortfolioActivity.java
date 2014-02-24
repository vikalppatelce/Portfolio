package demo.vicshady.portfolio.ui;

import imageUtils.ImageGridFragment;
import imageUtils.Images;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import demo.vicshady.portfolio.R;
import demo.vicshady.portfolio.app.AppConstants;
import demo.vicshady.portfolio.service.ServiceHandler;

public class PortfolioActivity extends SherlockFragmentActivity{

	Typeface fontStyle;
	ActionBar actionBar;
	ProgressDialog pDialog;
	JSONArray portfolio;
	private static final String TAG = "ImageGridActivity";
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		if(isNetworkAvailable())
		{
			new CachingImageURL(this).execute();	
		}
		else
		{
			Toast.makeText(PortfolioActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
		}
		
		
		fontStyle  = Typeface.createFromAsset(getAssets(), AppConstants.fontStyle);
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("Portfolio");
		
		fontActionBar(actionBar.getTitle().toString());
		actionBar.setIcon(android.R.drawable.ic_menu_gallery);
			}
	
    	public void fontActionBar(String str)
	{
		try {
			int titleId = getResources().getIdentifier("action_bar_title","id", "android");
			TextView yourTextView = (TextView) findViewById(titleId);
			yourTextView.setText(str);
			yourTextView.setTypeface(fontStyle);
		} catch (Exception e) {
			Log.e("ActionBar Style", e.toString());
		}
	}
	
    	private boolean isNetworkAvailable() {
    	    ConnectivityManager connectivityManager 
    	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    	    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
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
	
	private class CachingImageURL extends AsyncTask<Void, Void, Void> {
		 
    	Context context;
    	String jsonStr=null;
    	String imageURL=null, imageThumbURL=null;
        public CachingImageURL(Context context) {
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
            jsonStr = sh.makeServiceCall(AppConstants.URLS.PORTFOLIO_URL, ServiceHandler.GET);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    portfolio = jsonObj.getJSONArray("portfolio");
                    
                    // looping through All Contacts
                    for (int i = 0; i < portfolio.length(); i++) {
                        JSONObject c = portfolio.getJSONObject(i);
                         
                        imageURL = "http://arpitdesai.in/mystudio/" +c.getString("image");
                        imageThumbURL = "http://arpitdesai.in/mystudio/"+c.getString("thumb_image");
                        Images.imageUrls[i] = imageURL;
                        Images.imageThumbUrls[i] = imageThumbURL;
                    }
                    
//                    Log.e("imageURL","Image"+ imageURL + "ThumbImage" + imageThumbURL);
 
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
            if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
                final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(android.R.id.content, new ImageGridFragment(), TAG);
                ft.commit();
            }
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
        }
 
    }
}
