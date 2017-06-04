package com.example.guest.mylinks.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.guest.mylinks.R;
import com.example.guest.mylinks.models.Link;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LinkDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.linkImageView) ImageView mImageLabel;
    @Bind(R.id.linkNameTextView) TextView mNameLabel;
    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveLinkButton) TextView mSaveLinkButton;

    private Link mLink;

    public static LinkDetailFragment newInstance(Link link) {
        LinkDetailFragment linkDetailFragment = new LinkDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("link", Parcels.wrap(link));
        linkDetailFragment.setArguments(args);
        return linkDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLink = Parcels.unwrap(getArguments().getParcelable("link"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_link_detail, container, false);
        ButterKnife.bind(this, view);

//        Picasso.with(view.getContext()).load(mLink.getImageUrl()).into(mImageLabel);
        Picasso.with(view.getContext())
                .load(mLink.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mNameLabel.setText(mLink.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mLink.getCategories()));
        mRatingLabel.setText(Double.toString(mLink.getRating()) + "/5");
        mPhoneLabel.setText(mLink.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mLink.getAddress()));

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mLink.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mLink.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mLink.getLatitude()
                            + "," + mLink.getLongitude()
                            + "?q=(" + mLink.getName() + ")"));
            startActivity(mapIntent);
        }
    }
}
