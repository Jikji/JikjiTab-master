package com.example.gigabyte.CoupleByDesigner.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.gigabyte.CoupleByDesigner.Item.RecyclerViewHomeItem;
import com.example.gigabyte.CoupleByDesigner.R;
import com.ssomai.android.scalablelayout.ScalableLayout;

import java.util.ArrayList;

public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.ItemViewHolder> {

    ArrayList<RecyclerViewHomeItem> mItems = new ArrayList<>();

    public RecyclerViewHomeAdapter(ArrayList<RecyclerViewHomeItem> mItems) {
        this.mItems = mItems;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_home_group,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
//        holder.mImageView1.setImageResource(mItems.get(position).getImageView1Id());
//        holder.mImageView2.setImageResource(mItems.get(position).getImageView2Id());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ScalableLayout mScalableLayout;
        private LinearLayout mLinearLayout;
        private ImageView mImageView1;
        private ImageView mImageView2;

        public ItemViewHolder(View itemView) {
            super(itemView);
//            mScalableLayout = (ScalableLayout) itemView.findViewById(R.id.scalablelayout_home_group_product);
//            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.layout_home_group_product);
//            mImageView1 = (ImageView) itemView.findViewById(R.id.iv_home_group_product1);
//            mImageView2 = (ImageView) itemView.findViewById(R.id.iv_home_group_product2);
        }
    }
}
