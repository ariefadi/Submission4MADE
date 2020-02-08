package com.example.aplikasimoviecatalogue4.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplikasimoviecatalogue4.Adapter.SectionsPagerAdapter;
import com.example.aplikasimoviecatalogue4.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    private ViewPager pager;
    private TabLayout tabs;


    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(), getChildFragmentManager());
        pager = (ViewPager)view.findViewById(R.id.pager);
        pager.setAdapter(sectionsPagerAdapter);
        tabs = (TabLayout)view.findViewById(R.id.tab);
        tabs.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(android.R.color.white));
        tabs.setupWithViewPager(pager);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
    }


}
