package com.example.guest.mylinks.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.guest.mylinks.models.Link;
import com.example.guest.mylinks.ui.LinkDetailFragment;

import java.util.ArrayList;

public class LinkPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Link> mLinks;

    public LinkPagerAdapter(FragmentManager fm, ArrayList<Link> links) {
        super(fm);
        mLinks = links;
    }

    @Override
    public Fragment getItem(int position) {
        return LinkDetailFragment.newInstance(mLinks.get(position));
    }

    @Override
    public int getCount() {
        return mLinks.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mLinks.get(position).getName();
    }
}
