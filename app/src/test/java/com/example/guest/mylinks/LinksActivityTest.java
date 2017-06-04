package com.example.guest.mylinks;

import android.os.Build;
import android.widget.ListView;

import com.example.guest.mylinks.ui.LinksActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class LinksActivityTest {
    private LinksActivity activity;
    private ListView mRestaurantListView;

//    @Before
//    public void setup() {
//        activity = Robolectric.setupActivity(LinksActivity.class);
//        mRestaurantListView = (ListView) activity.findViewById(R.id.listView);
//    }

    @Test
    public void restaurantListViewPopulates() {
        assertNotNull(mRestaurantListView.getAdapter());
        assertEquals(mRestaurantListView.getAdapter().getCount(), 15);
    }
}