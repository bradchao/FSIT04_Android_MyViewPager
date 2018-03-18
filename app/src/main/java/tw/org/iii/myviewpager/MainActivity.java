package tw.org.iii.myviewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private ArrayList<View> views;
    private ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //flipper = findViewById(R.id.flipper);
        pager = findViewById(R.id.pager);
        initPager();
    }

    private void initPager(){
        views = new ArrayList<>();

        LayoutInflater inflater = LayoutInflater.from(this);
        View page0 = inflater.inflate(R.layout.page0, null);
        View page1 = inflater.inflate(R.layout.page1, null);
        View page2 = inflater.inflate(R.layout.page2, null);
        View page3 = inflater.inflate(R.layout.page3, null);
        View page4 = inflater.inflate(R.layout.page4, null);
        views.add(page0);
        views.add(page1);views.add(page2);views.add(page3);
        views.add(page4);

        flipper = page2.findViewById(R.id.flipper);
        View f0 = flipper.getChildAt(0);
        View f1 = flipper.getChildAt(1);
        View f2 = flipper.getChildAt(2);
        FlipperOnClick flipperOnClick = new FlipperOnClick();
        f0.setOnClickListener(flipperOnClick);
        f1.setOnClickListener(flipperOnClick);
        f2.setOnClickListener(flipperOnClick);


        pager.setAdapter(new MyPagerAdapter());
        pager.setOnPageChangeListener(new MyPagerChangeListener());
        pager.setCurrentItem(1);

    }

    private class FlipperOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            flipper.showNext();
        }
    }


    private class MyPagerChangeListener extends ViewPager.SimpleOnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            Log.v("brad", "now : " + position);
            if (position == 0){
                pager.setCurrentItem(1);
            }else if (position == 4){
                pager.setCurrentItem(3);
            }
        }
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View page = views.get(position);
            pager.addView(page);
            return page;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            View page = views.get(position);
            pager.removeView(page);
        }
    }

}
