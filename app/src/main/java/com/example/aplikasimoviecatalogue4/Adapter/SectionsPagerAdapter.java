package com.example.aplikasimoviecatalogue4.Adapter;

import android.content.Context;

import com.example.aplikasimoviecatalogue4.Fragment.FavoriteMovieFragment;
import com.example.aplikasimoviecatalogue4.Fragment.FavoriteTvFragment;
import com.example.aplikasimoviecatalogue4.R;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    //nama tab nya
    @StringRes
    private final int[] title = new int[]{
            R.string.tab_favorite_movie,
            R.string.tab_favorite_tv,
    };

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FavoriteMovieFragment();
                break;
            case 1:
                fragment = new FavoriteTvFragment();
                break;
            default:
                fragment = null;
                break;
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(title[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
