package com.example.gigabyte.CoupleByDesigner.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.gigabyte.CoupleByDesigner.Item.RecyclerViewHomeItem;
import com.example.gigabyte.CoupleByDesigner.R;
import com.ssomai.android.scalablelayout.ScalableLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.ItemViewHolder> {

    ArrayList<RecyclerViewHomeItem> mItems;
    private Context mContext;

    public RecyclerViewHomeAdapter(ArrayList<RecyclerViewHomeItem> mItems, Context mContext) {
        this.mItems = mItems;
        this.mContext = mContext;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_home_group,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Glide.with(mContext).
                load(mItems.get(position).getmUrl1())
                .into(holder.mImageView1);

        Glide.with(mContext).
                load(mItems.get(position).getmUrl2())
                .into(holder.mImageView2);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView1;
        private ImageView mImageView2;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mImageView1 = (ImageView) itemView.findViewById(R.id.iv_home_group_product1);
            mImageView2 = (ImageView) itemView.findViewById(R.id.iv_home_group_product2);
        }
    }
}
