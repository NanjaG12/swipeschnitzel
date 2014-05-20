package com.swipeschnitzel.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

/**
 * Created by Nanja on 18.04.2014.
 */
public class SwipeGoals extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v13.app.FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_activity);

        // Create the adapter that will return a fragment for each
        // primary sections of the activity.
        mSectionPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionPagerAdapter);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the /pages.
     */
    public static class SectionsPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
        private static final String[] CONTENT = new String[]{
                "Fachschaft",
                "Validierungsstation",
                "Cafeteria",
                "Zugansdaten",
                "Labore",
                "Praesenzbibliothek",
                "Dod.com",
                "Studenten Service",
                "Zentral Bibliothek",
                "Mensa",
        };
        private static final  int[] ICONS = new int[] {
                R.drawable.goal_1,
                R.drawable.goal_2,
                R.drawable.goal_3,
                R.drawable.goal_4,
                R.drawable.goal_5,
                R.drawable.goal_6,
                R.drawable.goal_7,
                R.drawable.goal_8,
                R.drawable.goal_9,
                R.drawable.goal_10,
        };

        private int mCount = CONTENT.length;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(CONTENT[position % CONTENT.length]);
        }

        @Override
        public int getCount() {
            //Total swiping pages
            return mCount;
        }

        @Override
        public  CharSequence getPageTitle(int position) {
            return SectionsPagerAdapter.CONTENT[position % CONTENT.length];
        }

        //@Override
        public int getIconResId(int index) {
            return ICONS[index];
        }



        public void setCount(int count){
            if (count > 0 && count <= 10) {
                mCount = count;
                notifyDataSetChanged();
            }
        }
    }

}
