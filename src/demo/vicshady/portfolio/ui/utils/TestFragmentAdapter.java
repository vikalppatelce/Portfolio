package demo.vicshady.portfolio.ui.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import demo.vicshady.rajvidesigning.R;

public class TestFragmentAdapter extends FragmentPagerAdapter  {
    protected static final int[] CONTENT = new int[] { 
    	R.drawable.update_tour_phone0,
        R.drawable.update_tour_phone1,
        R.drawable.update_tour_phone2,
        R.drawable.update_tour_phone3
    };
    protected static final String[] UCONTENT = new String[] { 
    	"Header_Title_1",
        "Header_Title_2",
        "Header_Title_3",
        "Header_Title_4"
    };
    protected static final String[] LCONTENT = new String[] { 
    	"Detail111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111",
        "Detail222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222",
        "Detail3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333",
        "Detail4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444"
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