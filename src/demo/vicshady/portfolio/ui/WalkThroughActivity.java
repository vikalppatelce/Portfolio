package demo.vicshady.portfolio.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.viewpagerindicator.CirclePageIndicator;

import demo.vicshady.portfolio.R;
import demo.vicshady.portfolio.app.Portfolio;
import demo.vicshady.portfolio.ui.utils.BaseSampleActivity;
import demo.vicshady.portfolio.ui.utils.TestFragmentAdapter;

public class WalkThroughActivity extends BaseSampleActivity {

	Button next,previous;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(Portfolio.getPreferences().getFirstTime())
		{
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.walkthrough);

			next = (Button) findViewById(R.id.next);
			previous = (Button) findViewById(R.id.previous);
			mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

			mPager = (ViewPager) findViewById(R.id.pager);
			mPager.setAdapter(mAdapter);

			mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
			mIndicator.setViewPager(mPager);
			Portfolio.getPreferences().setFirstTime(false);
			Portfolio.getApplication().createDatabase();
		}
		else
		{
			Intent i = new Intent(this, HomeActivity.class);
			startActivity(i);
			finish();
		}
	}
	public void onNext(View v) {
		if (mPager.getCurrentItem() == 2) {
			mPager.setCurrentItem(mPager.getCurrentItem() + 1);
			next.setText("Finish");
		} else if (mPager.getCurrentItem() == 3
				&& next.getText().toString().equalsIgnoreCase("finish")) {
			Intent i = new Intent(getApplicationContext(), HomeActivity.class);
			startActivity(i);
			finish();
		} else {
			mPager.setCurrentItem(mPager.getCurrentItem() + 1);
			next.setText("Next");
			if (mPager.getCurrentItem() > 0)
				previous.setVisibility(View.VISIBLE);
			else
				previous.setVisibility(View.INVISIBLE);
		}
	}
	
	public void onPrevious(View v) {
		mPager.setCurrentItem(mPager.getCurrentItem() - 1);
		next.setText("Next");
		if (mPager.getCurrentItem() == 0)
			previous.setVisibility(View.INVISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
