package demo.vicshady.portfolio.ui.utils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public final class TestFragment extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";

    public static TestFragment newInstance(int content,String ucontent,String lcontent) {
        TestFragment fragment = new TestFragment();
        fragment.mContent = content;
        fragment.uContent = ucontent;
        fragment.lContent = lcontent;
        return fragment;
    }

    private int mContent = 0;
    private String uContent = null;
    private String lContent = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getInt(KEY_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		TextView uppertext = new TextView(getActivity());
    	uppertext.setText(uContent);
    	uppertext.setTextColor(Color.GRAY);
    	uppertext.setTypeface(null, Typeface.ITALIC);
    	uppertext.setTextSize(15 * getResources().getDisplayMetrics().density);
    	uppertext.setPadding(20, 0, 20, 0);
    	LinearLayout.LayoutParams upperTextParams = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        
    	ImageView img = new ImageView(getActivity());
        img.setImageResource(mContent);
        img.setScaleType(ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 0, 20, 0);
        
        TextView lowertext = new TextView(getActivity());
        lowertext.setText(lContent);
        lowertext.setTextColor(Color.GRAY);
        lowertext.setTypeface(null, Typeface.ITALIC);
        lowertext.setTextSize(10 * getResources().getDisplayMetrics().density);
        lowertext.setPadding(20, 0, 20, 0);
        LinearLayout.LayoutParams lowerTextParams = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);        

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(1);
        layout.addView(uppertext,upperTextParams);
        layout.addView(img,layoutParams);
        layout.addView(lowertext,lowerTextParams);
        
        return layout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CONTENT, mContent);
    }
}
