package imageUtils;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import demo.vicshady.rajvidesigning.R;
import demo.vicshady.portfolio.app.AppConstants;

public class ImageZoomActivity extends SherlockFragmentActivity {
	Typeface fontStyle;
	ActionBar actionBar;
	ImageView image;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_detail);
		
		image = (ImageView) findViewById(R.id.imageView);
		
		fontStyle  = Typeface.createFromAsset(getAssets(), AppConstants.fontStyle);
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("Portfolio");
		
		fontActionBar(actionBar.getTitle().toString());
		actionBar.setIcon(android.R.drawable.ic_menu_gallery);
        
        String image_url = getIntent().getExtras().getString("IMAGEURL");//"http://api.androidhive.info/images/sample.jpg";
        
        ImageLoader imgLoader = new ImageLoader(getApplicationContext());
        
        imgLoader.DisplayImage(image_url, R.drawable.empty_photo, image);
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
