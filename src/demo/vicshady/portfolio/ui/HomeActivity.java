package demo.vicshady.portfolio.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import demo.vicshady.portfolio.R;

public class HomeActivity  extends SherlockFragmentActivity{

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
	}
	
	public void onSendPhoto(View v)
	{
		Intent i = new Intent(HomeActivity.this, SendPhotoActivity.class);
		startActivity(i);
	}
	public void onAbout(View v)
	{
		Intent i = new Intent(HomeActivity.this,AboutActivity.class);
		startActivity(i);
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
