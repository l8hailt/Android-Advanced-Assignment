package vn.poly.hailt.learningsupport.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.poly.hailt.learningsupport.fragment.ScheduleFragment;
import vn.poly.hailt.learningsupport.fragment.TestScheduleFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ScheduleFragment();
            case 1:
                return new TestScheduleFragment();
            default:
                return new ScheduleFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Lịch học";
            case 1:
                return "Lịch Thi";
            default:
                return "Lịch học";
        }
    }
}
