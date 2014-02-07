package demo.vicshady.portfolio.ui.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import demo.vicshady.portfolio.R;

public class TestFragmentAdapter extends FragmentPagerAdapter  {
    protected static final int[] CONTENT = new int[] { 
    	R.drawable.update_tour_phone0,
        R.drawable.update_tour_phone1,
        R.drawable.update_tour_phone2,
        R.drawable.update_tour_phone3
    };
    protected static final String[] UCONTENT = new String[] { 
    	"upper_text_1",
        "upper_text_2",
        "upper_text_3",
        "upper_text_4"
    };
    protected static final String[] LCONTENT = new String[] { 
    	"R.string.lower_text_1",
        "R.string.lower_text_2",
        "R.string.lower_text_3",
        "R.string.lower_text_4"
    };
    private int mCount = CONTENT.length;

    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TestFragment.newInstance(CONTENT[position % CONTENT.length],UCONTENT[position % UCONTENT.length],LCONTENT[position % LCONTENT.length]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}