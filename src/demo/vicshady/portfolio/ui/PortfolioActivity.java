package demo.vicshady.portfolio.ui;

import imageUtils.ImageGridFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import demo.vicshady.portfolio.R;
import demo.vicshady.portfolio.app.AppConstants;

public class PortfolioActivity extends SherlockFragmentActivity{

	Typeface fontStyle;
	ActionBar actionBar;
	private static final String TAG = "ImageGridActivity";
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		
		if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, new ImageGridFragment(), TAG);
            ft.commit();
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
