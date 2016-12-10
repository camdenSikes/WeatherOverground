package hu.ait.camdensikes.weatheroverground.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hu.ait.camdensikes.weatheroverground.fragment.MainFragment;
import hu.ait.camdensikes.weatheroverground.fragment.DetailsFragment;

/**
 * Created by Camden Sikes on 11/29/2016.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MainFragment();
            case 1:
                return new DetailsFragment();
            default:
                return new MainFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "main";
            case 1:
                return "details";
            default:
                return "main";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
