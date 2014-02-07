package demo.vicshady.portfolio.ui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import demo.vicshady.portfolio.R;

public class HomeActivity extends Activity{

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		TextView uppertext = new TextView(this);
    	uppertext.setText("UpperText");
    	//uppertext.setGravity(Gravity.CENTER_HORIZONTAL);
    	uppertext.setTextColor(Color.GRAY);
    	uppertext.setTypeface(null, Typeface.ITALIC);
    	uppertext.setTextSize(15 * getResources().getDisplayMetrics().density);
    	uppertext.setPadding(20, 0, 20, 0);
    	LinearLayout.LayoutParams upperTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        
    	ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.update_tour_phone0);
        img.setScaleType(ScaleType.CENTER_CROP);
        //img.setPadding(20, 0, 20, 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 0, 20, 0);
        
        TextView lowertext = new TextView(this);
        lowertext.setText("lowertext");
        lowertext.setTextColor(Color.GRAY);
        lowertext.setTypeface(null, Typeface.ITALIC);
        lowertext.setTextSize(10 * getResources().getDisplayMetrics().density);
        lowertext.setPadding(20, 0, 20, 0);
        LinearLayout.LayoutParams lowerTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);        

        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(1);
        layout.addView(uppertext,upperTextParams);
        layout.addView(img,layoutParams);
        layout.addView(lowertext,lowerTextParams);
        setContentView(layout);
	}
}
