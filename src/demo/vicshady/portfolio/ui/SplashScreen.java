package demo.vicshady.portfolio.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import demo.vicshady.portfolio.R;
import demo.vicshady.portfolio.app.AppConstants;

public class SplashScreen extends Activity
{
  private final int SPLASH_DISPLAY_LENGTH = 2500;
  TextView developed;
  Typeface stylefont;
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.splashscreen);
    
    stylefont = Typeface.createFromAsset(getAssets(), AppConstants.fontStyle);
    
    developed = (TextView)findViewById(R.id.developed);
	developed.setTypeface(stylefont);
   
    new Thread()
    {
      @Override
	public void run()
      {
        try
        {
          sleep(SPLASH_DISPLAY_LENGTH);
          Intent localIntent = new Intent(SplashScreen.this, HomeActivity.class);
          SplashScreen.this.startActivity(localIntent);
          SplashScreen.this.finish();
          return;
        }
        catch (Exception localException)
        {
        }
      }
    }
    .start();
  }
  public void onFooter(View v)
	{
		Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.krishnatechno.co.in/"));
		startActivity(browserIntent);
	}
}
