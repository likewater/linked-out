package com.example.guest.mylinks.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.guest.mylinks.R;
import com.example.guest.mylinks.adapters.LinkPagerAdapter;
import com.example.guest.mylinks.models.Link;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LinkDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private LinkPagerAdapter adapterViewPager;
    ArrayList<Link> mLinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_detail);
        ButterKnife.bind(this);

        mLinks = Parcels.unwrap(getIntent().getParcelableExtra("links"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new LinkPagerAdapter(getSupportFragmentManager(), mLinks);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
