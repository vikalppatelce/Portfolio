package demo.vicshady.portfolio.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import demo.vicshady.portfolio.R;
import demo.vicshady.portfolio.app.AppConstants;

public class HomeActivity  extends SherlockFragmentActivity{

	Button btnsend,btnabout,btnportfolio,btnplay;
	
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
		
		btnsend.setTypeface(stylefont);
		btnabout.setTypeface(stylefont);
		btnportfolio.setTypeface(stylefont);
		btnplay.setTypeface(stylefont);
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
	       inflater.inflate(R.menu.main, menu);
		 return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case R.id.more: 
        	return true;
        case R.id.action_settings:
        	Intent iSettings = new Intent(this,PrefsActivity.class);
        	startActivity(iSettings);
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
