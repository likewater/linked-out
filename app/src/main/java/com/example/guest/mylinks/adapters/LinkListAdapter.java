package com.example.guest.mylinks.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.mylinks.R;
import com.example.guest.mylinks.models.Link;
import com.example.guest.mylinks.ui.LinkDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LinkListAdapter extends RecyclerView.Adapter<LinkListAdapter.LinkViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Link> mLinks = new ArrayList<>();
    private Context mContext;

    public LinkListAdapter(Context context, ArrayList<Link> links) {
        mContext = context;
        mLinks = links;
    }

    @Override
    public LinkListAdapter.LinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_list_item, parent, false);
        LinkViewHolder viewHolder = new LinkViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LinkListAdapter.LinkViewHolder holder, int position) {
        holder.bindLink(mLinks.get(position));
    }

    @Override
    public int getItemCount() {
        return mLinks.size();
    }

    public class LinkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.linkImageView) ImageView mLinkImageView;
        @Bind(R.id.linkNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public LinkViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, LinkDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("restaurants", Parcels.wrap(mLinks));
            mContext.startActivity(intent);
        }

        public void bindLink(Link link) {

//            Picasso.with(mContext).load(link.getImageUrl()).into(mRestaurantImageView);
            Picasso.with(mContext)
                    .load(link.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mLinkImageView);

            mNameTextView.setText(link.getName());
            mCategoryTextView.setText(link.getCategories().get(0));
            mRatingTextView.setText("Rating: " + link.getRating() + "/5");
        }
    }
}
